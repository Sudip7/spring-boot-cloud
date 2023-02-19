package org.demo.springcloud.departmentservice.service;

import org.demo.springcloud.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto savDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);

    
}
