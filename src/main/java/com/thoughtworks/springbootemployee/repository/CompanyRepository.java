package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
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

    public Company getCompanyById(Integer companyId) throws EmployeeNotFoundException{
        try{
            return this.companies.stream()
                    .filter(company -> companyId.equals(company.getCompanyId()))
                    .findFirst()
                    .orElse(null);
        }catch (Exception exception){
            throw  new EmployeeNotFoundException();
        }

    }

    public Company updateCompany(Integer companyId, Company companyUpdate) throws EmployeeNotFoundException {
        Company existingCompany = getCompanyById(companyId);
        if(existingCompany != null){
            companies.remove(existingCompany);
            companies.add(companyUpdate);
            return companyUpdate;
        }
        throw new EmployeeNotFoundException();
    }

    public void deleteCompanyById(Integer companyId) throws EmployeeNotFoundException {
        Company deleteCompany = getCompanyById(companyId);
        if (deleteCompany != null){
            companies.remove(deleteCompany);
        }
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .get().getEmployees();

    }

    public List<Company> getWithPagination(Integer page, Integer pageSize) {
        int pageToSkip = page - 1;
        return companies.stream()
                .skip(pageToSkip * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
