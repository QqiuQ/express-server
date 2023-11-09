package com.team24.express.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CourierMapper {

    /**
     * 根据id改变包裹物流状态
     * @param id
     * @param condition
     */
//    @Update("update express.`order` set express_status = #{condition} where id = #{id}")
    @Update("update delivery set express_status = #{condition} where id = #{id}")
    void updateConditionById(Long id, Integer condition);
}
