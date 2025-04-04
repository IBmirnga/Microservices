package com.IBMirnga.companyms.company.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ReviewMessage {
    
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;

}
