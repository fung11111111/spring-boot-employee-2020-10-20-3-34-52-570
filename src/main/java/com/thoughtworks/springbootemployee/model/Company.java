package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private Integer companyId;
    private String companyName;
    private String companyType;
    private List<Employee> employees;

    public Company() {
    }

    public Company(Integer companyId, String companyName, String companyType, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyType = companyType;
        this.employees = employees;
    }

    public Integer getCompanyId() {
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
