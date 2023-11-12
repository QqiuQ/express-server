package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: StationEmployee
 * @author: Bobby
 * @date: 11/8/2023
 * 网点订单关系实体类
 **/
@Schema(name = "StationDelivery", title = "网点订单关系表")    // springdoc(api 文档) 实体类注解
@Data
@TableName("station_delivery")
public class StationDelivery {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)

    private Long deliveryId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)

    private Long stationId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static final Integer STATUS_UNSHIPPING = 0;
    public static final Integer STATUS_UNSTOCK = 1;
    public static final Integer STATUS_UNCLAIMED = 2;

}
