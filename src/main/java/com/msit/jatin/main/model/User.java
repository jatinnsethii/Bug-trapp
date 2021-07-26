package com.msit.jatin.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.msit.jatin.main.model.EmailNotification;

@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	@Column(unique = true)
	
	private String email;
	@Column(unique = true)
	
	private String password;
	
	private String realName;
	@Enumerated(EnumType.STRING)
	private EmailNotification emailNotification = EmailNotification.ExcludeSelfChanges;
	private Boolean enabled=false;
	private String myBugsLink;

	public String getMyBugsLink() {
		return myBugsLink;
	}

	public void setMyBugsLink(String myBugsLink) {
		this.myBugsLink = myBugsLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public EmailNotification getEmailNotification() {
		return emailNotification;
	}

	public void setEmailNotification(EmailNotification emailNotification) {
		this.emailNotification = emailNotification;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {

//		setEnabled(enabled);
		return this.enabled;

	}

	public User(String email, String password, String realName, EmailNotification emailNotification, Boolean enabled) {

		this.email = email;
		this.password = password;
		this.realName = realName;
		this.emailNotification = emailNotification;
		this.enabled = enabled;
	}

	public User() {
	}

//	my bugs setup
//	private String myBugs;

}
