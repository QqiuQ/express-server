package com.team24.express.service;

import com.team24.express.entity.CourierDelivery;
import com.team24.express.entity.Delivery;

import java.util.List;

public interface CourierService {

    /**
     * 确认妥投
     * @param id
     */
    void ackDelivered(Long id);

    /**
     * 揽件确认
     * @param id
     */
    void ackCollected(Long id);

    boolean acceptDelivery(CourierDelivery delivery);

    boolean finishTask(Long taskId);

    List<Delivery> getCollectingBy(Long courierId, Integer statusCollecting);

    boolean confirmCollected(Long id, Long stationId);

    boolean confirmDispatched(Long aLong, Long deliveryId, Long id);

}
