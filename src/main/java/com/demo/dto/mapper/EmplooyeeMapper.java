package com.demo.dto.mapper;

import com.demo.dto.modelDto.EmployeeDto;
import com.demo.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmplooyeeMapper {
    EmplooyeeMapper INSTANCE = Mappers.getMapper(EmplooyeeMapper.class);
    EmployeeDto employeeToEmployeeDTO(Employee employee);
    Employee employeeDTOToEmployee(EmployeeDto employeeDto);

}
