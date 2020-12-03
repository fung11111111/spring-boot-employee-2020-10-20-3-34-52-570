package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepositoryInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeRepositoryInt employeeRepositoryInt;

    public EmployeeService(EmployeeRepositoryInt employeeRepositoryInt) {
        this.employeeRepositoryInt = employeeRepositoryInt;
    }

    public List<Employee> getEmployeesList() {
        return employeeRepositoryInt.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepositoryInt.save(employee);
    }

    public Optional<Employee> getEmployeeByID(String id) throws EmployeeNotFoundException {
        return employeeRepositoryInt.findById(id);
    }

    public Employee updateEmployee(String id, Employee employeeUpdate) throws EmployeeNotFoundException {
        return employeeRepository.updateEmployee(id, employeeUpdate);
    }

    public void deleteEmployeeByID(String id) throws EmployeeNotFoundException {
        employeeRepository.deleteEmployeeByID(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployeesByGender(gender);
    }

    public List<Employee> getWithPagination(Integer page, Integer pageSize) {
        return employeeRepository.getWithPagination(page, pageSize);
    }
}

