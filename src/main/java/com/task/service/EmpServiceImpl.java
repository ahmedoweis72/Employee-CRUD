package com.task.service;
import com.task.dto.mapper.EmplooyeeMapper;
import com.task.dto.modelDto.EmployeeDto;
import com.task.exception.ResourceNotFoundException;
import com.task.model.Employee;
import com.task.repositories.EmpRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class EmpServiceImpl implements EmpService {
    private final EmpRepositories empRepositories;
    @Autowired
    private  EmplooyeeMapper emplooyeeMapper;

    public EmpServiceImpl(EmpRepositories empRepositories ) {
        this.empRepositories = empRepositories;

    }


    public ResponseEntity<EmployeeDto> FindByID (Long Id){
        Employee employee = empRepositories.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + Id));
        return ResponseEntity.ok(emplooyeeMapper.employeeToEmployeeDTO(employee));
    }

    @Override
     public ResponseEntity<List<EmployeeDto>> getEmployeeList(){
        List<EmployeeDto> employeeDtoList = empRepositories.findAll()
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
       return ResponseEntity.ok(employeeDtoList);
    }

    public ResponseEntity<EmployeeDto> createNewEmployee(EmployeeDto employeeDto) {
        Employee employee = emplooyeeMapper.employeeDTOToEmployee(employeeDto);
        Employee savedEmployee = empRepositories.save(employee);
        EmployeeDto returnDto = emplooyeeMapper.employeeToEmployeeDTO(savedEmployee);
        return ResponseEntity.ok(returnDto);
    }

    @Transactional
    public ResponseEntity<EmployeeDto> editEmployeeByDTO(EmployeeDto employeeDto) {
        Employee employee = emplooyeeMapper.employeeDTOToEmployee(employeeDto);
        Long id=employee.getId();
        String firstName=employee.getFirstName();
        String lasttName=employee.getLastName();
        Integer age=employee.getAge();

        Employee employeeRepositre = empRepositories.findById(employee.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Not Found Emploeyee With ID "+id)
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

        return ResponseEntity.ok(emplooyeeMapper.employeeToEmployeeDTO(employeeRepositre));

    }
    public void deletEmplById(Long id){
        boolean existsById = empRepositories.existsById(id);
        if (!existsById){
            throw new ResourceNotFoundException("Not Found Employee");
        }
        empRepositories.deleteById(id);
    }
    @Override
    public ResponseEntity<List<EmployeeDto>> findByFirstName(String firstName) {
        return ResponseEntity.ok(empRepositories.findByFirstName(firstName)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList()));

    }
    @Override
    public ResponseEntity<List<EmployeeDto>> findByLastName( String lastName) {
        return ResponseEntity.ok(empRepositories.findByLastName(lastName)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> findByFirstNameAndLastName( String firstName, String lastName) {
        return ResponseEntity.ok(empRepositories.findByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> findByFirstNameAndAgeLessThan( String firstName) {
         return ResponseEntity.ok( empRepositories.findAllByFirstNameAndAgeLessThan(firstName,30)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO).collect(Collectors.toList()));

    }

    @Override
    public ResponseEntity<List<EmployeeDto>> findByFirstNameOrLastName(String name) {
        return ResponseEntity.ok( empRepositories.findByFirstNameOrLastName(name,name)
                .stream()
                .map(emplooyeeMapper::employeeToEmployeeDTO).collect(Collectors.toList()));

    }


}
