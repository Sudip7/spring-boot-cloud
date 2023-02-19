package org.demo.springcloud.employeeservice.controller;

import org.demo.springcloud.employeeservice.dto.ApiResponseDto;
import org.demo.springcloud.employeeservice.dto.EmployeeDto;
import org.demo.springcloud.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @GetMapping("{employeeId}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable Long employeeId) {
        ApiResponseDto apiResponse = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
