package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: CourierDelivery
 * @author: Bobby
 * @date: 11/12/2023
 **/
@Data
@TableName("courier_delivery")
public class CourierDelivery {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long courierId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long deliveryId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static final Integer STATUS_COLLECTING = 0;
    public static final Integer STATUS_DISPATCHING = 1;
    public static final Integer STATUS_FINISHED = 2;

}
