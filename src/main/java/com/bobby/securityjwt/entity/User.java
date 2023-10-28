package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @className: User
 * @author: Bobby
 * @date: 10/8/2023
 * @description: 普通用户类
 **/
@Data
@TableName("user")
public class User implements Serializable {
    private Long id;
    private String nickname;
    private Integer sex;
    private String phone;
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
    private LocalDateTime registerTime;
    private String avatar;
    private Integer accountStatus;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer roleId;

    public static final Integer ACCOUNT_STATUS_NORMAL = 0;  // 正常
    public static final Integer ACCOUNT_STATUS_BLOCK = 1;   // 锁住
    public static final Integer ACCOUNT_STATUS_REMOVE = 2;  // 封禁
}
