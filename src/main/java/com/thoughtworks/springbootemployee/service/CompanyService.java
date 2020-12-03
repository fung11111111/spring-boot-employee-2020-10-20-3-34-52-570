package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> getCompanyById(String companyId){
        return companyRepository.findById(companyId);
    }
//
//    public Company updateCompany(String companyId, Company companyUpdate) throws CompanyNotFoundException {
//        return companyRepositoryOld.updateCompany(companyId, companyUpdate);
//    }
//
//    public void deleteCompanyById(String companyId) throws CompanyNotFoundException {
//        companyRepositoryOld.deleteCompanyById(companyId);
//    }
//
//    public List<Employee> getEmployeesByCompanyId(String companyId) throws CompanyNotFoundException {
//        return companyRepositoryOld.getEmployeesByCompanyId(companyId);
//    }
//
//    public List<Company> getWithPagination(Integer page, Integer pageSize) {
//        return companyRepositoryOld.getWithPagination(page, pageSize);
//    }
}
