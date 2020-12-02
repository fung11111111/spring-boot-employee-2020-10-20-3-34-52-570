package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        List<Company> expectedCompanies = Arrays.asList();
        when(companyRepository.getCompanies()).thenReturn(expectedCompanies);

        //when
        List<Company> actualEmployees = companyService.getCompanies();

        //then
        assertEquals(expectedCompanies, actualEmployees);
    }
}
