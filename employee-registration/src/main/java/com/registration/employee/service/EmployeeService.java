package com.registration.employee.service;

import java.util.List;

import com.registration.employee.dto.EmployeeDto;

public interface EmployeeService {

	List<EmployeeDto> getEmployees();
	
	List<EmployeeDto> getEmployees(String input);

	EmployeeDto getEmployeeDetails(Long id);

	void createEmployee(EmployeeDto employee);

	void updateEmployee(Long id, EmployeeDto employee);

	void deleteEmployee(Long id);

}
