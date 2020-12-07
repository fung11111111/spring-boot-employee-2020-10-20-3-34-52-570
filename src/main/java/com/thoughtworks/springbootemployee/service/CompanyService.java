package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeService employeeService;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(String companyId) throws CompanyNotFoundException {
        if (companyRepository.findById(companyId).isPresent()) {
            return companyRepository.findById(companyId).get();
        }
        throw new CompanyNotFoundException();
    }

    public Company updateCompany(String companyId, Company companyUpdate) throws CompanyNotFoundException {
        if (companyRepository.existsById(companyId)) {
            companyUpdate.setCompanyId(companyId);
            return companyRepository.save(companyUpdate);
        }
        throw new CompanyNotFoundException();
    }

    public void deleteCompanyById(String companyId) throws CompanyNotFoundException {
        if (!companyRepository.existsById(companyId)) {
            throw new CompanyNotFoundException();
        }
        companyRepository.deleteById(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(String companyId) throws CompanyNotFoundException {
        if (!companyRepository.existsById(companyId)) {
            throw new CompanyNotFoundException();
        }
        return employeeService.getEmployeeByCompanyId(companyId);
    }

    public Page<Company> getWithPagination(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return companyRepository.findAll(pageable);
    }
}
