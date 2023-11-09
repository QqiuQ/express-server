package com.team24.express.service;

import com.team24.express.entity.Delivery;

import java.util.List;

/**
 * @className: OrderService
 * @author: Bobby
 * @date: 11/8/2023
 **/
public interface DeliveryService {
    /**
     * 添加物流订单
     *
     * @param delivery
     * @return
     */
    Boolean add(Delivery delivery);

    /**
     * 预估两地物流价格
     *
     * @param srcCode
     * @param dstCode
     * @return
     */
    Integer estimatePrice(String srcCode, String dstCode);

    /**
     * 条件查询
     *
     * @param delivery
     * @return
     */
    List<Delivery> selectDeliveryList(Delivery delivery);

    /**
     * 寄出手机号查询
     *
     * @param phone
     * @return
     */
    List<Delivery> selectBySenderPhone(String phone);

    /**
     * 收到手机号查询
     *
     * @param phone
     * @return
     */
    List<Delivery> selectByReceivePhone(String phone);

    /**
     * 根据订单ID删除
     * @param id
     * @return
     */
    Boolean deleteById(Long id);

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    Boolean updateStatus(Long id, Integer status);

    /**
     * 更新物流状态
     * @param id
     * @param status
     * @return
     */
    Boolean updateExpressStatus(Long id, Integer status);

    /**
     * 根据ID返回物流实体
     * @param id
     * @return
     */
    Delivery getById(Long id);
}
