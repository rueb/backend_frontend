package com.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backend.entity.Employee;
import com.backend.service.EmployeeService;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	private  EmployeeService employeeService;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Employee employee1 = new Employee("ruben", "dario", "rueb@gmail.com");
		Employee employee2 = new Employee("celene", "velasquez", "celene@gmail.com");
		employeeService.addEmployee(employee1);
		employeeService.addEmployee(employee2);
	}

}
