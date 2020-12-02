package com.thoughtworks.springbootemployee;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        employeeRepository.addEmployee(expectedEmployee);
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
        employeeRepository.addEmployee(new Employee(1, "Tom", 20, "Male", 20000));
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


}
