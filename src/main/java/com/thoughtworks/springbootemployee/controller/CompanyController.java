package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping("/{companyId}")
    public Optional<Company> getCompanyById(@PathVariable String companyId) throws CompanyNotFoundException {
        return companyService.getCompanyById(companyId);
    }

    @PutMapping("/{companyId}")
    public Company updateCompany(@PathVariable String companyId, @RequestBody Company companyUpdate) throws CompanyNotFoundException {
        return companyService.updateCompany(companyId, companyUpdate);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompanyById(@PathVariable String companyId) throws CompanyNotFoundException {
        companyService.deleteCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable String companyId) throws CompanyNotFoundException, EmployeeNotFoundException {
        return companyService.getEmployeesByCompanyId(companyId);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return companyService.getWithPagination(page, pageSize);
    }
}
