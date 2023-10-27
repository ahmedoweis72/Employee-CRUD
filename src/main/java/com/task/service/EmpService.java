package com.task.service;

import com.task.dto.modelDto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmpService {

    ResponseEntity<EmployeeDto> FindByID (Long Id);
    ResponseEntity<List<EmployeeDto>>getEmployeeList();
    ResponseEntity<EmployeeDto> createNewEmployee(EmployeeDto employeeDto) ;
    ResponseEntity<EmployeeDto> editEmployeeByDTO(EmployeeDto employeeDto);
    void deletEmplById(Long id);
    ResponseEntity<List<EmployeeDto>> findByFirstName(String firstName);
    ResponseEntity<List<EmployeeDto>> findByLastName(String lastName);
    ResponseEntity<List<EmployeeDto>> findByFirstNameAndLastName(String firstName, String lastName);
    ResponseEntity<List<EmployeeDto>> findByFirstNameAndAgeLessThan(String firstName);
    ResponseEntity<List<EmployeeDto>> findByFirstNameOrLastName(String name);

}
