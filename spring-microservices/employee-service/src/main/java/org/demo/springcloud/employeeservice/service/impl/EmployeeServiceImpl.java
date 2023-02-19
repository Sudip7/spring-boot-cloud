package org.demo.springcloud.employeeservice.service.impl;

import org.demo.springcloud.employeeservice.dto.ApiResponseDto;
import org.demo.springcloud.employeeservice.dto.DepartmentDto;
import org.demo.springcloud.employeeservice.dto.EmployeeDto;
import org.demo.springcloud.employeeservice.entity.Employee;
import org.demo.springcloud.employeeservice.repository.EmployeeRepository;
import org.demo.springcloud.employeeservice.service.EmployeeService;
import org.demo.springcloud.employeeservice.service.FeignApiClient;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;

    // private WebClient webClient;

    private FeignApiClient feignApiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employeeInput = new Employee(employeeDto.getId(), employeeDto.getFirstName(),
                employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getDepartmentCode());

        Employee savedEmployee = employeeRepository.save(employeeInput);

        EmployeeDto employeeDtoOutput = EmployeeDto.builder().id(savedEmployee.getId())
                .firstName(savedEmployee.getFirstName()).lastName(savedEmployee.getLastName())
                .email(savedEmployee.getEmail()).departmentCode(savedEmployee.getDepartmentCode()).build();

        return employeeDtoOutput;
    }

    @Override
    public ApiResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).get();
        // ResponseEntity<DepartmentDto> responseEntity =
        // restTemplate.getForEntity("http://localhost:8080/api/department/" +
        // employee.getDepartmentCode(),
        // DepartmentDto.class);

        // DepartmentDto departmentDto = responseEntity.getBody();

   
        // using spring webflux client in sync mode
        // DepartmentDto departmentDto = webClient.get()
        // .uri("http://localhost:8080/api/department/" + employee.getDepartmentCode())
        // .retrieve()
        // .bodyToMono(DepartmentDto.class)
        // .block();

        DepartmentDto departmentDto = feignApiClient.getDepartmentByDepartmentCode(employee.getDepartmentCode());

        EmployeeDto employeeRetrieved = EmployeeDto.builder().id(employee.getId())
                .firstName(employee.getFirstName()).lastName(employee.getLastName())
                .email(employee.getEmail()).departmentCode(employee.getDepartmentCode()).build();

        ApiResponseDto responseDto = ApiResponseDto.builder().employee(employeeRetrieved).department(departmentDto)
                .build();
        return responseDto;

    }

}
