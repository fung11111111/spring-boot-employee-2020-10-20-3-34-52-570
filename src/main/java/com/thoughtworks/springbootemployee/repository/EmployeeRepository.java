package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findById(String id);

    Employee save(Employee employee);

    List<Employee> findByGender(String gender);

    boolean existsById(String id);

    List<Employee> findByCompanyId(String companyId);

}


