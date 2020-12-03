package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

//    @Autowired
//    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRepositoryInt employeeRepositoryInt;

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
        if(employeeRepositoryInt.existsById(id)){
            return employeeRepositoryInt.save(employeeUpdate);
        }
        throw new EmployeeNotFoundException();
    }

    public void deleteEmployeeByID(String id) throws EmployeeNotFoundException {
        employeeRepositoryInt.deleteById(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepositoryInt.findByGender(gender);
    }
//
//    public List<Employee> getWithPagination(Integer page, Integer pageSize) {
//        return employeeRepository.getWithPagination(page, pageSize);
//    }
}

