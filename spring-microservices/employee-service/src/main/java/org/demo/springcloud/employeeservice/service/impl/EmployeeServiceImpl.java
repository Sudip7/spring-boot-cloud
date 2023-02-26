package org.demo.springcloud.employeeservice.service.impl;

import org.demo.springcloud.employeeservice.dto.ApiResponseDto;
import org.demo.springcloud.employeeservice.dto.DepartmentDto;
import org.demo.springcloud.employeeservice.dto.EmployeeDto;
import org.demo.springcloud.employeeservice.dto.OrganizationDto;
import org.demo.springcloud.employeeservice.entity.Employee;
import org.demo.springcloud.employeeservice.mapper.EmployeeMapper;
import org.demo.springcloud.employeeservice.repository.EmployeeRepository;
import org.demo.springcloud.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

        private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
        private EmployeeRepository employeeRepository;

        // private RestTemplate restTemplate;

        private WebClient webClient;

        // private FeignApiClient feignApiClient;

        @Override
        public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

                Employee employeeInput = EmployeeMapper.mapToEmployee(employeeDto);

                Employee savedEmployee = employeeRepository.save(employeeInput);

                EmployeeDto employeeDtoOutput = EmployeeMapper.mapToEmployeeDto(savedEmployee);

                return employeeDtoOutput;
        }

        @Override
       // @CircuitBreaker(name = "${spring.application.name}",
         //               fallbackMethod = "getDefaultDepartment")
        @Retry(name = "${spring.application.name}",
                      fallbackMethod = "getDefaultDepartment")
        public ApiResponseDto getEmployeeById(Long employeeId) {

                LOGGER.info("Inside get employee by id method");
                Employee employee = employeeRepository.findById(employeeId).get();
                // ResponseEntity<DepartmentDto> responseEntity =
                // restTemplate.getForEntity("http://localhost:8080/api/department/" +
                // employee.getDepartmentCode(),
                // DepartmentDto.class);

                // DepartmentDto departmentDto = responseEntity.getBody();

                // using spring webflux client in sync mode
                DepartmentDto departmentDto = webClient.get()
                                .uri("http://localhost:8081/api/departments/"
                                                + employee.getDepartmentCode())
                                .retrieve().bodyToMono(DepartmentDto.class).block();

                // DepartmentDto departmentDto =
                // feignApiClient.getDepartmentByDepartmentCode(employee.getDepartmentCode());
                OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:9092/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

                EmployeeDto employeeRetrieved = EmployeeMapper.mapToEmployeeDto(employee);

                ApiResponseDto responseDto = ApiResponseDto.builder().employee(employeeRetrieved)
                                .department(departmentDto).org(organizationDto).build();
                return responseDto;

        }

        public ApiResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

                LOGGER.info("Inside getDefaultDepartment fallback method");
                Employee employee = employeeRepository.findById(employeeId).get();

                DepartmentDto departmentDto = DepartmentDto.builder().id(1l)
                                .departmentName("R&D department").departmentCode("RD001")
                                .departmentDescription("Research and Development Department")
                                .build();

                EmployeeDto employeeRetrieved = EmployeeMapper.mapToEmployeeDto(employee);

                ApiResponseDto responseDto = ApiResponseDto.builder().employee(employeeRetrieved)
                                .department(departmentDto).build();
                return responseDto;
        }

}
