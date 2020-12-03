package com.thoughtworks.springbootemployee.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String companyId;
    private String companyName;
    private String companyType;

    public Company() {
    }

    public Company(String companyId, String companyName, String companyType) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyType = companyType;
    }

    public Company(String companyName, String companyType) {
        this.companyName = companyName;
        this.companyType = companyType;
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



}
