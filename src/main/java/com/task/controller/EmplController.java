package com.task.controller;
import com.task.dto.modelDto.EmployeeDto;
import com.task.model.Employee;
import com.task.service.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmplController {

    private final EmpService empService;

    public EmplController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeDto>> getEmplList(){
        return ResponseEntity.ok(empService.getEmployeeList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto employeeDto){
        return empService.createNewEmployee(employeeDto);
    }
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateCustomer(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        return empService.saveEmployeeByDTO(id, employeeDto);
    }
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id){
        empService.deletEmplById(id);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto findEmpByID(@PathVariable Long id){
        return empService.FindByID(id);
    }
    @GetMapping({"first/{firstname}"})
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> findEmpByFirstName(@PathVariable String firstname){
        return empService.findByFirstName(firstname);
    }
    @GetMapping({"last/{lastname}"})
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> findEmpByLastName(@PathVariable String lastname){
        return empService.findByLastName(lastname);
    }
    @GetMapping({"firstlastname/{firstname}/{lastname}"})
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> findEmpByFirstAndLastName(@PathVariable String firstname,@PathVariable String lastname){
        return empService.findByFirstNameAndLastName(firstname,lastname);
    }
    @GetMapping({"firstnameandage/{firstname}"})
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> findEmpByFirstAndAge(@PathVariable String firstname){
        return empService.findByFirstNameAndAgeLessThan(firstname);
    }
    @GetMapping({"firstandlastname/{name}"})
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> findEmpByFirstOrLastname(@PathVariable String name){
        return empService.findByFirstNameOrLastName(name);
    }
}



