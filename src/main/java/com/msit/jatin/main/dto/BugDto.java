package com.msit.jatin.main.dto;

import javax.validation.constraints.NotBlank;

public class BugDto {
	private Long id;
	@NotBlank
	private String bugDetails;
	@NotBlank
	private String description;
	@NotBlank
	private String compName;

	public BugDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBugDetails() {
		return bugDetails;
	}

	public void setBugDetails(String bugDetails) {
		this.bugDetails = bugDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public BugDto(Long id, String bugDetails, String description, String compName) {
		this.id = id;
		this.bugDetails = bugDetails;
		this.description = description;
		this.compName = compName;
	}

}
