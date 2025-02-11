package com.IBmirnga.Microservices.repository;

import com.IBmirnga.Microservices.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
