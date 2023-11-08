package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.common.Result;
import com.team24.express.entity.Address;
import com.team24.express.mapper.AddressMapper;
import com.team24.express.service.AddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @className: AddressServiceImpl
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressMapper addressMapper;


    @Override
    public Address selectById(Long id) {
        return addressMapper.selectById(id);
    }

    @Override
    public List<Address> queryAddressList(Address address) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        if (Objects.isNull(address)) {
            return addressMapper.selectList(wrapper);
        }
        if (Objects.nonNull(address.getUserId())) {
            wrapper.eq("user_id", address.getUserId());
        }
        if (Objects.nonNull(address.getPhone()) && !address.getPhone().isEmpty()) {
            wrapper.like("phone", address.getPhone());
        }
        if (Objects.nonNull(address.getRecipientName()) && !address.getRecipientName().isEmpty()) {
            wrapper.like("recipient_name", address.getRecipientName());
        }
        if (Objects.nonNull(address.getProvince()) && !address.getProvince().isEmpty()) {
            wrapper.eq("province", address.getProvince());
        }
        if (Objects.nonNull(address.getCountry()) && !address.getCountry().isEmpty()) {
            wrapper.eq("country", address.getCountry());
        }
        if (Objects.nonNull(address.getCity()) && !address.getCity().isEmpty()) {
            wrapper.eq("city", address.getCity());
        }
        if (Objects.nonNull(address.getStreet()) && !address.getStreet().isEmpty()) {
            wrapper.eq("street", address.getStreet());
        }
        if (Objects.nonNull(address.getDetail()) && !address.getDetail().isEmpty()) {
            wrapper.like("detail", address.getDetail());
        }
        if (Objects.nonNull(address.getPostalCode()) && !address.getPostalCode().isEmpty()) {
            wrapper.eq("postal_code", address.getPostalCode());
        }

        return addressMapper.selectList(wrapper);
    }

    @Override
    public Boolean edit(Address address) {
        address.setUpdateTime(LocalDateTime.now());
        if (addressMapper.updateById(address) > 0) return true;
        return false;
    }

    @Override
    public Boolean add(Address address) {
        address.setCreateTime(LocalDateTime.now());
        if (addressMapper.insert(address) > 0) return true;
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (addressMapper.deleteById(id) > 0) return true;
        return false;
    }
}
