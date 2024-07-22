package com.ata.be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "salary_survey")
public class SalarySurveyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String timestamp;
    public String employer;
    public String location;
    public String jobTitle;
    public String yearsAtEmployer;
    public String yearsOfExperience;
    public String salary;
    public String signingBonus;
    public String annualBonus;
    public String annualStockValue;
    public String gender;
    public String additionalComments;
}
