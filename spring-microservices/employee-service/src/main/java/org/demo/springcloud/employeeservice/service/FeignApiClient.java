package org.demo.springcloud.employeeservice.service;

import org.demo.springcloud.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface FeignApiClient {

    // configuring getDepartmentByDepartmentCode endpoint 
    @GetMapping("api/department/{departmentCode}")
    DepartmentDto getDepartmentByDepartmentCode(@PathVariable String departmentCode);
}
