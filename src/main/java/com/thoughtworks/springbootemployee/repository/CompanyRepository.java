package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Company getCompanyById(Integer companyId) throws CompanyNotFoundException {
        try{
            return this.companies.stream()
                    .filter(company -> companyId.equals(company.getCompanyId()))
                    .findFirst()
                    .orElse(null);
        }catch (Exception exception){
            throw  new CompanyNotFoundException();
        }

    }

    public Company updateCompany(Integer companyId, Company companyUpdate) throws CompanyNotFoundException {
        Company existingCompany = getCompanyById(companyId);
        if(existingCompany != null){
            companies.remove(existingCompany);
            companies.add(companyUpdate);
            return companyUpdate;
        }
        throw new CompanyNotFoundException();
    }

    public void deleteCompanyById(Integer companyId) throws CompanyNotFoundException {
        Company deleteCompany = getCompanyById(companyId);
        if (deleteCompany != null){
            companies.remove(deleteCompany);
        }
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) throws CompanyNotFoundException {
        Company existingCompany = getCompanyById(companyId);
        if(existingCompany != null){
            return existingCompany.getEmployees();
        }
        throw new CompanyNotFoundException();
    }

    public List<Company> getWithPagination(Integer page, Integer pageSize) {
        int pageToSkip = page - 1;
        return companies.stream()
                .skip(pageToSkip * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
