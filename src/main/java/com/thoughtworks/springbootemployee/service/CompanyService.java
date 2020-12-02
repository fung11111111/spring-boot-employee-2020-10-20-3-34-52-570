package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    public Company addCompany(Company company) {
        return companyRepository.addCompany(company);
    }

    public Company getCompanyById(Integer companyId) throws EmployeeNotFoundException {
        return companyRepository.getCompanyById(companyId);
    }

    public Company updateCompany(Integer companyId, Company companyUpdate) throws EmployeeNotFoundException {
        return companyRepository.updateCompany(companyId, companyUpdate);
    }

    public void deleteCompanyById(Integer companyId) throws EmployeeNotFoundException {
        companyRepository.deleteCompanyById(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        return companyRepository.getEmployeesByCompanyId(companyId);
    }

    public List<Company> getWithPagination(Integer page, Integer pageSize) {
        return companyRepository.getWithPagination(page, pageSize);
    }
}
