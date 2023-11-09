package com.team24.express.mapper;


import com.team24.express.entity.Order;
import com.team24.express.entity.StationEmployee;
import com.team24.express.entity.StationOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StationMapper {
    /**
     * 条件查询包裹
     * @param order
     * @return
     */
    List<Order> selectPackagesByConditions(Order order);

    /**
     * 新增快递（快递入库）
     * @param stationOrder
     */
    @Insert("insert into express.station_order(order_id, station_id, create_time, update_time) " +
            "values (#{orderId},#{stationId},#{createTime},#{updateTime})")
    void addNewPackage(StationOrder stationOrder);

    /**
     * 修改快递状态（快递出库）
     * @param stationOrder
     */
    @Update("update express.station_order set status = 1, update_time = #{updateTime} where id = #{id}")
    void updatePackageCondition(StationOrder stationOrder);

    /**
     * 快递网点录入快递员
     * @param e
     */
    @Insert("insert into express.station_employee(station_id, employee_id, position, create_time, update_time)" +
            "values (#{stationId},#{employeeId},#{position},#{createTime},#{updateTime})")
    void addNewCourier(StationEmployee e);

    /**
     * 快递网点查询快递员
     * @param status
     * @param id
     * @return
     */
    List<StationEmployee> selectCourierByCondition(Integer status, Long id);

    /**
     * 撤销快递员
     * @param id
     */
    @Delete("delete from express.station_employee where employee_id = #{id}")
    void deleteById(Long id);
}
