package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rest.model.Employee;
import com.springboot.rest.service.EmployeeService;
import com.springboot.rest.util.ResponseError;

@RestController
public class EmployeeController {


	@Autowired
	EmployeeService employeeService; 
	
	
	@RequestMapping(value="/employee", method=RequestMethod.GET)
    public ModelAndView employee() {
		List<Employee> employees = employeeService.findAllEmployees();
		ModelAndView model = new ModelAndView("employee");
		model.addObject("list", employees);
        return model;
    }
	
	@RequestMapping(value = "/employee/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		  ModelAndView model = new ModelAndView("edit");
		  Employee employee = employeeService.findById(id);
	      model.addObject("employee", employee);
	      return model;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/employee/list", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listAllEmployees() {
		List<Employee> employees = employeeService.findAllEmployees();
		if (employees.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/employee/edit/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id) {
			Employee Employee = employeeService.findById(id);
		if (Employee == null) {
			return new ResponseEntity(new ResponseError("Employee with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Employee>(Employee, HttpStatus.OK);
	}
*/

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {

		if (employeeService.isEmployeeExist(employee)) {
			return new ResponseEntity(new ResponseError("Unable to create. A employee with name " + 
			employee.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		employeeService.saveEmployee(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
	
		Employee currentEmployee = employeeService.findById(id);

		if (currentEmployee == null) {
			return new ResponseEntity(new ResponseError("Unable to upate. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentEmployee.setName(employee.getName());
		currentEmployee.setSurname(employee.getSurname());
		employeeService.updateEmployee(currentEmployee);
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") long id) {
		
		Employee Employee = employeeService.findById(id);
		if (Employee == null) {
			return new ResponseEntity(new ResponseError("Unable to delete. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}


	@RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteAllEmployees() {
	
		employeeService.deleteAllEmployees();
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
		
}
