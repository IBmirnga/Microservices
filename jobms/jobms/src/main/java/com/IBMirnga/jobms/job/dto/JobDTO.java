package com.IBMirnga.jobms.job.dto;

import com.IBMirnga.jobms.job.external.Company;
import com.IBMirnga.jobms.job.external.Review;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class JobDTO {

    private Long Id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> review;

}
