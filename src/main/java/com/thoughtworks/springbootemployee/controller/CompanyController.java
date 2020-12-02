package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private List<Company> companies = new ArrayList<>();

    @Autowired
    private CompanyService companyService;
    @GetMapping
    public List<Company> getEmployeesList() {
        return companyService.getCompanies();
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

}
