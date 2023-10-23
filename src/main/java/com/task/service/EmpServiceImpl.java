package com.task.service;
import com.task.controller.EmplController;
import com.task.dto.mapper.EmplooyeeMapper;
import com.task.dto.modelDto.EmployeeDto;
import com.task.model.Employee;
import com.task.repositories.EmpRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmpServiceImpl implements EmpService {
    private final EmpRepositories empRepositories;
    @Autowired
    private  EmplooyeeMapper emplooyeeMapper;

    public EmpServiceImpl(EmpRepositories empRepositories ) {
        this.empRepositories = empRepositories;

    }


    public EmployeeDto FindByID (Long Id){
        return emplooyeeMapper.employeeToEmployeeDTO(empRepositories.findById(Id).get()) ;
     }
    @Override
     public List<EmployeeDto> getEmployeeList(){
        return empRepositories.findAll()
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
     }

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        return saveAndReturnDTO(emplooyeeMapper.employeeDTOToEmployee(employeeDto));
    }

    @Override
    public EmployeeDto saveEmployeeByDTO(Long id, EmployeeDto employeeDto) {
        Employee employee = emplooyeeMapper.employeeDTOToEmployee(employeeDto);
        employee.setId(id);
        return saveAndReturnDTO(employee);
    }
    public void deletEmplById(Long id){
        empRepositories.deleteById(id);
    }
    @Override
    public List<EmployeeDto> findByFirstName(String firstName) {
        return empRepositories.findByFirstName(firstName)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList())
                ;
    }
    @Override
    public List<EmployeeDto> findByLastName(String lastName) {
        return empRepositories.findByLastName(lastName)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> findByFirstNameAndLastName(String firstName, String lastName) {
        return empRepositories.findByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> findByFirstNameAndAgeLessThan(String firstName) {
        return empRepositories.findAllByFirstNameAndAgeLessThan(firstName,30)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO).collect(Collectors.toList());

    }

    @Override
    public List<EmployeeDto> findByFirstNameOrLastName(String name) {
        return empRepositories.findByFirstNameOrLastName(name,name)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO).collect(Collectors.toList());

    }
    private EmployeeDto saveAndReturnDTO(Employee employee) {
        Employee savedEmployee = empRepositories.save(employee);
        EmployeeDto returnDto = emplooyeeMapper.employeeToEmployeeDTO(savedEmployee);
        return returnDto;
    }

}
