package com.team24.express.service.impl;

import com.team24.express.mapper.CourierMapper;
import com.team24.express.service.CourierService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    CourierMapper courierMapper;

    private Integer condition; //物流状态

    @Override
    public void ackDelivered(Long id) {
        condition = 4;
        courierMapper.updateConditionById(id,condition);
    }

    @Override
    public void ackCollected(Long id) {
        condition = 2;
        courierMapper.updateConditionById(id,condition);
    }
}
