package org.demo.springcloud.employeeservice.service;

import org.demo.springcloud.employeeservice.dto.ApiResponseDto;
import org.demo.springcloud.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    
    EmployeeDto saveEmployee(EmployeeDto employee);
    ApiResponseDto getEmployeeById(Long employeeId);
}
