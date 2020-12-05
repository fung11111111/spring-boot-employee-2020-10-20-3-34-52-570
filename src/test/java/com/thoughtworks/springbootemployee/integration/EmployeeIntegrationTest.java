package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.advice.ErrorResponse;
import com.thoughtworks.springbootemployee.advice.GlobalControllerAdvice;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_employees() throws Exception {
        //given
        Employee employee = new Employee("May", 18, "Female", 12000, "123");
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("May"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].salary").value(12000))
                .andExpect(jsonPath("$[0].companyId").value("123"));
    }

    @Test
    public void should_return_employee_when_add_employee_given_employee() throws Exception {
        //given
        String employeeJson = "{\n" +
                "   \"name\": \"Tom\",\n" +
                "   \"age\": 10,\n" +
                "   \"gender\": \"Male\",\n" +
                "   \"salary\": 10000,\n" +
                "   \"companyId\": \"123\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Tom"))
                .andExpect(jsonPath("$.age").value(10))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(10000))
                .andExpect(jsonPath("$.companyId").value("123"));

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1, employees.size());
        assertEquals("Tom", employees.get(0).getName());
        assertEquals(10, employees.get(0).getAge());
        assertEquals("Male", employees.get(0).getGender());
        assertEquals(10000, employees.get(0).getSalary());
        assertEquals("123", employees.get(0).getCompanyId());
    }

    @Test
    public void should_return_employee_when_find_employee_by_id_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee("May", 18, "Female", 12000, "123");
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("May"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("Female"))
                .andExpect(jsonPath("$.salary").value(12000))
                .andExpect(jsonPath("$.companyId").value("123"));
    }

    @Test
    public void should_return_all_male_employees_when_get_by_gender_given_male() throws Exception {
        //given
        Employee employee1 = new Employee("Tom", 18, "Male", 10000, "123");
        Employee employee2 = new Employee("May", 18, "Female", 10000, "123");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        //when
        //then
        mockMvc.perform(get("/employees").param("gender", "Male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(10000))
                .andExpect(jsonPath("$[0].companyId").value("123"));

        List<Employee> actualEmployees = employeeRepository.findByGender("Male");
        TestCase.assertEquals(1, actualEmployees.size());
    }

    @Test
    public void should_return_updated_employee_when_update_employee_given_employee_id_and_employee() throws Exception {
        //given
        Employee employee = new Employee("Tom", 18, "Male", 200, "1234");
        employeeRepository.save(employee);
        String employeeUpdatedJson = "\n" +
                "{\n" +
                "   \"name\": \"Tom\",\n" +
                "   \"age\": 19,\n" +
                "   \"gender\": \"Male\",\n" +
                "   \"salary\": 10000,\n" +
                "   \"companyId\": \"1234\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(put("/employees/" + employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeUpdatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tom"))
                .andExpect(jsonPath("$.age").value(19))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(10000))
                .andExpect(jsonPath("$.companyId").value("1234"));

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1, employees.size());
        assertEquals("Tom", employees.get(0).getName());
        assertEquals(19, employees.get(0).getAge());
        assertEquals("Male", employees.get(0).getGender());
        assertEquals(10000, employees.get(0).getSalary());
        assertEquals("1234", employees.get(0).getCompanyId());
    }

    @Test
    public void should_return_2_employees_when_get_employee_by_pagination_given_3_employees_and_page_number_is_2_and_page_size_is_2() throws Exception {
        //given
        Employee employee1 = new Employee("Tom", 18, "Male", 10000, "123");
        Employee employee2 = new Employee("May", 18, "Female", 10000, "123");
        Employee employee3 = new Employee("Marry", 18, "Female", 10000, "123");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        //when
        //then
        mockMvc.perform(get("/employees").param("page", String.valueOf(2)).param("pageSize", String.valueOf(2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Marry"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].salary").value(10000))
                .andExpect(jsonPath("$[0].companyId").value("123"));
    }

    @Test
    public void should_delete_employee_when_delete_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee("Tom", 18, "Male", 10000, "123");
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(delete("/employees/" + employee.getId()))
                .andExpect(status().isNoContent());

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(0, employees.size());
    }

    @Test
    public void should_throw_employee_not_found_exception_when_find_employee_by_id_given_non_existed_employee_id() throws Exception {
        //given
        String non_existedId = "5fc89540208fd1789f2aa947";

        //when
        //then
        mockMvc.perform(get("/employees/" + non_existedId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("Employee Not Found."));
    }

    @Test
    public void should_return_400_bad_request_when_find_employee_by_id_given_invalid_employee_id() throws Exception {
        //given
        String invalid = "1234";

        //when
        //then
        mockMvc.perform(get("/employees/" + invalid))
                .andExpect(status().isBadRequest());
    }



}
