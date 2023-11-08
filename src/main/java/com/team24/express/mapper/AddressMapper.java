package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: AddressMapper
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Mapper
public interface AddressMapper extends BaseMapper<Address> {
}
