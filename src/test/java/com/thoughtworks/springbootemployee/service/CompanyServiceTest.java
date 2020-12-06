package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_companies_when_get_all_given_repository_with_all_companies() {
        //given
        List<Company> expectedCompanies = Arrays.asList(new Company());
        when(companyRepository.findAll()).thenReturn(expectedCompanies);

        //when
        List<Company> actualCompanies = companyService.getCompanies();

        //then
        assertEquals(expectedCompanies, actualCompanies);
    }

    @Test
    public void should_return_company_when_save_given_repository_with_company() {
        //given
        Company expectedCompany = new Company();
        when(companyRepository.save(expectedCompany)).thenReturn(expectedCompany);

        //when
        Company actualCompany = companyService.addCompany(expectedCompany);

        //then
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void should_return_company_when_find_company_by_id_given_repository_comapny_id() throws CompanyNotFoundException {
        //given
        Optional<Company> expectedCompany = Optional.of(new Company( "A COM", "Banking"));
        when(companyRepository.findById("123")).thenReturn(expectedCompany);

        //when
        Company actualCompany = companyService.getCompanyById("123");

        //then
        assertEquals(expectedCompany.get(), actualCompany);
    }

    @Test
    public void should_call_delete_company_by_id_when_delete_company_by_id_given_repository_with_company_id() {
        //given
        when(companyRepository.existsById(anyString())).thenReturn(true);

        //when
        companyService.deleteCompanyById("123");

        //then
        verify(companyRepository, times(1)).deleteById("123");
    }

    @Test
    public void should_return_updated_company_when_update_company_by_id_given_repository_with_company_id_and_employee() throws CompanyNotFoundException {
        //given
        Company expectedCompany = new Company("A COM", "Banking");
        when(companyRepository.existsById("1")).thenReturn(true);
        when(companyRepository.save(expectedCompany)).thenReturn(expectedCompany);
        //when
        Company actualCompany = companyService.updateCompany("1", expectedCompany);

        //then
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void should_return_employees_when_get_employees_by_company_id_given_repository_with_company_id() throws CompanyNotFoundException, EmployeeNotFoundException {
        //given
        ArrayList<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee( "Tom", 20, "Male", 200, "123"));
        when(companyRepository.existsById(any())).thenReturn(true);
        when(employeeRepository.findByCompanyId(any())).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = companyService.getEmployeesByCompanyId("123");

        //then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_return_1_company_when_get_companies_with_pagination_given_3_companies_with_pageNumber_is_2_and_pageSize_is_2() {
        //given
        List <Company> companies = new ArrayList<>();
        companies.add(new Company("ACOM", "Banking"));
        Page<Company> expectedCompanies = new PageImpl<>(companies);

        when(companyRepository.findAll((Pageable)any())).thenReturn(expectedCompanies);

        //when
        Page<Company> actualEmployees = companyService.getWithPagination(2, 2);

        //then
        assertEquals(expectedCompanies, actualEmployees);
    }
}
