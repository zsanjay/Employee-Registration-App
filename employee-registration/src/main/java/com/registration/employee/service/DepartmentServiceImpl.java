package com.registration.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.registration.employee.dto.DepartmentDto;
import com.registration.employee.repo.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<DepartmentDto> getDepartments() {
		
		return this.departmentRepository.getDepartments();
		
	}

	
}
