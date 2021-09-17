package com.registration.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.registration.employee.model.Department;
import com.registration.employee.repo.DepartmentRepository;

@SpringBootApplication
public class EmployeeRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRegistrationApplication.class, args);
	}

}

@Component
class DataCommandLineRunner implements CommandLineRunner{

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Department department = new Department();
		department.setCode("AD");
		department.setDescription("Administration");
		departmentRepository.save(department);
		
		department = new Department();
		department.setCode("IT");
		department.setDescription("Information technology");
		departmentRepository.save(department);
		
		department = new Department();
		department.setCode("HD");
		department.setDescription("Help Desk");
		departmentRepository.save(department);
		
		department = new Department();
		department.setCode("HR");
		department.setDescription("Human Resource");
		departmentRepository.save(department);
		
		department = new Department();
		department.setCode("OP");
		department.setDescription("Operation");
		departmentRepository.save(department);
		
	
	}
}
