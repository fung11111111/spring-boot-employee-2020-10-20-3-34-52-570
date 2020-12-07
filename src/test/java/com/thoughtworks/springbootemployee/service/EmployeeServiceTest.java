package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employee_when_find_all_given_repository_interface_with_all_employee() {
        //given
        List<Employee> expectedEmployees = Arrays.asList(new Employee("Tom", 20, "Male", 20000, "123"));
        when(employeeRepository.findAll()).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getEmployeesList();

        //then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_return_employee_when_find_by_id_given_repository_interface_with_all_employee() throws EmployeeNotFoundException {
        //given
        Optional<Employee> expectedEmployee = Optional.of(new Employee("Tom", 20, "Male", 20000, "123"));
        when(employeeRepository.findById("1")).thenReturn(expectedEmployee);

        //when
        Employee actualEmployees = employeeService.getEmployeeByID("1");

        //then
        assertEquals(expectedEmployee.get(), actualEmployees);
    }

    @Test
    public void should_return_employee_when_save_given_repository_interface_with_employee() throws EmployeeNotFoundException {
        //given
        Employee expectedEmployee = new Employee("Tom", 20, "Male", 20000, "123");
        when(employeeRepository.save(expectedEmployee)).thenReturn(expectedEmployee);

        //when
        Employee actualEmployees = employeeService.addEmployee(expectedEmployee);

        //then
        assertEquals(expectedEmployee, actualEmployees);
    }

    @Test
    public void should_return_employee_when_get_employee_by_gender_given_repository_interface_with_gender() throws EmployeeNotFoundException {
        //given
        ArrayList<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee("Tom", 20, "Male", 20000, "123"));
        expectedEmployees.add(new Employee("Tommy", 20, "Male", 20000, "123"));

        when(employeeRepository.findByGender("Male")).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getEmployeesByGender("Male");

        //then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_call_delete_employee_by_id_when_delete_employee_by_id_given_repository_with_employee_id() throws EmployeeNotFoundException {
        //given
        when(employeeRepository.existsById(anyString())).thenReturn(true);

        //when
        employeeService.deleteEmployeeByID("1");

        //then
        verify(employeeRepository, times(1)).deleteById("1");
    }

    @Test
    public void should_return_updated_employee_when_update_employee_by_id_given_repository_with_employee_id_and_employee() throws EmployeeNotFoundException {
        //given
        Employee expectedEmployee = new Employee("Tom", 20, "Male", 20000, "123");
        when(employeeRepository.existsById("1")).thenReturn(true);
        when(employeeRepository.save(expectedEmployee)).thenReturn(expectedEmployee);
        //when
        Employee actualEmployee = employeeService.updateEmployee("1", expectedEmployee);

        //then
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void should_return_1_employees_when_get_employees_with_pagination_given_3_employees_with_pageNumber_is_2_and_pageSize_is_2() {
        //given
        List <Employee> employees = new ArrayList<>();
        employees.add(new Employee("Tom", 20, "Male", 20000, "123"));
        Page<Employee> expectedEmployees = new PageImpl<>(employees);

        when(employeeRepository.findAll((Pageable)any())).thenReturn(expectedEmployees);

        //when
        Page<Employee> actualEmployees = employeeService.getWithPagination(2, 2);

        //then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_return_employees_when_get_employee_by_company_id_given_repository_company_id() throws EmployeeNotFoundException {
        //given
        ArrayList<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee("Tom", 20, "Male", 20000, "123"));
        expectedEmployees.add(new Employee("Tommy", 20, "Male", 20000, "123"));

        when(employeeRepository.findByCompanyId("123")).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getEmployeeByCompanyId("123");

        //then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_throw_employee_not_found_exception_when_get_employee_by_id_given_repository_employee_id() {
        //given
        when(employeeRepository.findById("1")).thenReturn(Optional.empty());

        //when
        EmployeeNotFoundException employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> {
           employeeService.getEmployeeByID("1");
        });

        //then
        assertEquals("Employee Not Found.", employeeNotFoundException.getLocalizedMessage());
    }

    @Test
    public void should_throw_employee_not_found_exception_when_update_employee_by_id_given_repository_employee_id_and_employee() {
        //given
        Employee employeeUpdate = new Employee("Tom", 20, "Male", 10000, "123");
        when(employeeRepository.existsById("1")).thenReturn(false);

        //when
        EmployeeNotFoundException employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.updateEmployee("1", employeeUpdate);
        });

        //then
        assertEquals("Employee Not Found.", employeeNotFoundException.getLocalizedMessage());
    }

    @Test
    public void should_throw_employee_not_found_exception_when_delete_employee_by_id_given_repository_employee_id() {
        //given
        when(employeeRepository.existsById("1")).thenReturn(false);

        //when
        EmployeeNotFoundException employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.deleteEmployeeByID("1");
        });

        //then
        assertEquals("Employee Not Found.", employeeNotFoundException.getLocalizedMessage());
    }

}
