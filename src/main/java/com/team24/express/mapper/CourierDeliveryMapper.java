package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.CourierDelivery;
import com.team24.express.entity.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @className: CourierDeliveryMapper
 * @author: Bobby
 * @date: 11/12/2023
 **/
@Mapper
public interface CourierDeliveryMapper extends BaseMapper<CourierDelivery> {
    @Update("delete from courier_delivery where id = #{taskId}")
    boolean finishTask(Long taskId);

    List<Delivery> getCollectingBy(Long courierId, Integer status);
}
