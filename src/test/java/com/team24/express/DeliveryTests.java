package com.team24.express;

import com.team24.express.entity.Delivery;
import com.team24.express.entity.Order;
import com.team24.express.mapper.DeliveryMapper;
import com.team24.express.util.SnowflakeIdGenerator;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @className: OrderTests
 * @author: Bobby
 * @date: 11/9/2023
 **/
@SpringBootTest
public class DeliveryTests {

    @Resource
    DeliveryMapper deliveryMapper;
    @Resource
    SnowflakeIdGenerator idGenerator;

    @Test
    void createOrders() {
        Delivery delivery = new Delivery();
        delivery.setExpressNumber(String.valueOf(idGenerator.nextId()));
        delivery.setSenderName("黄");
        delivery.setSenderPhone("88888811");
        delivery.setSenderAddress("福州大学");
        delivery.setRecipientName("五");
        delivery.setRecipientPhone("1788");
        delivery.setRecipientAddress("上海");
//        delivery.setCourierCode("888844");
//        delivery.setCourierName("li");
//        delivery.setCourierPhone("1313333333");

        Assert.assertTrue(deliveryMapper.insert(delivery) > 0);

    }
}
