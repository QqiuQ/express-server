package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.StationDelivery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: StationDeliveryMapper
 * @author: Bobby
 * @date: 11/11/2023
 **/
public interface StationDeliveryMapper extends BaseMapper<StationDelivery> {
    List<Delivery> getDeliverysByStationId(@Param("stationId") Long stationId);
}
