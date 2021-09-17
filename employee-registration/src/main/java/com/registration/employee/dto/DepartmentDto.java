package com.registration.employee.dto;

public class DepartmentDto {
	
	private Long id;
	
	private String code;
	
	private String description;
	
	public DepartmentDto(Long id , String code , String desc) {
		
		this.id = id;
		this.code = code;
		this.description = desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
