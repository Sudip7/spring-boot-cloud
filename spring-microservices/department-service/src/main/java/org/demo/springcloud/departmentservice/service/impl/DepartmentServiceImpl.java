package org.demo.springcloud.departmentservice.service.impl;

import org.demo.springcloud.departmentservice.dto.DepartmentDto;
import org.demo.springcloud.departmentservice.entity.Department;
import org.demo.springcloud.departmentservice.repository.DepartmentRepository;
import org.demo.springcloud.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto savDepartment(DepartmentDto departmentDto) {

        Department department = new Department(departmentDto.getId(),
                departmentDto.getDepartmentName(), departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode());

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto newDepartmentDto = DepartmentDto.builder().id(savedDepartment.getId())
                .departmentName(savedDepartment.getDepartmentDescription())
                .departmentCode(savedDepartment.getDepartmentCode()).build();

        return newDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = DepartmentDto.builder().id(department.getId())
                .departmentName(department.getDepartmentName()).departmentCode(department.getDepartmentCode())
                .departmentDescription(department.getDepartmentDescription()).build();
        return departmentDto;
    }

}
