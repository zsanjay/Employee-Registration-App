package com.registration.employee.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


public class EmployeeDto {
	
	private Long id;
	
	@NotNull(message = "Employee is mandatory ")
	private Integer empNo;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotNull(message = "Date of Joining is mandatory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doj;
	
	@NotBlank(message = "Department code is mandatory")
	private String deptCode;
	
	private String deptDescription;
	
	@NotNull(message = "Salary is mandatory")
	private Long salary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getDeptDescription() {
		return deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

}
