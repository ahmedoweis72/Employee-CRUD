package com.task.controller;

import com.task.dto.modelDto.EmployeeDto;
import com.task.service.EmpService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmplController {

    private final EmpService empService;

    public EmplController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public List<EmployeeDto> getEmplList() {
        return empService.getEmployeeList();
    }

    @PostMapping
    public EmployeeDto createNewEmployee(@RequestBody
                                         @Valid
                                         @NotNull    EmployeeDto employeeDto) {
        return empService.createNewEmployee(employeeDto);
    }

    @PutMapping
    public EmployeeDto updateEmployee(@RequestBody
                                      @Valid
                                          @NotNull    EmployeeDto employeeDto) {
        return empService.editEmployeeByDTO(employeeDto);
    }

    @DeleteMapping({"/{id}"})
    public void deleteEmployee(@PathVariable
                               @NotNull    Long id) {
        empService.deletEmplById(id);
    }

    @GetMapping({"/{id}"})
    public EmployeeDto findEmpByID(@PathVariable
                                   @NotNull Long id) {
        return empService.FindByID(id);
    }

    @GetMapping({"first/{firstname}"})
    public List<EmployeeDto> findEmpByFirstName(@PathVariable
                                                @NotNull String firstname) {
        return empService.findByFirstName(firstname);
    }

    @GetMapping({"last/{lastname}"})
    public List<EmployeeDto> findEmpByLastName(@PathVariable
                                               @NotNull String lastname) {
        return empService.findByLastName(lastname);
    }

    @GetMapping({"firstlastname/{firstname}/{lastname}"})
    public List<EmployeeDto> findEmpByFirstAndLastName(@PathVariable @NotNull String firstname,
                                                       @PathVariable @NotNull String lastname) {
        return empService.findByFirstNameAndLastName(firstname, lastname);
    }

    @GetMapping({"firstnameandage/{firstname}"})
    public List<EmployeeDto> findEmpByFirstAndAge(@PathVariable
                                                  @NotNull    String firstname) {
        return empService.findByFirstNameAndAgeLessThan(firstname);
    }

    @GetMapping({"firstandlastname/{name}"})
    public List<EmployeeDto> findEmpByFirstOrLastname(@PathVariable
                                                      @NotNull    String name) {
        return empService.findByFirstNameOrLastName(name);
    }


}



