package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

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
}
