package com.thoughtworks.springbootemployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesList(){
        return employeeRepository.getEmployeesList();
    }

    public Employee addEmpolyee(Employee employee){
        return employeeRepository.addEmployee(employee);
    }

    public Employee getEmployeeByID(Integer id) {
        return employeeRepository.getEmployeeByID(id);
    }

    public Employee updateEmployee(Integer id, Employee employeeUpdate) {
        return employeeRepository.updateEmployee(id, employeeUpdate);
    }
}

