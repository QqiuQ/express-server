package com.team24.express.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: BaseUser
 * @author: Bobby
 * @date: 10/29/2023
 * User 和 Employee 的基类
 **/
@Schema(name = "Account", title = "账户类", description = "账户类属性，是User类和Employee类的基类")
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
    public static final Integer ACCOUNT_STATUS_BLOCK = 1;   // 封禁
    public static final Integer ACCOUNT_STATUS_REMOVE = 2;  // 注销
}
