package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;


public interface EmployeeRepositoryInt extends MongoRepository<Employee, String> {

    Optional<Employee> findById(String id);

    Employee save(Employee employee);

    List<Employee> findByGender(String gender);

    @Override
    void deleteById(String id);

    boolean existsById(String id);

}


