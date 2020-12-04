package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
//        employee.setName(employeeRequest.getName());
//        employee.setAge(employeeRequest.getAge());
//        employee.setGender(employeeRequest.getGender());
//        employee.setSalary(employeeRequest.getSalary());
//        employee.setCompanyId(employeeRequest.getCompanyId());

        BeanUtils.copyProperties(employeeRequest,employee);

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
//        employeeResponse.setName(employee.getName());
//        employeeResponse.setAge(employee.getAge());
//        employeeResponse.setGender(employee.getGender());
//        employeeResponse.setSalary(employee.getSalary());
//        employeeResponse.setCompanyId(employee.getCompanyId());
        BeanUtils.copyProperties(employee,employeeResponse);

        return  employeeResponse;
    }
}
