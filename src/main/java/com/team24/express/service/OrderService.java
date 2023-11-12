package com.team24.express.service;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.Station;

import java.util.List;

/**
 * @className: DeliveryService
 * @author: Bobby
 * @date: 10/10/2023
 **/
@Deprecated
public interface OrderService {
//    Delivery selectByDeliveryname(String Deliveryname);



    @Deprecated
    int update(Delivery delivery);
    List<Delivery> queryList();


}
