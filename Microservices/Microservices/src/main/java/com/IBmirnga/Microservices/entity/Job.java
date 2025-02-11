package com.IBmirnga.Microservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {


    @jakarta.persistence.Id
    @GeneratedValue
    private Long Id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @JsonIgnore
    @ManyToOne
    private Company company;
}
