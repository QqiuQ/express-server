package com.team24.express.service.impl;

import com.team24.express.entity.Delivery;
import com.team24.express.mapper.StationDeliveryMapper;
import com.team24.express.service.StationDeliveryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: StationDeliveryServiceImpl
 * @author: Bobby
 * @date: 11/11/2023
 **/
@Service
public class StationDeliveryServiceImpl implements StationDeliveryService {
    @Resource
    StationDeliveryMapper stationDeliveryMapper;

    @Override
    public List<Delivery> getDeliverysByStationId(Long stationId) {
        return stationDeliveryMapper.getDeliverysByStationId(stationId);
    }
}
