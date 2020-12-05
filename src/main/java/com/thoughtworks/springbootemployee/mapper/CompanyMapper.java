package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {
    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
//
//        company.setCompanyName(companyRequest.getCompanyName());
//        company.setCompanyType(companyRequest.getCompanyType());

        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
//        companyResponse.setCompanyName(company.getCompanyName());
//        companyResponse.setCompanyType(company.getCompanyType());
//        companyResponse.setEmployees(new ArrayList<>());

        return companyResponse;
    }


    public CompanyResponse toResponse(Company company, List<Employee> employees) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setCompanyType(company.getCompanyType());
        companyResponse.setEmployees(employees);

        return companyResponse;
    }
}
