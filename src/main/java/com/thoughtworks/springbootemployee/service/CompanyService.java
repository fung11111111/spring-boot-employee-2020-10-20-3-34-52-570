package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(String companyId) throws CompanyNotFoundException{
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

    public void deleteCompanyById(String companyId) {
        companyRepository.deleteById(companyId);
    }

    //add company not found exception
    // user service at same layer
    public List<Employee> getEmployeesByCompanyId(String companyId) throws EmployeeNotFoundException {
        if (employeeRepository.findByCompanyId(companyId) != null) {
            return employeeRepository.findByCompanyId(companyId);
        }
        throw new EmployeeNotFoundException();
    }

    public List<Company> getWithPagination(Integer page, Integer pageSize) {
        int pageToSkip = page - 1;
        return companyRepository.findAll()
                .stream()
                .skip(pageToSkip * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
