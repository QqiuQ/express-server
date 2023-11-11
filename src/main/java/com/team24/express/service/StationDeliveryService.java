package com.team24.express.service;

import com.team24.express.entity.Delivery;

import java.util.List;

/**
 * @className: StationDeliveryService
 * @author: Bobby
 * @date: 11/11/2023
 **/
public interface StationDeliveryService {
    List<Delivery> getDeliverysByStationId(Long stationId);

}
