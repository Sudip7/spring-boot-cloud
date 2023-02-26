package org.demo.springcloud.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto {
    
    private EmployeeDto employee;
    private DepartmentDto department;
    private OrganizationDto org;
}
