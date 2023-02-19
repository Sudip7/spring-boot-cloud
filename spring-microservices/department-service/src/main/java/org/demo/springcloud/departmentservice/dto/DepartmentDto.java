package org.demo.springcloud.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    
    private Long id ;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
