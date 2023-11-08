package com.team24.express.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: EmployeeDto
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Deprecated
@Data
@AllArgsConstructor
public class EmployeeDto {
    private String username;
    private String password;
    private String code;
}
