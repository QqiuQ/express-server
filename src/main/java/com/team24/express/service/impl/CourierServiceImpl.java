package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.CourierDelivery;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.Employee;
import com.team24.express.entity.StationDelivery;
import com.team24.express.mapper.*;
import com.team24.express.service.CourierService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    CourierMapper courierMapper;
    @Resource
    CourierDeliveryMapper courierDeliveryMapper;
    @Resource
    DeliveryMapper deliveryMapper;
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    StationDeliveryMapper stationDeliveryMapper;

    private Integer condition; //物流状态

    @Override
    public void ackDelivered(Long id) {
        condition = 4;
        courierMapper.updateConditionById(id, condition);
    }

    @Override
    public void ackCollected(Long id) {
        condition = 2;
        courierMapper.updateConditionById(id, condition);
    }

    @Override
    public boolean acceptDelivery(CourierDelivery delivery) {
        delivery.setCreateTime(LocalDateTime.now());
        return courierDeliveryMapper.insert(delivery) > 0;
    }

    @Override
    public boolean finishTask(Long taskId) {

        return courierDeliveryMapper.finishTask(taskId);
    }

    @Override
    public List<Delivery> getCollectingBy(Long courierId, Integer statusCollecting) {
        return courierDeliveryMapper.getCollectingBy(courierId, statusCollecting);
    }

    @Override
    public boolean confirmCollected(Long id, Long stationId) {
        // 揽收快递：
        // 从关系表中删除
        // 将快递物流状态改为运输中
        QueryWrapper<CourierDelivery> wrapper = new QueryWrapper<>();
        wrapper.eq("delivery_id", id);
        wrapper.eq("status", CourierDelivery.STATUS_COLLECTING);
        CourierDelivery courierDelivery = courierDeliveryMapper.selectOne(wrapper);// 找到该待揽收的关系对象
        if (Objects.isNull(courierDelivery)) return false;

        // 找到物流
        Delivery delivery = deliveryMapper.selectById(courierDelivery.getDeliveryId());
        // 投递到网点
        StationDelivery stationDelivery = new StationDelivery();
        stationDelivery.setCreateTime(LocalDateTime.now());
        stationDelivery.setStationId(stationId);
        stationDelivery.setDeliveryId(delivery.getId());
        stationDelivery.setStatus(StationDelivery.STATUS_UNSHIPPING);

        return deliveryMapper.updateById(delivery) > 0
                && stationDeliveryMapper.insert(stationDelivery) > 0
                && courierDeliveryMapper.deleteById(courierDelivery) > 0;
    }

    @Override
    public boolean confirmDispatched(Long deliveryId, Long courierId, Long stationId) {
        // 妥投快递
        // 从关系表中删除
        // 将快递物流状态改为 派送中
        // 之后添加快递与站点的关联
        QueryWrapper<CourierDelivery> wrapper = new QueryWrapper<>();
        wrapper.eq("delivery_id", deliveryId);
        wrapper.eq("status", CourierDelivery.STATUS_DISPATCHING);
        CourierDelivery courierDelivery = courierDeliveryMapper.selectOne(wrapper);// 找到该待派送的关系对象
        // 找到物流
        Delivery delivery = deliveryMapper.selectById(courierDelivery.getDeliveryId());
        delivery.setExpressStatus(Delivery.EXPRESS_STATUS_DELIVERING);
        // 找到快递员
        Employee courier = employeeMapper.selectById(courierId);
        delivery.setCourierCode(courier.getCode());
        delivery.setCourierName(courier.getName());
        delivery.setCourierPhone(courier.getPhone());
        delivery.setUpdateTime(LocalDateTime.now());
        delivery.setActualDeliveryTime(LocalDateTime.now());    //送达

        // 添加站点关系表
        StationDelivery sd = new StationDelivery();
        sd.setDeliveryId(delivery.getId());
        sd.setStationId(stationId);
        sd.setCreateTime(LocalDateTime.now());
        sd.setStatus(StationDelivery.STATUS_UNSTOCK);   // 待入库

        return stationDeliveryMapper.insert(sd) > 0 && deliveryMapper.updateById(delivery) > 0 && courierDeliveryMapper.deleteById(courierDelivery) > 0;
    }
}
