package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }


    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyService.getCompanies().stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest) {
        Company company = companyService.addCompany(companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable String companyId) throws CompanyNotFoundException {
        return companyMapper.toResponse(companyService.getCompanyById(companyId));
    }

    @PutMapping("/{companyId}")
    public CompanyResponse updateCompany(@PathVariable String companyId, @RequestBody CompanyRequest companyRequest) throws CompanyNotFoundException {
        Company company = companyService.updateCompany(companyId, companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompanyById(@PathVariable String companyId) {
        companyService.deleteCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable String companyId) throws EmployeeNotFoundException, CompanyNotFoundException {
        return companyService.getEmployeesByCompanyId(companyId);
    }


    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return companyService.getWithPagination(page, pageSize);
    }
}
