package com.msit.jatin.main.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterRequest {
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String realName;

	public RegisterRequest() {
	}

	public RegisterRequest(String email, String password, String realName) {
		this.email = email;
		this.realName = realName;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getrealName() {
		return realName;
	}

	public void setrealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
