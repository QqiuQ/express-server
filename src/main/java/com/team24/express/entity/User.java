package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "User", title = "用户表", description = "用户类属性")
@Data
@TableName("user")
public class User extends Account implements Serializable {
    private String nickname;
    private Integer sex;
    private String phone;
    private LocalDate birthday;
    private LocalDateTime registerTime;
}
