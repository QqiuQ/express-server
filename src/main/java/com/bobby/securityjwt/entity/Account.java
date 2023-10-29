package com.bobby.securityjwt.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: BaseUser
 * @author: Bobby
 * @date: 10/29/2023
 * User 和 Employee 的基类
 **/
@Data
public class Account {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private Integer accountStatus;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime lastLoginTime;

    public static final Integer ACCOUNT_STATUS_NORMAL = 0;  // 正常
    public static final Integer ACCOUNT_STATUS_BLOCK = 1;   // 锁住
    public static final Integer ACCOUNT_STATUS_REMOVE = 2;  // 封禁
}
