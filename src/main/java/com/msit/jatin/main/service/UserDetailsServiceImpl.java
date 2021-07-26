package com.msit.jatin.main.service;

import static java.util.Collections.singletonList;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msit.jatin.main.dao.UserDao;
import com.msit.jatin.main.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserDao userDao;

	public UserDetailsServiceImpl(UserDao userDao) {

		this.userDao = userDao;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) {
		Optional<User> userOptional = userDao.findByEmail(email);
		User user = userOptional
				.orElseThrow(() -> new UsernameNotFoundException("no username found with: " + userOptional));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getEnabled(), true, true, true, getAuthorities("USER"));

	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}

}
