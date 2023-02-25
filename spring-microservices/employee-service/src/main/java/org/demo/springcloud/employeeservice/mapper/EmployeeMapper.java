package org.demo.springcloud.employeeservice.mapper;

import org.demo.springcloud.employeeservice.dto.EmployeeDto;
import org.demo.springcloud.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {

        EmployeeDto employeeDto = EmployeeDto.builder().id(employee.getId())
                .firstName(employee.getFirstName()).lastName(employee.getLastName())
                .email(employee.getEmail()).departmentCode(employee.getDepartmentCode()).build();

        return employeeDto;

    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(employeeDto.getId(), employeeDto.getFirstName(),
                employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getDepartmentCode());

        return employee;

    }
}
