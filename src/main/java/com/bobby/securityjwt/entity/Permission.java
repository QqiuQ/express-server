package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: Permission
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Data
@TableName("permission")
public class Permission {
    private Integer id;
    private Integer roleId;
    private String domain;
    private String permission;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
