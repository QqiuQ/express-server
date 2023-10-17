package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @className: Employee
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Data
@TableName("employee")
public class Employee {  // 由于要实现security登录认证
    private Long id;
    private String code;
    private String name;
    private Integer sex;
    private String avatar;
    private String phone;
    private String username;
    private String password;
    private String email;
    private Integer level;
    private Integer status;
    private String address;
    private Integer accountStatus;
    private LocalDate hireDate;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static final Integer ACCOUNT_STATUS_NORMAL = 0;  // 正常
    public static final Integer ACCOUNT_STATUS_BLOCK = 1;   // 锁住
    public static final Integer ACCOUNT_STATUS_REMOVE = 2;  // 封禁
    public static final Integer STATUS_POSITION_ON = 0;     // 在职
    public static final Integer STATUS_POSITION_OFF = 1;        // 离职
    public static final Integer STATUS_POSITION_VOCATION = 2;   // 休假

}
