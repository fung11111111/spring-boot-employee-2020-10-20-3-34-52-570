package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

// add company id
public class CompanyResponse {
    private String companyId;
    private String companyName;
    private String companyType;
    private List<Employee> employees;

    public CompanyResponse() {
    }

    public CompanyResponse(String companyId, String companyName, String companyType, List<Employee> employees) {
        this.companyId = companyId;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
