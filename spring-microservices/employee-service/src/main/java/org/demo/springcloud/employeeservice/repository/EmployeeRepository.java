package org.demo.springcloud.employeeservice.repository;

import org.demo.springcloud.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
