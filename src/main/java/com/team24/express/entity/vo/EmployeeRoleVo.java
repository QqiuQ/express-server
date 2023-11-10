package com.team24.express.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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

    private Integer roleId;
    private String roleName;
}
