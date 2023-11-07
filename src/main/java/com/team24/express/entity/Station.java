package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: Station
 * @author: Bobby
 * @date: 11/6/2023
 * 站点表
 **/
@Schema(name = "Station", title = "站点表", description = "站点类属性")
@Data
@TableName("station")
public class Station {
    private Long id;
    private String phone;
    private String landlineNumber;
    private String province;
    private String city;
    private String country;
    private String street;
    private String detail;
    private String stationName;
    private String stationManager;
    private String managerPhone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
