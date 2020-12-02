package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

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

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeUpdate) {
        return employeeService.updateEmployee(employeeId, employeeUpdate);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployeeByID(employeeId);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeByID(@PathVariable Integer employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeByID(employeeId);
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeeByGender(@RequestParam("gender") String gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeeByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return employeeService.getWithPagination(page, pageSize);
    }
}