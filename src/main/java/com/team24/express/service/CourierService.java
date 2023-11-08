package com.team24.express.service;

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
}
