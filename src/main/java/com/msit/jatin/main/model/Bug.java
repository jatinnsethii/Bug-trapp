package com.msit.jatin.main.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bug_details")
public class Bug {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bug_id;
	
	private String bugDetails;
	
	private Date createdDate;
	private Date updatedTime;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private Product prod;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "componentId", referencedColumnName = "id")
	private Component comp;
	private String status;
	
	


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBug_id() {
		return bug_id;
	}

	public void setBug_id(Long bug_id) {
		this.bug_id = bug_id;
	}

	public String getBugDetails() {
		return bugDetails;
	}

	public void setBugDetails(String bugDetails) {
		this.bugDetails = bugDetails;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Component getComp() {
		return comp;
	}

	public void setComp(Component comp) {
		this.comp = comp;
	}

	public Bug(String bugDetails, Date createdDate, Date updatedTime, String description) {
		this.bugDetails = bugDetails;
		this.createdDate = createdDate;
		this.updatedTime = updatedTime;
		this.description = description;
	}

	public Bug() {
	}

}
