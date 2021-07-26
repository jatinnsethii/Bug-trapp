package com.msit.jatin.main.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msit.jatin.main.dao.UserDao;
import com.msit.jatin.main.dao.VerificationTokenDao;
import com.msit.jatin.main.dto.AuthenticationResponse;
import com.msit.jatin.main.dto.LoginRequest;
import com.msit.jatin.main.dto.RegisterRequest;
import com.msit.jatin.main.exceptions.BugTrackingException;
import com.msit.jatin.main.model.NotificationEmail;
import com.msit.jatin.main.model.User;
import com.msit.jatin.main.model.VerificationToken;
import com.msit.jatin.main.security.JwtProvider;

@Service
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenDao verificationTokenDao;
	private final UserDao userDao;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;

	public AuthService(UserDao userDao, PasswordEncoder passwordEncoder, VerificationTokenDao verificationTokenDao,
			MailService mailService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenDao = verificationTokenDao;
		this.mailService = mailService;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
	}

	@Transactional
	public void signUp(RegisterRequest registerRequest) {
		User user = new User();
		user.setRealName(registerRequest.getrealName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEnabled(false);
		user.setMyBugsLink("http://localhost:8080/myBugsUrl");
		userDao.save(user);
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please activate your account.", user.getEmail(),
				"Thankyou for signing up at Bug Tracker " + "http://localhost:8080/bugapi/auth/accountVerification/"
						+ token));
	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verifyToken = new VerificationToken();
		verifyToken.setToken(token);
		verifyToken.setUser(user);
		verificationTokenDao.save(verifyToken);
		return token;

	}

	@Transactional
	public void validate(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenDao.findByToken(token);
		verificationToken.orElseThrow(() -> new BugTrackingException("Illegal token,Please contact admin"));
		fetchUserAndEnable(verificationToken.get());

	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String email = verificationToken.getUser().getEmail();
		User user = userDao.findByEmail(email)
				.orElseThrow(() -> new BugTrackingException("No user found with email: " + email));
		user.setEnabled(true);
		userDao.save(user);

	}

	@Transactional(readOnly = true)
	public User getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDao.findByEmail(principal.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(token, loginRequest.getEmail());
	}

}
