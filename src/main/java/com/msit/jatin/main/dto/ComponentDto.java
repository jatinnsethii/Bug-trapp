package com.msit.jatin.main.dto;

import javax.validation.constraints.NotBlank;

public class ComponentDto {
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String desc;
	@NotBlank
	private String productName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ComponentDto(Long id, String name, String desc, String productName) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.productName = productName;
	}

	public ComponentDto() {
	}
}
