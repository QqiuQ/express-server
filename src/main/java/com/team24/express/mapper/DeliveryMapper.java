package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @className: OrderMapper
 * @author: Bobby
 * @date: 11/9/2023
 **/
@Mapper
public interface DeliveryMapper extends BaseMapper<Delivery> {
    @Update("update delivery set status = #{status} where id = #{id}")
    int updateStatus(Long id, Integer status);

    @Update("update delivery set express_status = #{status} where id = #{id}")
    int updateExpressStatus(Long id, Integer status);
}
