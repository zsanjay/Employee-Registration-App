package com.registration.employee.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registration.employee.dto.DepartmentDto;
import com.registration.employee.dto.EmployeeDto;
import com.registration.employee.service.DepartmentService;
import com.registration.employee.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {

	private final EmployeeService employeeService;
	private final DepartmentService departmentService;
	
	public EmployeeRestController(EmployeeService employeeService ,DepartmentService departmentService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
	}
	
	@GetMapping("/departments")
	public List<DepartmentDto> getDepartments(){
		
		return departmentService.getDepartments();
	}
	
	@GetMapping("/employees")
	public List<EmployeeDto> getEmployeeList(){
		
	    return employeeService.getEmployees();
	}
	
	@GetMapping("/employees/search")
	public List<EmployeeDto> getEmployeeListByQuery(@RequestParam("searchInput") String searchInput){
		
	    return employeeService.getEmployees(searchInput);
	}
	
	@GetMapping("/employees/{employeeId}")
	public EmployeeDto getEmployeeDetails(@PathVariable("employeeId") Long employeeId) {
		
		return employeeService.getEmployeeDetails(employeeId);
	}
	
	
	@PostMapping("/employees")
	public void createEmployee(@RequestBody EmployeeDto employee) {
		
		employeeService.createEmployee(employee);
	}
	
	@PutMapping("/employees/{employeeId}")
	public void updateEmployee(@PathVariable("employeeId") Long employeeId , @RequestBody EmployeeDto employee) {
		
		employeeService.updateEmployee(employeeId, employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable("id") Long id){
		
		employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/employees/top")
	public List<EmployeeDto> getTopEmployeeList(@RequestParam("top") Integer top){
		
	    return employeeService.getTopEmployees(top);
	}
	
}
