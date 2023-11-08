package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Order;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: DeliveryController
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Tag(name = "DeliveryController", description = "快件、订单相关控制器")
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @PostMapping("/add")
    public Result add(Order order) {
        return null;
    }

}
