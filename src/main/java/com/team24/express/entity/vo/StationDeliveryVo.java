package com.team24.express.entity.vo;

import com.team24.express.entity.StationDelivery;
import lombok.Data;

/**
 * @className: StationDeliveryVo
 * @author: Bobby
 * @date: 11/12/2023
 **/
@Data
public class StationDeliveryVo extends StationDelivery {

    // 物流订单部分信息
    private String expressNumber;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
}
