package com.team24.express.service.impl;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.Employee;
import com.team24.express.mapper.RegionMapper;
import com.team24.express.service.RegionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: RegionServiceImpl
 * @author: Bobby
 * @date: 11/12/2023
 **/
@Service
public class RegionServiceImpl implements RegionService {
    @Resource
    RegionMapper regionMapper;

    @Override
    public List<Employee> getCouriers(String region) {
        return regionMapper.getCouriers(region);

    }

    @Override
    public List<Delivery> getDeliveries(String region) {
        return regionMapper.getDeliveries(region);
    }
}
