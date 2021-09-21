package com.registration.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.registration.employee.config.ResourceExistException;
import com.registration.employee.config.ResourceNotFoundException;
import com.registration.employee.dto.EmployeeDto;
import com.registration.employee.model.Department;
import com.registration.employee.model.Employee;
import com.registration.employee.repo.DepartmentRepository;
import com.registration.employee.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository , DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}
	

	@Override
	public List<EmployeeDto> getEmployees() {
		
		List<EmployeeDto> employees = new ArrayList<>();
				
		this.employeeRepository.findAll().forEach(emp -> {
			
			EmployeeDto empDto = new EmployeeDto();
			BeanUtils.copyProperties(emp, empDto);
			empDto.setDeptCode(emp.getDepartment().getCode());
			empDto.setDeptDescription(emp.getDepartment().getDescription());
			
			employees.add(empDto);
			
		});
		
		return employees;
		
	}

	@Override
	public EmployeeDto getEmployeeDetails(Long id) {
		
		EmployeeDto employeeDto = new EmployeeDto();
		
		Optional<Employee> optional = employeeRepository.findById(id);
		
		if(optional.isPresent()) {
			
		    Employee employee =	optional.get();
			BeanUtils.copyProperties(employee, employeeDto);
			employeeDto.setDeptCode(employee.getDepartment().getCode());
			employeeDto.setDeptDescription(employee.getDepartment().getDescription());

		}else {
			throw new ResourceNotFoundException("Employee is not exist");
		}
				
		return employeeDto;
	}

	@Override
	public Integer createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = employeeRepository.findByEmpNo(employeeDto.getEmpNo());
		
		if(employee != null) {
			throw new ResourceExistException("Employee already exists with the Employee No. "+ employeeDto.getEmpNo());
		}
		
		employee = new Employee();
		
		BeanUtils.copyProperties(employeeDto, employee);
		
		Department dept = departmentRepository.findByCode(employeeDto.getDeptCode());
		employee.setDepartment(dept);
		
		Employee newEmployee = employeeRepository.save(employee);
		
		return newEmployee.getEmpNo();

	}

	@Override
	public void updateEmployee(Long id, EmployeeDto employeeDto) {
		
		Optional<Employee> optEmployee = employeeRepository.findById(id);
		
		if(optEmployee.isPresent()) {
			
			Employee employee = optEmployee.get();
			
			employee.setEmpNo(employeeDto.getEmpNo());
			employee.setName(employeeDto.getName());
			employee.setSalary(employeeDto.getSalary());
			employee.setDoj(employeeDto.getDoj());
			
			Department dept = departmentRepository.findByCode(employeeDto.getDeptCode());
			employee.setDepartment(dept);
					
			employeeRepository.save(employee);
			
		}else {
			throw new ResourceNotFoundException("Employee is not exist");
		}
		
	}

	@Override
	public void deleteEmployee(Long id) {
	
		employeeRepository.deleteById(id);
	}


	@Override
	public List<EmployeeDto> getEmployees(String input) {
		
		List<EmployeeDto> employeesDto = new ArrayList<>();
	   
		this.employeeRepository.findByNameORNumber("%" + input + "%" ).forEach(emp -> {
			
			EmployeeDto empDto = new EmployeeDto();
			BeanUtils.copyProperties(emp, empDto);
			empDto.setDeptCode(emp.getDepartment().getCode());
			empDto.setDeptDescription(emp.getDepartment().getDescription());

			employeesDto.add(empDto);
			
		});
		
		if(employeesDto.isEmpty())
			throw new ResourceNotFoundException("Not Found");
		
		
		return employeesDto;
	}
	
        @Override
	public List<EmployeeDto> getTopEmployees(Integer top) {
		
		if(top < 1)
			throw new ResourceNotFoundException("Input must be greater than 1");
		
		List<EmployeeDto> employeesDto = new ArrayList<>();
		
		Page<Employee> page = employeeRepository.findAll(PageRequest.of(0, top, Sort.by(Sort.Direction.ASC, "empNo")));
		
		 page.getContent().forEach(emp -> {
			
			EmployeeDto empDto = new EmployeeDto();
			BeanUtils.copyProperties(emp, empDto);
			empDto.setDeptCode(emp.getDepartment().getCode());
			empDto.setDeptDescription(emp.getDepartment().getDescription());

			employeesDto.add(empDto);
			
		});
		 
		 if(employeesDto.isEmpty())
				throw new ResourceNotFoundException("Not Found");
		 
		 
		return employeesDto;
		
	}

}
