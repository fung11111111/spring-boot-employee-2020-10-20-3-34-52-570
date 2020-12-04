package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Employee> getEmployeeByID(String id){
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(String id, Employee employeeUpdate) throws EmployeeNotFoundException {
        if(employeeRepository.existsById(id)){
            employeeUpdate.setId(id);
            return employeeRepository.save(employeeUpdate);
        }
        throw new EmployeeNotFoundException();
    }

    public void deleteEmployeeByID(String id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> getWithPagination(Integer page, Integer pageSize) {
        int pageToSkip = page - 1;
        return employeeRepository.findAll()
                .stream()
                .skip(pageToSkip * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeByCompanyId(String companyId) throws EmployeeNotFoundException{
        if(employeeRepository.findByCompanyId(companyId) != null){
            return employeeRepository.findByCompanyId(companyId);
        }
        throw  new EmployeeNotFoundException();
    }
}

