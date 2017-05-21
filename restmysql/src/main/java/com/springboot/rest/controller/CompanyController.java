package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rest.model.Company;
import com.springboot.rest.service.CompanyService;
import com.springboot.rest.util.ResponseError;


@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService; 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/company/list", method = RequestMethod.GET)
	public ResponseEntity<List<Company>> listAllCompanys() {
		List<Company> Companys = companyService.findAllCompany();
		if (Companys.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Company>>(Companys, HttpStatus.OK);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCompanyById(@PathVariable("id") long id) {
		Company Company = companyService.findById(id);
		if (Company == null) {
			return new ResponseEntity(new ResponseError("Company with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Company>(Company, HttpStatus.OK);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/company/", method = RequestMethod.POST)
	public ResponseEntity<?> createCompany(@RequestBody Company company, UriComponentsBuilder ucBuilder) {

		if (companyService.isCompanyExist(company)) {
			return new ResponseEntity(new ResponseError("Unable to create. A company with name " + 
					company.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		companyService.saveCompany(company);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
	
		Company currentCompany = companyService.findById(id);

		if (currentCompany == null) {
			return new ResponseEntity(new ResponseError("Unable to upate. company with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentCompany.setName(company.getName());
	
		companyService.updateCompany(currentCompany);
		return new ResponseEntity<Company>(currentCompany, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompanyById(@PathVariable("id") long id) {
		
		Company Company = companyService.findById(id);
		if (Company == null) {
			return new ResponseEntity(new ResponseError("Unable to delete. company with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		companyService.deleteCompanyById(id);
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}


	@RequestMapping(value = "/company/", method = RequestMethod.DELETE)
	public ResponseEntity<Company> deleteAllCompanys() {
	
		companyService.deleteAllCompany();
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}
    
    
     
}
