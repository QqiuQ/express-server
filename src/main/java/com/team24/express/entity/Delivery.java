package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: Delivery
 * @author: Bobby
 * @date: 11/9/2023
 **/
@Schema(name = "Delivery", title = "订单表", description = "订单类属性")
@Data
@TableName("delivery")
public class Delivery {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String expressNumber;
    private Integer status; //运单状态(0:已取消; 1:已完成; 2:进行中)
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private Integer expressStatus;  //物流状态(0:异常; 1:已代收; 2:已揽收; 3:运送中; 4:派送中; 5:待取件; 6:派送中; 7:已签收)
    private Integer packageWeight;  // 包裹重量(g)
    private Integer packageLength;  // 长宽(cm)
    private Integer packageWidth;
    private Integer expressCost;    // 快递费用(分)
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
    private String courierName;
    private String courierPhone;
    private String courierCode;
    private String description;
    private String remark;
    private String recipientSignature;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public static final Integer STATUS_CANCEL = 0;  // 取消
    public static final Integer STATUS_FINISH = 1;  // 完成
    public static final Integer STATUS_PROGRESS = 2;    //进行中

    public static final Integer EXPRESS_STATUS_EXCEPTION = 0;   // 异常
    public static final Integer EXPRESS_STATUS_COLLECTING = 1;  // 揽件中
    public static final Integer EXPRESS_STATUS_SHIPPING = 2;    // 运输中
    public static final Integer EXPRESS_STATUS_DELIVERING = 3;  // 派送中
    public static final Integer EXPRESS_STATUS_SIGNED = 4;    // 已签收
}
