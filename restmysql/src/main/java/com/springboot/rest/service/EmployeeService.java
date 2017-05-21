package com.springboot.rest.service;

import java.util.List;

import com.springboot.rest.model.Employee;

public interface EmployeeService {

	Employee findById(Long id);

	Employee findByName(String name);

	void saveEmployee(Employee user);

	void updateEmployee(Employee user);

	void deleteEmployeeById(Long id);

	void deleteAllEmployees();

	List<Employee> findAllEmployees();

	boolean isEmployeeExist(Employee user);

}
