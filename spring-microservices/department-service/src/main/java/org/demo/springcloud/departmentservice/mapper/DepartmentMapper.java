package org.demo.springcloud.departmentservice.mapper;

import org.demo.springcloud.departmentservice.dto.DepartmentDto;
import org.demo.springcloud.departmentservice.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department) {

        DepartmentDto departmentDto = DepartmentDto.builder().id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentCode(department.getDepartmentCode())
                .departmentDescription(department.getDepartmentDescription()).build();

        return departmentDto;
    }

    public static Department mapToDepartment(DepartmentDto departmentDto) {
        Department department = new Department(departmentDto.getId(),
                departmentDto.getDepartmentName(), departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode());
        return department;
    }
}
