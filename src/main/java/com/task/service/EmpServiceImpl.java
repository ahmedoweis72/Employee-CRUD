package com.task.service;
import com.task.dto.mapper.EmplooyeeMapper;
import com.task.dto.modelDto.EmployeeDto;
import com.task.exceptions.NotFoundException;
import com.task.model.Employee;
import com.task.repositories.EmpRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        Optional<Employee> EmployeeOptional = empRepositories.findById(Id);
        if (!EmployeeOptional.isPresent()) {
            throw new NotFoundException();
        }
        return emplooyeeMapper.employeeToEmployeeDTO(EmployeeOptional.get()) ;
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

//    @Override
//    public EmployeeDto saveEmployeeByDTO(Long id, EmployeeDto employeeDto) {
//        Employee employee = emplooyeeMapper.employeeDTOToEmployee(employeeDto);
//        employee.setId(id);
//        return saveAndReturnDTO(employee);
//    }
    @Transactional
    public EmployeeDto editEmployeeByDTO(EmployeeDto employeeDto) {
        Employee employee = emplooyeeMapper.employeeDTOToEmployee(employeeDto);
        Long id=employee.getId();
        String firstName=employee.getFirstName();
        String lasttName=employee.getLastName();
        Integer age=employee.getAge();

        Employee employeeRepositre = empRepositories.findById(employee.getId()).orElseThrow(
                () -> new IllegalStateException("############################ employee with Id " + id + " doesn't exist #################################")
        );
        if (firstName!=null&&firstName.length()>0&&!Objects.equals(employeeRepositre.getFirstName(),firstName)){
            employeeRepositre.setFirstName(firstName);
        }
        if (lasttName!=null&&lasttName.length()>0&&!Objects.equals(employeeRepositre.getLastName(),lasttName)){
            employeeRepositre.setLastName(lasttName);
        }
        if (age!=null&&age>0){
            employeeRepositre.setAge(age);
        }
        return emplooyeeMapper.employeeToEmployeeDTO(employeeRepositre);

    }
    public void deletEmplById(Long id){
        boolean existsById = empRepositories.existsById(id);
        if (!existsById){
            throw new IllegalStateException("############################ employee with Id "+id+" doesn't exist #################################");
        }
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
