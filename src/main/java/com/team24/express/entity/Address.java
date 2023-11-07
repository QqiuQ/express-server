package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: Address
 * @author: Bobby
 * @date: 11/6/2023
 * 用户地址表
 **/
@Schema(name = "Address", title = "地址表", description = "用户地址类属性")
@Data
@TableName("address")
public class Address {
    private Long id;
    private Long userId;
    private String recipientName;
    private String phone;
    private String province;
    private String city;
    private String country;
    private String street;
    private String detail;
    private String postalCode;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
