package com.team24.express.service;

import com.team24.express.entity.vo.StationDeliveryVo;

import java.util.List;

/**
 * @className: StationDeliveryService
 * @author: Bobby
 * @date: 11/11/2023
 **/
public interface StationDeliveryService {
    List<StationDeliveryVo> getDeliverysByStationId(Long stationId);

    boolean shipping(Long id);

    boolean outOfStock(Long id);

    boolean goStocking(Long id);
}
