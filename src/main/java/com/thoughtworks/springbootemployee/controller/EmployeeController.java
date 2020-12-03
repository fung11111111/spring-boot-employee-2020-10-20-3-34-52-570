package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployeesList() {
        return employeeService.getEmployeesList();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employeeCreate) {
        return employeeService.addEmployee(employeeCreate);
    }

//    @PutMapping("/{employeeId}")
//    public Employee updateEmployee(@PathVariable String employeeId, @RequestBody Employee employeeUpdate) throws EmployeeNotFoundException {
//        return employeeService.updateEmployee(employeeId, employeeUpdate);
//    }
//
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) throws EmployeeNotFoundException {
        employeeService.deleteEmployeeByID(employeeId);
    }

    @GetMapping("/{employeeId}")
    public Optional<Employee> getEmployeeByID(@PathVariable String employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeByID(employeeId);
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeeByGender(@RequestParam("gender") String gender) {
        return employeeService.getEmployeesByGender(gender);
    }

//    @GetMapping(params = {"page", "pageSize"})
//    public List<Employee> getEmployeeByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
//        return employeeService.getWithPagination(page, pageSize);
//    }
}