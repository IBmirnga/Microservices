package com.IBmirnga.Microservices.repository;

import com.IBmirnga.Microservices.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
