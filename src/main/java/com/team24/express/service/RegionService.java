package com.team24.express.service;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.Employee;

import java.util.List;

/**
 * @className: RegionService
 * @author: Bobby
 * @date: 11/12/2023
 **/

public interface RegionService {
    List<Employee> getCouriers(String region);

    List<Delivery> getDeliveries(String region);
}
