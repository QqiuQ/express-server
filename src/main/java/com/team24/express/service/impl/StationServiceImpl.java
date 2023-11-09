package com.team24.express.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.Station;
import com.team24.express.entity.User;
import com.team24.express.mapper.StationMapper;
import com.team24.express.service.StationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
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
  // jjw code

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
  
  // zgd code

    @Override
    public Station selectByStationname(String username) {
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return stationMapper.selectOne(wrapper);
    }

    @Override
    public List<Station> getStationList() {
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        return stationMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return stationMapper.deleteById(id) > 0;
    }
    public boolean deleteBatchIds(List<Long> ids) {
        return stationMapper.deleteBatchIds(ids) > 0;
    }
    @Override
    public int insert(Station station) {
        return stationMapper.insert(station);
    }

    @Override
    public int update(Station station) {
        return stationMapper.updateById(station);
    }

    @Override
    public List<Station> queryList() {
        return stationMapper.selectList();
    }

}
