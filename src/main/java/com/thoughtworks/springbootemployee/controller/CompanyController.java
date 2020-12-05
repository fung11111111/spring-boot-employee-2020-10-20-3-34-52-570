package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;

    private final EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyService.getCompanies().stream()
                .map(company -> {
                    try {
                        List<Employee> employees = companyService.getEmployeesByCompanyId(company.getCompanyId());
                        return companyMapper.toResponse(company, employees);
                    } catch (EmployeeNotFoundException employeeNotFoundExceptionexception) {
                        employeeNotFoundExceptionexception.getLocalizedMessage();
                    } catch (CompanyNotFoundException companyNotFoundException) {
                        companyNotFoundException.getLocalizedMessage();
                    }
                    return companyMapper.toResponse(company);
                }).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest) throws CompanyNotFoundException, EmployeeNotFoundException {
        Company company = companyService.addCompany(companyMapper.toEntity(companyRequest));
        List<Employee> employees = companyService.getEmployeesByCompanyId(company.getCompanyId());
        return companyMapper.toResponse(company, employees);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable String companyId) throws CompanyNotFoundException, EmployeeNotFoundException {
        Company company = companyService.getCompanyById(companyId);
        List<Employee> employees = companyService.getEmployeesByCompanyId(company.getCompanyId());
        return companyMapper.toResponse(company, employees);
    }

    @PutMapping("/{companyId}")
    public CompanyResponse updateCompany(@PathVariable String companyId, @RequestBody CompanyRequest companyRequest) throws CompanyNotFoundException, EmployeeNotFoundException {
        Company company = companyService.updateCompany(companyId, companyMapper.toEntity(companyRequest));
        List<Employee> employees = companyService.getEmployeesByCompanyId(company.getCompanyId());
        return companyMapper.toResponse(company);
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable String companyId) {
        companyService.deleteCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<EmployeeResponse> getEmployeesByCompanyId(@PathVariable String companyId) throws EmployeeNotFoundException, CompanyNotFoundException {
        return companyService.getEmployeesByCompanyId(companyId).stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<CompanyResponse> getCompaniesByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return companyService.getWithPagination(page, pageSize)
                .map(companyMapper::toResponse);
    }

}
