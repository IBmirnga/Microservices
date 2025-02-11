package com.IBMirnga.companyms.company;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    List<Company> findAll();

    void addCompany(Company company);

    Company findCompanyById(Long id);

    boolean deleteCompanyById(Long id);

    boolean updateCompany(Long id, Company updatedCompany);

}
