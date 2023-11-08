package com.team24.express.service;

import com.team24.express.entity.Address;
import com.team24.express.mapper.AddressMapper;

import java.util.List;

/**
 * @className: AddressService
 * @author: Bobby
 * @date: 11/8/2023
 **/
public interface AddressService {
    Address selectById(Long id);

    List<Address> queryAddressList(Address address);

    Boolean edit(Address address);

    Boolean add(Address address);

    Boolean delete(Long id);
}
