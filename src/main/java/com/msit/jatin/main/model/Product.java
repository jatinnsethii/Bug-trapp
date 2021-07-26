package com.msit.jatin.main.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private String productDesc;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Component> comp;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Bug> bugs;

	public void addBug(Bug bug) {
		if (bugs == null) {
			bugs = new ArrayList<Bug>();
		}
		bugs.add(bug);
	}

	public List<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public List<Component> getComp() {
		return comp;
	}

	public void setComp(List<Component> comp) {
		this.comp = comp;
	}

	public void addComponent(Component component) {
		if (comp == null) {
			comp = new ArrayList<Component>();
		}
		comp.add(component);
	}

	public Product(String productName, String productDesc) {
		this.productName = productName;
		this.productDesc = productDesc;
	}

	public Product() {
	};
	// default milestone figure out

}
