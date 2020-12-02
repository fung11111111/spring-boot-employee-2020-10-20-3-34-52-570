package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {

    @Test
    public void should_return_all_companies_when_get_companies_given_repository_with_all_companies() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        List<Company> expectedCompanies = Arrays.asList(new Company());
        when(companyRepository.getCompanies()).thenReturn(expectedCompanies);

        //when
        List<Company> actualEmployees = companyService.getCompanies();

        //then
        assertEquals(expectedCompanies, actualEmployees);
    }

    @Test
    public void should_add_company_when_add_company_given_repository_with_company() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        Company expectedCompany = new Company();
        when(companyRepository.addCompany(expectedCompany)).thenReturn(expectedCompany);

        //when
        Company actualCompany = companyService.addCompany(expectedCompany);

        //then
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void should_company_when_get_company_by_id_given_repository_with_company_id() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        Company expectedCompany = new Company(123,"Hi","Banking",new ArrayList<>());
        when(companyRepository.getCompanyById(123)).thenReturn(expectedCompany);

        //when
        Company actualCompany = companyService.getCompanyById(123);

        //then
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void should_return_updated_company_when_update_company_given_repository_with_company_and_company_id() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        Company expectedCompany = new Company(123,"Hello","Banking",new ArrayList<>());
        companyRepository.addCompany(new Company(123,"HI","Banking",new ArrayList<>()));
        when(companyRepository.updateCompany(123, expectedCompany)).thenReturn(expectedCompany);

        //when
        Company actualCompany = companyService.updateCompany(123, expectedCompany);

        //then
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void should_call_deleteCompanyByID_when_service_delete_company_by_id_given_repository_with_company_id() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);

        //when
        companyService.deleteCompanyById(11);

        //then
        verify(companyRepository, times(1)).deleteCompanyById(1);
    }



}
