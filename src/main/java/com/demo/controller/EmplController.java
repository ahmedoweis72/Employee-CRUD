package com.demo.controller;

import com.demo.dto.modelDto.EmployeeDto;
import com.demo.service.EmpService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmplController {

    private final EmpService empService;

    public EmplController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmplList() {
        return empService.getEmployeeList();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return empService.createNewEmployee(employeeDto);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return empService.editEmployeeByDTO(employeeDto);
    }

    @DeleteMapping({"/{id}"})
    public void deleteEmployee(@PathVariable @NotNull Long id) {
        empService.deletEmplById(id);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<EmployeeDto> findEmpByID(@PathVariable @NotNull Long id) {
        return empService.FindByID(id);
    }

    @GetMapping({"first/{firstname}"})
    public ResponseEntity<List<EmployeeDto>> findEmpByFirstName(@PathVariable @NotNull String firstname) {
        return empService.findByFirstName(firstname);
    }

    @GetMapping({"last/{lastname}"})
    public ResponseEntity<List<EmployeeDto>> findEmpByLastName(@PathVariable @NotNull String lastname) {
        return empService.findByLastName(lastname);
    }

    @GetMapping({"firstlastname/{firstname}/{lastname}"})
    public ResponseEntity<List<EmployeeDto>> findEmpByFirstAndLastName(@PathVariable @NotNull String firstname, @PathVariable @NotNull String lastname) {
        return empService.findByFirstNameAndLastName(firstname, lastname);
    }

    @GetMapping({"firstnameandage/{firstname}"})
    public ResponseEntity<List<EmployeeDto>> findEmpByFirstAndAge(@PathVariable @NotNull String firstname) {
        return empService.findByFirstNameAndAgeLessThan(firstname);
    }

    @GetMapping({"firstandlastname/{name}"})
    public ResponseEntity<List<EmployeeDto>> findEmpByFirstOrLastname(@PathVariable @NotNull String name) {
        return empService.findByFirstNameOrLastName(name);
    }

    @GetMapping("/page")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction) {
        return empService.getEmployeesByPage(page, size, sortBy, direction);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> searchEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) String department, @RequestParam(required = false) Integer minAge, @RequestParam(required = false) Integer maxAge) {
        return empService.searchEmployees(name, department, minAge, maxAge);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@PathVariable Long departmentId) {
        return empService.getEmployeesByDepartment(departmentId);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EmployeeDto> updateEmployeeStatus(@PathVariable Long id, @RequestParam boolean active) {
        return empService.updateEmployeeStatus(id, active);
    }

    @GetMapping("/created-between")
    public ResponseEntity<List<EmployeeDto>> getEmployeesCreatedBetween(@RequestParam String startDate, @RequestParam String endDate) {
        return empService.getEmployeesCreatedBetween(startDate, endDate);
    }

    @GetMapping("/stats/age")
    public ResponseEntity<Map<String, Object>> getAgeStatistics() {
        return empService.getAgeStatistics();
    }

    @GetMapping("/stats/department-count")
    public ResponseEntity<Map<String, Long>> getEmployeeCountByDepartment() {
        return empService.getEmployeeCountByDepartment();
    }


}



