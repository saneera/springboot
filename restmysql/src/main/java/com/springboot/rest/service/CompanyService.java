package com.springboot.rest.service;

import java.util.List;

import com.springboot.rest.model.Company;

public interface CompanyService {

	Company findById(Long id);

	Company findByName(String name);

	void saveCompany(Company company);

	void updateCompany(Company company);

	void deleteCompanyById(Long id);

	void deleteAllCompany();

	List<Company> findAllCompany();

	boolean isCompanyExist(Company user);

}
