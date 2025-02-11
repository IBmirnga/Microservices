package com.IBmirnga.Microservices.service;

import com.IBmirnga.Microservices.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    void addCompany(Company company);

    Company findCompanyById(Long id);

    boolean deleteCompanyById(Long id);

    boolean updateCompany(Long id, Company updatedCompany);

}
