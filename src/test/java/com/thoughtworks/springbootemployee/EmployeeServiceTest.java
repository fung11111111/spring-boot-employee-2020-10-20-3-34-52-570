package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Test
    public void should_return_all_employee_when_get_employee_list_given_repository_with_all_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> expectedEmployees = Arrays.asList(new Employee(1, "Tom", 20, "Male", 20000));
        when(employeeRepository.getEmployeesList()).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getEmployeesList();

        //then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_add_employee_when_add_employee_given_repository_with_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee expectedEmployee = new Employee(1, "Tom", 20, "Male", 20000);
        when(employeeRepository.addEmployee(expectedEmployee)).thenReturn(expectedEmployee);

        //when
        Employee actualEmployees = employeeService.addEmployee(expectedEmployee);

        //then
        assertEquals(expectedEmployee, actualEmployees);
    }

    @Test
    public void should_return_employee_when_get_employee_by_id_given_repository_with_employee_id() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee expectedEmployee = new Employee(1, "Tom", 20, "Male", 20000);
        when(employeeRepository.getEmployeeByID(1)).thenReturn(expectedEmployee);

        //when
        Employee actualEmployees = employeeService.getEmployeeByID(1);

        //then
        assertEquals(expectedEmployee, actualEmployees);
    }

    @Test
    public void should_return_employee_when_update_employee_by_id_given_repository_with_employee_id_employee_update() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee expectedEmployee = new Employee(1, "Tommy", 21, "Male", 21000);
        when(employeeRepository.updateEmployee(1, expectedEmployee)).thenReturn(expectedEmployee);

        //when
        Employee actualEmployees = employeeService.updateEmployee(1, expectedEmployee);

        //then
        assertEquals(expectedEmployee, actualEmployees);
    }

    @Test
    public void should_call_deleteEmployeeByID_when_service_delete_employee_by_id_given_repository_with_employee_id() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        //when
        employeeService.deleteEmployeeByID(1);

        //then
        verify(employeeRepository, times(1)).deleteEmployeeByID(1);
    }

    @Test
    public void should_return_male_employees_when_get_employees_by_gender_given_repository_with_employee_gender() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        employeeRepository.addEmployee(new Employee(1, "Tom", 20, "Male", 200));
        employeeRepository.addEmployee(new Employee(2, "Tommy", 20, "Male", 200));
        employeeRepository.addEmployee(new Employee(3, "Mandy", 20, "Female", 200));
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(1, "Tom", 20, "Male", 200));
        expectedEmployees.add(new Employee(2, "Tommy", 20, "Male", 200));
        when(employeeRepository.getEmployeesByGender("Male")).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getEmployeesByGender("Male");

        //then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void should_return_2_employees_when_get_employees_with_pagination_given_employees_more_than_2_with_pageNumber_is_1_and_pageSize_is_2() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(1, "Tom", 20, "Male", 200));
        expectedEmployees.add(new Employee(2, "Tommy", 20, "Male", 200));
        when(employeeRepository.getWithPagination(1, 2)).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getWithPagination(1, 2);

        //then
        assertEquals(2, actualEmployees.size());
    }

}
