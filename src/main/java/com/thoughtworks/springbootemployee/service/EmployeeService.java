package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesList() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByID(String id) throws EmployeeNotFoundException {
        if (employeeRepository.findById(id).isPresent()) {
            return employeeRepository.findById(id).get();
        }
        throw new EmployeeNotFoundException();
    }

    public Employee updateEmployee(String id, Employee employeeUpdate) throws EmployeeNotFoundException {
        if (employeeRepository.existsById(id)) {
            employeeUpdate.setId(id);
            return employeeRepository.save(employeeUpdate);
        }
        throw new EmployeeNotFoundException();
    }

    public void deleteEmployeeByID(String id) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException();
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Page<Employee> getWithPagination(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> getEmployeeByCompanyId(String companyId) throws EmployeeNotFoundException {
        if (employeeRepository.findByCompanyId(companyId) != null) {
            return employeeRepository.findByCompanyId(companyId);
        }
        throw new EmployeeNotFoundException();
    }
}

