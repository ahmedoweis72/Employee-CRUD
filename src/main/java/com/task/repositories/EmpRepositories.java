package com.task.repositories;

import com.task.dto.modelDto.EmployeeDto;
import com.task.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRepositories extends JpaRepository<Employee,Long> {

    List<Employee> findByLastName(String lastName);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    List<Employee> findAllByFirstNameAndAgeLessThan(String firstName, Integer age);
    List<Employee> findByFirstNameOrLastName(String firtname, String lastname);


}
