package com.thoughtworks.springbootemployee.integration;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_company() throws Exception {
        //given
        Company company = new Company("ACOM", "Banking");
        companyRepository.save(company);
        employeeRepository.save(new Employee("Tom", 18, "Male", 10000, company.getCompanyId()));
        employeeRepository.save(new Employee("Tommy", 20, "Male", 10000, company.getCompanyId()));

        //when
        //then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].companyId").isString())
                .andExpect(jsonPath("$[0].companyName").value("ACOM"))
                .andExpect(jsonPath("$[0].companyType").value("Banking"))
                .andExpect(jsonPath("$[0].employees", hasSize(2)));
    }

    @Test
    public void should_return_company_when_add_company_given_company() throws Exception {
        //given
        String companyJson = "{\n" +
                "   \"companyName\": \"ACOM\",\n" +
                "   \"companyType\": \"Banking\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyId").isString())
                .andExpect(jsonPath("$.companyName").value("ACOM"))
                .andExpect(jsonPath("$.companyType").value("Banking"))
                .andExpect(jsonPath("$.employees").isEmpty());


        List<Company> companies = companyRepository.findAll();
        assertEquals(1, companies.size());
        assertNotNull(companies.get(0).getCompanyId());
        assertEquals("ACOM", companies.get(0).getCompanyName());
        assertEquals("Banking", companies.get(0).getCompanyType());
    }

    @Test
    public void should_return_company_when_find_company_by_id_given_company_id() throws Exception {
        //given
        Company company = new Company("ACOM", "Banking");
        companyRepository.save(company);
        employeeRepository.save(new Employee("Tom", 18, "Male", 10000, company.getCompanyId()));
        employeeRepository.save(new Employee("Tommy", 20, "Male", 10000, company.getCompanyId()));

        //when
        //then
        mockMvc.perform(get("/companies/" + company.getCompanyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isString())
                .andExpect(jsonPath("$.companyName").value("ACOM"))
                .andExpect(jsonPath("$.companyType").value("Banking"))
                .andExpect(jsonPath("$.employees", hasSize(2)));
    }

    @Test
    public void should_return_updated_company_when_update_company_given_company_id_and_company() throws Exception {
        //given
        Company company = new Company("ACOM", "Banking");
        companyRepository.save(company);
        employeeRepository.save(new Employee("Tom", 18, "Male", 10000, company.getCompanyId()));
        employeeRepository.save(new Employee("Tommy", 20, "Male", 10000, company.getCompanyId()));
        String companyJson = "{\n" +
                "   \"companyName\": \"BCOM\",\n" +
                "   \"companyType\": \"IT\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(put("/companies/" + company.getCompanyId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isString())
                .andExpect(jsonPath("$.companyName").value("BCOM"))
                .andExpect(jsonPath("$.companyType").value("IT"))
                .andExpect(jsonPath("$.employees", hasSize(2)));

        List<Company> companies = companyRepository.findAll();
        Assertions.assertEquals(1, companies.size());
        assertNotNull(companies.get(0).getCompanyId());
        assertEquals("BCOM", companies.get(0).getCompanyName());
        assertEquals("IT", companies.get(0).getCompanyType());
    }

    @Test
    public void should_return_2_employees_when_get_employees_by_company_id_given_company_id() throws Exception {
        //given
        Company company = new Company("ACOM", "Banking");
        companyRepository.save(company);
        Employee employee1 = new Employee("Tom", 18, "Male", 10000, company.getCompanyId());
        Employee employee2 = new Employee("Tommy", 20, "Male", 10000, company.getCompanyId());
        Employee retiredEmployee = new Employee("May", 20, "Male", 10000, "5fc89540208fd1789f2aa947");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(retiredEmployee);

        //when
        //then
        mockMvc.perform(get("/companies/" + company.getCompanyId() + "/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[1].id").isString())
                .andExpect(jsonPath("$[1].name").value("Tommy"))
                .andExpect(jsonPath("$[1].age").value(20))
                .andExpect(jsonPath("$[1].gender").value("Male"))
                .andExpect(jsonPath("$[1].salary").value(10000))
                .andExpect(jsonPath("$[1].companyId").isString());
    }

    @Test
    public void should_return_2_companies_when_get_companies_by_pagination_given_3_companies_and_page_number_is_2_and_page_size_is_2() throws Exception {
        //given
        Company company1 = new Company("ACOM", "Banking");
        Company company2 = new Company("BCOM", "IT");
        Company company3 = new Company("CCOM", "Education");
        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
        employeeRepository.save(new Employee("Tom", 18, "Male", 10000, company1.getCompanyId()));
        employeeRepository.save(new Employee("Tom", 18, "Male", 10000, company3.getCompanyId()));
        employeeRepository.save(new Employee("Tommy", 20, "Male", 10000, company3.getCompanyId()));

        //when
        //then
        mockMvc.perform(get("/companies").param("page", String.valueOf(2)).param("pageSize", String.valueOf(2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfElements").value(1))
                .andExpect(jsonPath("$.pageable.pageNumber").value(1))
                .andExpect(jsonPath("$.content[0].companyId").isString())
                .andExpect(jsonPath("$.content[0].companyName").value("CCOM"))
                .andExpect(jsonPath("$.content[0].companyType").value("Education"))
                .andExpect(jsonPath("$.content[0].employees", hasSize(2)));
    }

    @Test
    public void should_delete_company_when_delete_given_company_id() throws Exception {
        //given
        Company company = new Company("ACOM", "Banking");
        companyRepository.save(company);

        //when
        //then
        mockMvc.perform(delete("/companies/" + company.getCompanyId()))
                .andExpect(status().isNoContent());

        List<Company> companies = companyRepository.findAll();
        assertEquals(0, companies.size());
    }

    @Test
    public void should_throw_company_not_found_exception_when_find_company_by_id_given_non_existed_company_id() throws Exception {
        //given
        String non_existedId = ObjectId.get().toString();

        //when
        //then
        mockMvc.perform(get("/companies/" + non_existedId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("Company Not Found."));
    }

    @Test
    public void should_throw_company_not_found_exception_when_update_company_by_id_given_non_existed_company_id_updated_company() throws Exception {
        //given
        String non_existedId = ObjectId.get().toString();
        String companyJson = "{\n" +
                "   \"companyName\": \"BCOM\",\n" +
                "   \"companyType\": \"IT\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(put("/companies/" + non_existedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("Company Not Found."));
    }

    @Test
    public void should_throw_company_not_found_exception_when_delete_company_by_id_given_non_existed_company_id() throws Exception {
        //given
        String non_existedId = ObjectId.get().toString();

        //when
        //then
        mockMvc.perform(get("/companies/" + non_existedId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("Company Not Found."));
    }

    @Test
    public void should_return_400_bad_request_when_find_company_by_id_given_invalid_company_id() throws Exception {
        //given
        String invalid = "1234";

        //when
        //then
        mockMvc.perform(get("/companies/" + invalid))
                .andExpect(status().isBadRequest());
    }

}
