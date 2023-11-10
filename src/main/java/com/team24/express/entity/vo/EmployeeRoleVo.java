package com.team24.express.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @className: EmployeeRoleDto
 * @author: Bobby
 * @date: 11/9/2023
 **/
@Data
public class EmployeeRoleVo {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long employeeId;
    private String username;
    private String name;
    private String code;
    private String phone;
    private String email;
    private Integer status;
    private Integer accountStatus;
    private String avatar;
    private Integer sex;
    private String address;
    private LocalDate hireDate;

    private Integer roleId;
    private String roleName;
}
