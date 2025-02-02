package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Employee;
import com.backend.repository.EmployeeRepository;


@Service
public class EmployeeService {
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	
	public List<Employee>getEmployees(){
		return (List<Employee>) employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}
	
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
	
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

}
