package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class CompanyResponse {
    private String companyName;
    private String companyType;
    private List<Employee> employees;

    public CompanyResponse() {
    }

    public CompanyResponse(String companyName, String companyType, List<Employee> employees) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

}
