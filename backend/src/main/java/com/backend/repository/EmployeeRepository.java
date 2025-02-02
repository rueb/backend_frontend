package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.backend.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
