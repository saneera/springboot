package com.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.rest.model.Company;
import com.springboot.rest.repository.CompanyRepository;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService{

	
	@Autowired
	private CompanyRepository companyRepository;

	public Company findById(Long id) {
		return companyRepository.findOne(id);
	}

	public Company findByName(String name) {
		return companyRepository.findByName(name);
	}

	public void saveCompany(Company employee) {
		companyRepository.save(employee);
	}

	public void updateCompany(Company employee){
		saveCompany(employee);
	}

	public void deleteCompanyById(Long id){
		companyRepository.delete(id);
	}

	public void deleteAllCompany(){
		companyRepository.deleteAll();
	}

	public List<Company> findAllCompany(){
		return companyRepository.findAll();
	}

	public boolean isCompanyExist(Company company) {
		return findByName(company.getName()) != null;
	}			
}
