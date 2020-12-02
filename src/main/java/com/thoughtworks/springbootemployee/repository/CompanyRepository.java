package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {

    private List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies() {
        return companies;
    }

    public Company addCompany(Company company) {
        companies.add(company);
        return company;
    }

    public Company getCompanyById(Integer companyId){
        return this.companies.stream()
                .filter(company -> companyId.equals(company.getCompanyId()))
                .findFirst()
                .orElse(null);
    }

    public Company updateCompany(Integer companyId, Company companyUpdate) {
        companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .ifPresent(existingCompany -> {
                    companies.remove(existingCompany);
                    companies.add(companyUpdate);
                });
        return companyUpdate;
    }

    public void deleteCompanyById(Integer companyId) {

    }
}
