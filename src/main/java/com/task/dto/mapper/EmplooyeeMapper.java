package com.task.dto.mapper;

import com.task.dto.modelDto.EmployeeDto;
import com.task.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmplooyeeMapper {
    EmplooyeeMapper INSTANCE = Mappers.getMapper(EmplooyeeMapper.class);
    EmployeeDto employeeToEmployeeDTO(Employee employee);
    Employee employeeDTOToEmployee(EmployeeDto employeeDto);

}
