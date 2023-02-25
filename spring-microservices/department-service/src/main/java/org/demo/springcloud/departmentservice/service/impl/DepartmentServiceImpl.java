package org.demo.springcloud.departmentservice.service.impl;

import org.demo.springcloud.departmentservice.dto.DepartmentDto;
import org.demo.springcloud.departmentservice.entity.Department;
import org.demo.springcloud.departmentservice.mapper.DepartmentMapper;
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

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto newDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return newDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);
        return departmentDto;
    }

}
