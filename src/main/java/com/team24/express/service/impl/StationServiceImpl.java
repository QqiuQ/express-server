package com.team24.express.service.impl;

import com.team24.express.entity.Employee;
import com.team24.express.entity.Order;
import com.team24.express.entity.StationEmployee;
import com.team24.express.entity.StationOrder;
import com.team24.express.mapper.StationMapper;
import com.team24.express.service.StaitonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StationServiceImpl implements StaitonService {

    @Autowired
    StationMapper stationMapper;

    @Override
    public List<Order> selectPackages(Order order) {
        return stationMapper.selectPackagesByConditions(order);
    }

    @Override
    public void packageInRep(StationOrder stationOrder) {
        stationOrder.setCreateTime(LocalDateTime.now());
        stationOrder.setUpdateTime(LocalDateTime.now());
        stationMapper.addNewPackage(stationOrder);
    }

    @Override
    public void packageOutRep(StationOrder stationOrder) {
        stationOrder.setUpdateTime(LocalDateTime.now());
        stationMapper.updatePackageCondition(stationOrder);
    }

    @Override
    public void addNewCourier(StationEmployee e) {
        stationMapper.addNewCourier(e);
    }

    @Override
    public List<StationEmployee> searchCourier(Integer status, Long id) {
        return stationMapper.selectCourierByCondition(status,id);
    }

    @Override
    public void deleteCourier(Long id) {
        stationMapper.deleteById(id);
    }


}
