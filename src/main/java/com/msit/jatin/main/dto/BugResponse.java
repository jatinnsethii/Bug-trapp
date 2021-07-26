package com.msit.jatin.main.dto;

public class BugResponse {

	private String bugDetails;
	private String description;
	private String productName;
	private String compName;
	private String createdTime;
	private String updatedTime;
	private String owner;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BugResponse(String bugDetails, String description, String productName, String compName, String createdTime,
			String updatedTime, String owner,String status) {
		this.bugDetails = bugDetails;
		this.description = description;
		this.productName = productName;
		this.compName = compName;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.owner = owner;
		this.status=status;
		
	}

	public BugResponse() {

	}
}
