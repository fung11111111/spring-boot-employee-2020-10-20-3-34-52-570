package com.thoughtworks.springbootemployee.integration;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class CompanyIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_company() throws Exception {
        //given
        Company company = new Company("ACOM", "Banking");
        companyRepository.save(company);

        //when
        //then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyId").isString())
                .andExpect(jsonPath("$[0].companyName").value("ACom"))
                .andExpect(jsonPath("$[0].companyType").value("Banking"));
    }

    @Test
    public void should_return_company_when_add_company_given_company() throws Exception {
        //given
        String employeeJson = "{\n" +
                "   \"companyName\": \"ACOM\",\n" +
                "   \"companyType\": \"Banking\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isString())
                .andExpect(jsonPath("$.companyName").value("ACOM"))
                .andExpect(jsonPath("$.companyType").value("Banking"));


        List<Company> companies = companyRepository.findAll();
        assertEquals(1, companies.size());
        assertEquals("ACOM", companies.get(0).getCompanyName());
        assertEquals("Banking", companies.get(0).getCompanyType());
    }

    @Test
    public void should_return_company_when_find_company_by_id_given_company_id() throws Exception {
        //given
        Company company = new Company("ACOM", "Banking");
        companyRepository.save(company);

        //when
        //then
        mockMvc.perform(get("/companies/" + company.getCompanyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isString())
                .andExpect(jsonPath("$.companyName").value("ACOM"))
                .andExpect(jsonPath("$.companyType").value("Banking"));
    }
//
//    @Test
//    public void should_return_all_male_employees_when_get_by_gender_given_male() throws Exception {
//        //given
//        Employee employee1 = new Employee("Tom", 18, "Male", 10000, "123");
//        Employee employee2 = new Employee("May", 18, "Female", 10000, "123");
//        employeeRepository.save(employee1);
//        employeeRepository.save(employee2);
//        //when
//        //then
//        mockMvc.perform(get("/employees").param("gender", "Male"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").isString())
//                .andExpect(jsonPath("$[0].name").value("Tom"))
//                .andExpect(jsonPath("$[0].age").value(18))
//                .andExpect(jsonPath("$[0].gender").value("Male"))
//                .andExpect(jsonPath("$[0].salary").value(10000))
//                .andExpect(jsonPath("$[0].companyId").value("123"));
//
//        List<Employee> actualEmployees = employeeRepository.findByGender("Male");
//        TestCase.assertEquals(1, actualEmployees.size());
//    }
//
//    @Test
//    public void should_return_updated_employee_when_update_employee_given_employee_id_and_employee() throws Exception {
//        //given
//        Employee employee = new Employee("Tom", 18, "Male", 200, "1234");
//        employeeRepository.save(employee);
//        String employeeUpdatedJson = "\n" +
//                "{\n" +
//                "   \"name\": \"Tom\",\n" +
//                "   \"age\": 19,\n" +
//                "   \"gender\": \"Male\",\n" +
//                "   \"salary\": 10000,\n" +
//                "   \"companyId\": \"1234\"\n" +
//                "}";
//
//        //when
//        //then
//        mockMvc.perform(put("/employees/" + employee.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(employeeUpdatedJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").isString())
//                .andExpect(jsonPath("$.name").value("Tom"))
//                .andExpect(jsonPath("$.age").value(19))
//                .andExpect(jsonPath("$.gender").value("Male"))
//                .andExpect(jsonPath("$.salary").value(10000))
//                .andExpect(jsonPath("$.companyId").value("1234"));
//
//    }
//
    @Test
    public void should_return_2_companies_when_get_companies_by_pagination_given_3_companies_and_page_number_is_2_and_page_size_is_2() throws Exception {
        //given
        Company company1 = new Company("ACOM", "Banking");
        Company company2 = new Company("BCOM", "IT");
        Company company3 = new Company("CCOM", "Education");
        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
        //when
        //then
        mockMvc.perform(get("/companies").param("page", String.valueOf(2)).param("pageSize", String.valueOf(2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyId").isString())
                .andExpect(jsonPath("$[0].companyName").value("CCOM"))
                .andExpect(jsonPath("$[0].companyType").value("Education"));
    }
//
//    @Test
//    public void should_delete_employee_when_delete_given_employee_id() throws Exception {
//        //given
//        Employee employee = new Employee("Tom", 18, "Male", 10000, "123");
//        employeeRepository.save(employee);
//
//        //when
//        //then
//        mockMvc.perform(delete("/employees/" + employee.getId()))
//                .andExpect(status().isOk());
//
//        List<Employee> employees = employeeRepository.findAll();
//        assertEquals(0, employees.size());
//    }
}
