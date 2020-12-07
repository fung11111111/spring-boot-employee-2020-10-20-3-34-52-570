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

        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);

        return companyResponse;
    }


    public CompanyResponse toResponse(Company company, List<Employee> employees) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCompanyId(company.getCompanyId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setCompanyType(company.getCompanyType());
        companyResponse.setEmployees(employees);

        return companyResponse;
    }
}
