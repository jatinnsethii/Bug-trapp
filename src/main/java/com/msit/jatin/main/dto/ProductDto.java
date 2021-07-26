package com.msit.jatin.main.dto;

import javax.validation.constraints.NotBlank;

public class ProductDto {
	
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String desc;

	public ProductDto() {
	}

	public ProductDto(Long id, String name, String desc) {
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

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
	};

}
