package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @className: Employee
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Schema(name = "Employee", title = "员工表", description = "员工类属性")
@Data
@TableName("employee")
public class Employee extends Account implements Serializable {  // 由于要实现security登录认证
    private String code;
    private String name;
    private Integer sex;
    private String phone;
    private Integer status;
    private String address;
    private LocalDate hireDate;

    public static final Integer STATUS_POSITION_ON = 0;     // 在职
    public static final Integer STATUS_POSITION_OFF = 1;        // 离职
    public static final Integer STATUS_POSITION_VOCATION = 2;   // 休假
}
