package com.registration.employee.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public Integer createEmployee(@Valid @RequestBody EmployeeDto employee) {
		
		return employeeService.createEmployee(employee);
	}
	
	@PutMapping("/employees/{employeeId}")
	public void updateEmployee(@PathVariable("employeeId") Long employeeId , @Valid @RequestBody EmployeeDto employee) {
		
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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
