package com.thoughtworks.springbootemployee.dto;

public class CompanyRequest {
    private String companyName;
    private String companyType;

    public CompanyRequest() {
    }

    public CompanyRequest(String companyName, String companyType) {
        this.companyName = companyName;
        this.companyType = companyType;
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
