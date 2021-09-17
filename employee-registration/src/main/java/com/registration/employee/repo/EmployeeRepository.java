package com.registration.employee.repo;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.registration.employee.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee , Long>{
	
	Employee findByEmpNo(Integer empNo);
	
	@Query("SELECT emp from Employee as emp where emp.name like :text or CAST(emp.empNo AS string) like :text")
	List<Employee> findByNameORNumber(@Param("text") String text);
	
}
