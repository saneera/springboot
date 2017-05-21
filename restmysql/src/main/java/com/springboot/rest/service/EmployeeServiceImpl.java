package com.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.rest.model.Employee;
import com.springboot.rest.repository.EmployeeRepository;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	
	@Autowired
	private EmployeeRepository employeRepository;

	public Employee findById(Long id) {
		return employeRepository.findOne(id);
	}

	public Employee findByName(String name) {
		return employeRepository.findByName(name);
	}

	public void saveEmployee(Employee employee) {
		employeRepository.save(employee);
	}

	public void updateEmployee(Employee employee){
		saveEmployee(employee);
	}

	public void deleteEmployeeById(Long id){
		employeRepository.delete(id);
	}

	public void deleteAllEmployees(){
		employeRepository.deleteAll();
	}

	public List<Employee> findAllEmployees(){
		return employeRepository.findAll();
	}

	public boolean isEmployeeExist(Employee employee) {
		return findByName(employee.getName()) != null;
	}			
}
