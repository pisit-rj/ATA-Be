package com.ata.be.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JobDataRequest implements Serializable {
    private List<String> field;
    private String gender;
    private String jobTitle;
    private String salary;
    private SalaryOperatorEnum salaryOperator;
}
