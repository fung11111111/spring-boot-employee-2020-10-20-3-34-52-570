package com.thoughtworks.springbootemployee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public Employee getEmployeeByID(Integer id) {
        return this.employees.stream()
                .filter(employee -> id.equals(employee.getId()))
                .findFirst()
                .orElse(null);
    }

    public Employee updateEmployee(Integer id, Employee employeeUpdate) {
        return null;
    }
}
