package com.team24.express.mapper;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.Employee;

import java.util.List;

/**
 * @className: RegionMapper
 * @author: Bobby
 * @date: 11/12/2023
 **/
public interface RegionMapper {
    List<Employee> getCouriers(String region);

    List<Delivery> getDeliveries(String region);
}
