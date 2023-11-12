package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.Delivery;
import com.team24.express.mapper.DeliveryMapper;
import com.team24.express.service.DeliveryService;
import com.team24.express.util.SnowflakeIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @className: OrderServiceImpl
 * @author: Bobby
 * @date: 11/9/2023
 **/
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Resource
    DeliveryMapper deliveryMapper;
    @Resource
    SnowflakeIdGenerator idGenerator;

    @Override
    public Boolean add(Delivery delivery) {
        delivery.setCreateTime(LocalDateTime.now());
        delivery.setExpressNumber(String.valueOf(idGenerator.nextId()));
        delivery.setExpressStatus(Delivery.EXPRESS_STATUS_COLLECTING);
        delivery.setStatus(Delivery.STATUS_PROGRESS);
        if (deliveryMapper.insert(delivery) > 0) return true;
        return false;
    }

    @Override
    public Integer estimatePrice(String srcCode, String dstCode) {
        return null;
    }

    @Override
    public List<Delivery> selectDeliveryList(Delivery delivery) {
        QueryWrapper<Delivery> wrapper = new QueryWrapper<>();
        if (Objects.isNull(delivery)) {
            return deliveryMapper.selectList(wrapper);
        }
        if (Objects.nonNull(delivery.getExpressNumber()) && !delivery.getExpressNumber().isEmpty()) {
            wrapper.like("express_number", delivery.getExpressNumber());
        }
        if (Objects.nonNull(delivery.getStatus())) {
            wrapper.eq("status", delivery.getStatus());
        }
        if (Objects.nonNull(delivery.getSenderName()) && !delivery.getSenderName().isEmpty()) {
            wrapper.like("sender_name", delivery.getSenderName());
        }
        if (Objects.nonNull(delivery.getSenderPhone()) && !delivery.getSenderPhone().isEmpty()) {
            wrapper.like("sender_phone", delivery.getSenderPhone());
        }
        if (Objects.nonNull(delivery.getSenderAddress()) && !delivery.getSenderAddress().isEmpty()) {
            wrapper.like("sender_address", delivery.getSenderAddress());
        }
        if (Objects.nonNull(delivery.getRecipientName()) && !delivery.getRecipientName().isEmpty()) {
            wrapper.like("recipient_name", delivery.getRecipientName());
        }
        if (Objects.nonNull(delivery.getRecipientPhone()) && !delivery.getRecipientPhone().isEmpty()) {
            wrapper.like("recipient_phone", delivery.getRecipientPhone());
        }
        if (Objects.nonNull(delivery.getRecipientAddress()) && !delivery.getRecipientAddress().isEmpty()) {
            wrapper.like("recipient_address", delivery.getRecipientAddress());
        }
        if (Objects.nonNull(delivery.getExpressStatus())) {
            wrapper.eq("express_status", delivery.getExpressStatus());
        }
        if (Objects.nonNull(delivery.getCourierName()) && !delivery.getCourierName().isEmpty()) {
            wrapper.like("courier_name", delivery.getRecipientAddress());
        }
        if (Objects.nonNull(delivery.getCourierPhone()) && !delivery.getCourierPhone().isEmpty()) {
            wrapper.like("courier_phone", delivery.getCourierPhone());
        }
        if (Objects.nonNull(delivery.getCourierCode()) && !delivery.getCourierCode().isEmpty()) {
            wrapper.like("courier_code", delivery.getCourierCode());
        }
        if (Objects.nonNull(delivery.getDescription()) && !delivery.getDescription().isEmpty()) {
            wrapper.like("description", delivery.getDescription());
        }
        if (Objects.nonNull(delivery.getRemark()) && !delivery.getRemark().isEmpty()) {
            wrapper.like("remark", delivery.getRemark());
        }
        if (Objects.nonNull(delivery.getRecipientSignature()) && !delivery.getRecipientSignature().isEmpty()) {
            wrapper.like("recipient_signature", delivery.getRecipientSignature());
        }
        return deliveryMapper.selectList(wrapper);
    }

    @Override
    public List<Delivery> selectBySenderPhone(String phone) {
        QueryWrapper<Delivery> wrapper = new QueryWrapper<>();
        wrapper.like("sender_phone", phone);
        return deliveryMapper.selectList(wrapper);
    }

    @Override
    public List<Delivery> selectByReceivePhone(String phone) {
        QueryWrapper<Delivery> wrapper = new QueryWrapper<>();
        wrapper.like("recipient_phone", phone);
        return deliveryMapper.selectList(wrapper);
    }

    @Override
    public Boolean deleteById(Long id) {
        if (deliveryMapper.deleteById(id) > 0) return true;
        return false;
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        if (deliveryMapper.updateStatus(id, status) > 0) return true;
        return false;
    }

    @Override
    public Boolean updateExpressStatus(Long id, Integer status) {
        if (deliveryMapper.updateExpressStatus(id, status) > 0) return true;
        return false;
    }

    @Override
    public Delivery getById(Long id) {
        return deliveryMapper.selectById(id);
    }

    @Override
    public boolean edit(Delivery delivery) {
        return deliveryMapper.updateById(delivery) > 0;
    }
}
