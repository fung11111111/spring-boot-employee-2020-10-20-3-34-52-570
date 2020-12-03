package com.thoughtworks.springbootemployee.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Company {
    @MongoId
    private String companyId;
    private String companyName;
    private String companyType;
    private List<Employee> employees;

    public Company() {
    }

    public Company(String companyId, String companyName, String companyType, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyType = companyType;
        this.employees = employees;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public List<Employee> getEmployees() {
        return employees;
    }


}
