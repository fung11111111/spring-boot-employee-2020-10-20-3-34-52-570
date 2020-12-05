package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployeesList() {
        return employeeService.getEmployeesList().stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.addEmployee(employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @PutMapping("/{employeeId}")
    public EmployeeResponse updateEmployee(@PathVariable String employeeId, @RequestBody EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        Employee employee = employeeService.updateEmployee(employeeId,employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployeeByID(employeeId);
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployeeByID(@PathVariable String employeeId) throws EmployeeNotFoundException {
        return employeeMapper.toResponse(employeeService.getEmployeeByID(employeeId));
    }

    @GetMapping(params = "gender")
    public  List<EmployeeResponse> getEmployeeByGender(@RequestParam("gender") String gender) {
         return employeeService.getEmployeesByGender(gender).stream()
                 .map(employeeMapper::toResponse)
                 .collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<EmployeeResponse> getEmployeeByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return employeeService.getWithPagination(page, pageSize).stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

}