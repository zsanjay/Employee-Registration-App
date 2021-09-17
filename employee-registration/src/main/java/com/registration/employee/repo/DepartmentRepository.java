package com.registration.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.registration.employee.dto.DepartmentDto;
import com.registration.employee.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long>{
	
	@Query("select new com.registration.employee.dto.DepartmentDto(dept.id , dept.code, dept.description) from Department as dept")
	List<DepartmentDto> getDepartments();
	
	Department findByCode(String code);

}
