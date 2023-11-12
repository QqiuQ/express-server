package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.StationDelivery;
import com.team24.express.entity.vo.StationDeliveryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: StationDeliveryMapper
 * @author: Bobby
 * @date: 11/11/2023
 **/
@Mapper
public interface StationDeliveryMapper extends BaseMapper<StationDelivery> {
    List<StationDeliveryVo> getDeliverysByStationId(@Param("stationId") Long stationId);
}
