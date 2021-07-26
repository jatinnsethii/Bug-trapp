package com.msit.jatin.main.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msit.jatin.main.dto.AuthenticationResponse;
import com.msit.jatin.main.dto.LoginRequest;
import com.msit.jatin.main.dto.RegisterRequest;
import com.msit.jatin.main.service.AuthService;

@RestController
@RequestMapping("bugapi/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signup")
	public ResponseEntity<String> singup(@Valid @RequestBody RegisterRequest registerRequest) {
		authService.signUp(registerRequest);
		return new ResponseEntity<>("User Registration Successfull", HttpStatus.CREATED);
	}

	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authService.validate(token);
		return new ResponseEntity<>("Account verified", HttpStatus.OK);
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
}
