package com.team24.express.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper

public interface StationMapper extends BaseMapper<Station> {
    // zgd code
    @Select("select * from station")
    List<Station> selectList();


    // jjw code

    /**
     * 条件查询包裹
     *
     * @param order
     * @return
     */
    List<Delivery> selectPackagesByConditions(Delivery order);

    /**
     * 新增快递（快递入库）
     *
     * @param stationDelivery
     */
    @Insert("insert into express.station_order(order_id, station_id, create_time, update_time) " +
            "values (#{orderId},#{stationId},#{createTime},#{updateTime})")
    void addNewPackage(StationDelivery stationDelivery);

    /**
     * 修改快递状态（快递出库）
     *
     * @param stationDelivery
     */
    @Update("update express.station_order set status = 1, update_time = #{updateTime} where id = #{id}")
    void updatePackageCondition(StationDelivery stationDelivery);

    /**
     * 快递网点录入快递员
     *
     * @param e
     */
    @Insert("insert into express.station_employee(station_id, employee_id, position, create_time, update_time)" +
            "values (#{stationId},#{employeeId},#{position},#{createTime},#{updateTime})")
    void addNewCourier(StationEmployee e);

    /**
     * 快递网点查询快递员
     *
     * @param status
     * @param id
     * @return
     */
    List<StationEmployee> selectCourierByCondition(Integer status, Long id);

    /**
     * 撤销快递员
     *
     * @param id
     */
    @Delete("delete from express.station_employee where employee_id = #{id}")
    void deleteEmployeeById(Long id);
//    void deleteById(Long id); // 方法名与BaseMapper自带的方法冲突


}
