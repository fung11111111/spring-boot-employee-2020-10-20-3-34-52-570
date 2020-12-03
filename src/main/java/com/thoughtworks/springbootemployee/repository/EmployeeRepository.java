package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployeesList() {
        return employees;
    }

    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee getEmployeeByID(String id) throws EmployeeNotFoundException {
        return this.employees.stream()
                .filter(employee -> id.equals(employee.getId()))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    public Employee updateEmployee(String id, Employee employeeUpdate) throws EmployeeNotFoundException {
        Employee existingEmployee = getEmployeeByID(id);
        if (existingEmployee != null) {
            employees.remove(existingEmployee);
            employees.add(employeeUpdate);
            return employeeUpdate;
        }
        throw new EmployeeNotFoundException();
    }

    public void deleteEmployeeByID(String id) throws EmployeeNotFoundException {
        Employee deletedEmployee = getEmployeeByID(id);
        if (deletedEmployee != null) {
            employees.remove(deletedEmployee);
        }
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getWithPagination(Integer page, Integer pageSize) {
        int pageToSkip = page - 1;
        return employees.stream()
                .skip(pageToSkip * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
