package com.team24.express.service.impl;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.StationDelivery;
import com.team24.express.entity.vo.StationDeliveryVo;
import com.team24.express.mapper.StationDeliveryMapper;
import com.team24.express.service.DeliveryService;
import com.team24.express.service.StationDeliveryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Resource
    DeliveryService deliveryService;

    @Override
    public List<StationDeliveryVo> getDeliverysByStationId(Long stationId) {
        return stationDeliveryMapper.getDeliverysByStationId(stationId);
    }

    @Override
    public boolean shipping(Long id) {
        // 查找到相关快递
        // 快递订单非取消
        // 更改物流状态
        // 删除本记录
        StationDelivery stationDelivery = stationDeliveryMapper.selectById(id);
        Delivery delivery = deliveryService.getById(stationDelivery.getDeliveryId());
        if (delivery.getStatus().equals(Delivery.STATUS_CANCEL)) {
            return false;
        }

        return   deliveryService.updateExpressStatus(stationDelivery.getDeliveryId(), Delivery.EXPRESS_STATUS_SHIPPING)&&
                stationDeliveryMapper.deleteById(stationDelivery.getId()) > 0;
    }

    @Override
    public boolean outOfStock(Long id) {
        // 查找到相关快递
        // 更改物流状态 -> 已签收
        // 订单完成
        // 删除本记录状态
        StationDelivery stationDelivery = stationDeliveryMapper.selectById(id);
        return deliveryService.updateExpressStatus(stationDelivery.getDeliveryId(), Delivery.EXPRESS_STATUS_SIGNED)
                && deliveryService.updateStatus(stationDelivery.getDeliveryId(), Delivery.STATUS_FINISH)
                && stationDeliveryMapper.deleteById(stationDelivery.getId()) > 0;
    }

    @Override
    public boolean goStocking(Long id) {
        // 查找到相关快递
        // 更改物流状态 -> 待取件
        // 状态改为已入库
        StationDelivery stationDelivery = stationDeliveryMapper.selectById(id);
        stationDelivery.setStatus(StationDelivery.STATUS_UNCLAIMED);
        stationDelivery.setUpdateTime(LocalDateTime.now());
        return deliveryService.updateExpressStatus(stationDelivery.getDeliveryId(), Delivery.EXPRESS_STATUS_UNCLAIM)
                && stationDeliveryMapper.updateById(stationDelivery) > 0;
    }
}
