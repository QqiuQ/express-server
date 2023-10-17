package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: EmployeeRole
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Data
@TableName("employee_role")
public class EmployeeRole {
    private Long id;
    private Long employeeId;
    private Integer roleId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
