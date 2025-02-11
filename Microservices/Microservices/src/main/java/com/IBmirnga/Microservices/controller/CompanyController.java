package com.IBmirnga.Microservices.controller;


import com.IBmirnga.Microservices.entity.Company;
import com.IBmirnga.Microservices.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/company")
public class CompanyController {

    @Autowired
    private CompanyService CompanyService;

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        CompanyService.addCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(CompanyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id) {

        Company company = CompanyService.findCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = CompanyService.deleteCompanyById(id);
        if (deleted)
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        boolean updated = CompanyService.updateCompany(id, updatedCompany);
        if (updated)
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
