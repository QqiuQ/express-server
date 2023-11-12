package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.Order;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
@Deprecated
public interface OrderMapper extends BaseMapper<Delivery> {
    @Select("select * from delivery")
    List<Delivery> selectList();

}
