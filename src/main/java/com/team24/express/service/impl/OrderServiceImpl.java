package com.team24.express.service.impl;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.Order;
import com.team24.express.mapper.OrderMapper;
import com.team24.express.mapper.StationMapper;
import com.team24.express.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Deprecated
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public int update(Delivery delivery) {
        return orderMapper.updateById(delivery);
    }

    @Override
    public List<Delivery> queryList() {
        return orderMapper.selectList();
    }
}
