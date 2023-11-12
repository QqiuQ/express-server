package com.team24.express.service;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.Station;
import com.team24.express.entity.StationEmployee;
import com.team24.express.entity.StationDelivery;

import java.util.List;

/**
 * 名称打错！！！
 */
public interface StaitonService {

    /**
     * 条件查询包裹信息
     *
     * @param order
     * @return
     */
    List<Delivery> selectPackages(Delivery order);

    /**
     * 订单与网点关系表中新增-快递入库
     *
     * @param stationDelivery
     */
    void packageInRep(StationDelivery stationDelivery);

    /**
     * 订单与网点关系表中修改包裹状态
     *
     * @param stationDelivery
     */
    void packageOutRep(StationDelivery stationDelivery);


    /**
     * 录入快递员
     *
     * @param e
     */
    void addNewCourier(StationEmployee e);


    /**
     * 查询快递员
     *
     * @param status
     * @param id
     * @return
     */
    List<StationEmployee> searchCourier(Integer status, Long id);

    /**
     * 根据快递员id在员工关系表内删除快递员与网点的关系
     *
     * @param id
     */
    void deleteCourier(Long id);

    List<Station> queryList();

    int insert(Station station);

    int update(Station station);

    boolean deleteById(Long id);

    boolean deleteBatchIds(List<Long> ids);

    Station selectByStationname(String username);

    List<Station> getStationList();

    List<Station> queryByAddress(String address);
}
