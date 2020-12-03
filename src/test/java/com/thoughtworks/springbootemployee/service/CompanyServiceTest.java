package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
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

    //
//
//    @Test
//    public void should_return_all_companies_when_get_companies_given_repository_with_all_companies() {
//        //given
//        List<Company> expectedCompanies = Arrays.asList(new Company());
//        when(companyRepository.getCompanies()).thenReturn(expectedCompanies);
//
//        //when
//        List<Company> actualEmployees = companyService.getCompanies();
//
//        //then
//        assertEquals(expectedCompanies, actualEmployees);
//    }
//
//    @Test
//    public void should_add_company_when_add_company_given_repository_with_company() {
//        //given
//        Company expectedCompany = new Company();
//        when(companyRepository.addCompany(expectedCompany)).thenReturn(expectedCompany);
//
//        //when
//        Company actualCompany = companyService.addCompany(expectedCompany);
//
//        //then
//        assertEquals(expectedCompany, actualCompany);
//    }
//
//    @Test
//    public void should_company_when_get_company_by_id_given_repository_with_company_id() throws CompanyNotFoundException {
//        //given
//        Company expectedCompany = new Company(123, "Hi", "Banking", new ArrayList<>());
//        when(companyRepository.getCompanyById(123)).thenReturn(expectedCompany);
//
//        //when
//        Company actualCompany = companyService.getCompanyById(123);
//
//        //then
//        assertEquals(expectedCompany, actualCompany);
//    }
//
//    @Test
//    public void should_return_updated_company_when_update_company_given_repository_with_company_and_company_id() throws CompanyNotFoundException {
//        //given
//        Company expectedCompany = new Company(123, "Hello", "Banking", new ArrayList<>());
//        when(companyRepository.updateCompany(123, expectedCompany)).thenReturn(expectedCompany);
//
//        //when
//        Company actualCompany = companyService.updateCompany(123, expectedCompany);
//
//        //then
//        //check value with id and value
//        assertEquals(expectedCompany, actualCompany);
//    }
//
//    @Test
//    public void should_call_deleteCompanyByID_when_service_delete_company_by_id_given_repository_with_company_id() throws CompanyNotFoundException {
//        //when
//        companyService.deleteCompanyById(1);
//
//        //then
//        verify(companyRepository, times(1)).deleteCompanyById(1);
//    }
//
//    @Test
//    public void should_return_employees_when_get_employees_by_company_id_given_repository_with_company_id() throws CompanyNotFoundException {
//        //given
//        //shd only add employee id to company
//        ArrayList<Employee> expectedEmployees = new ArrayList<>();
//        expectedEmployees.add(new Employee());
//        expectedEmployees.add(new Employee());
//        expectedEmployees.add(new Employee());
//        when(companyRepository.getEmployeesByCompanyId(123)).thenReturn(expectedEmployees);
//
//        //when
//        List<Employee> actualEmployees = companyService.getEmployeesByCompanyId(123);
//
//        //then
//        assertEquals(expectedEmployees, actualEmployees);
//    }
//
//    @Test
//    void should_return_2_companies_when_get_companies_with_pagination_given_companies_more_than_2_with_pageNumber_is_1_and_pageSize_is_2() {
//        //given
//        List<Company> expectedCompanies = new ArrayList<>();
//        expectedCompanies.add(new Company(1, "A COM", "Banking", new ArrayList<>()));
//        expectedCompanies.add(new Company(2, "B COM", "Banking", new ArrayList<>()));
//        when(companyRepository.getWithPagination(1, 2)).thenReturn(expectedCompanies);
//
//        //when
//        List<Company> actualCompanies = companyService.getWithPagination(1, 2);
//
//        //then
//        assertEquals(2, actualCompanies.size());
//    }
//
//    @Test
//    void should_throw_company_not_found_exception_when_get_company_by_id_given_not_exist_id() throws CompanyNotFoundException {
//        //given
//        when(companyRepository.getCompanyById(20)).thenThrow(new CompanyNotFoundException());
//
//        //when
//        CompanyNotFoundException companyNotFoundException = assertThrows(CompanyNotFoundException.class, () -> {
//            companyService.getCompanyById(20);
//        });
//
//        //then
//        assertEquals("Company Not Found.", companyNotFoundException.getLocalizedMessage());
//    }

    //star here using spring interface
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
    public void should_return_company_when_find_company_by_id_given_repository_comapny_id() {
        //given
        Optional<Company> expectedCompany = Optional.of(new Company("123", "A COM", "Banking", new ArrayList<>()));
        when(companyRepository.findById("123")).thenReturn(expectedCompany);

        //when
        Optional<Company> actualCompany = companyService.getCompanyById("123");

        //then
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void should_call_delete_company_by_id_when_delete_company_by_id_given_repository_with_company_id() {
        //when
        companyService.deleteCompanyById("1");

        //then
        verify(companyRepository, times(1)).deleteById("1");
    }

    @Test
    public void should_return_updated_company_when_update_company_by_id_given_repository_with_company_id_and_employee() throws CompanyNotFoundException {
        //given
        Company expectedCompany = new Company("1", "A COM", "Banking", new ArrayList<>());
        when(companyRepository.existsById("1")).thenReturn(true);
        when(companyRepository.save(expectedCompany)).thenReturn(expectedCompany);
        //when
        Company actualCompany = companyService.updateCompany("1", expectedCompany);

        //then
        assertEquals(expectedCompany, actualCompany);
    }

//    @Test
//    public void should_return_employees_when_get_employees_by_company_id_given_repository_with_company_id() throws CompanyNotFoundException {
//        //given
//        Company expectedCompany = new Company("1", "A COM", "Banking", new ArrayList<>());
//        ArrayList<Employee> employees = new ArrayList<>();
//        employees.add(new Employee("1", "Tom", 20, "Male", 200, "1"));
//        employees.add(new Employee("1", "Tommy", 20, "Male", 200, "1"));
//        when(employeeRepository.findByCompanyId("1")).thenReturn(employees);
//
//        //when
//        List<Employee> actualEmployees = companyService.getEmployeesByCompanyId("1");
//
//        //then
//        assertEquals(expectedCompany, actualEmployees);
//    }

    @Test
    public void should_return_2_companies_when_get_companies_with_pagination_given_companies_more_than_2_with_pageNumber_is_1_and_pageSize_is_2() {
        //given
        ArrayList<Company> expectedCompanies = new ArrayList<>();
        expectedCompanies.add(new Company("123", "A COM", "BANKING", new ArrayList<>()));
        expectedCompanies.add(new Company("124", "B COM", "BANKING", new ArrayList<>()));
        when(companyRepository.findAll()).thenReturn(expectedCompanies);

        //when
        List<Company> actualCompanies = companyService.getWithPagination(1,2);
        assertEquals(expectedCompanies, actualCompanies);
    }
}