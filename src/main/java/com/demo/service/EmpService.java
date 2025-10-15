package com.demo.service;

import com.demo.dto.modelDto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

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

    ResponseEntity<List<EmployeeDto>> getEmployeesByPage(int page, int size, String sortBy, String direction);

    ResponseEntity<List<EmployeeDto>> searchEmployees(String name, String department, Integer minAge, Integer maxAge);

    ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(Long departmentId);

    ResponseEntity<EmployeeDto> updateEmployeeStatus(Long id, boolean active);

    ResponseEntity<List<EmployeeDto>> getEmployeesCreatedBetween(String startDate, String endDate);

    ResponseEntity<Map<String, Object>> getAgeStatistics();

    ResponseEntity<Map<String, Long>> getEmployeeCountByDepartment();
}
