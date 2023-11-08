package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.service.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/delivery")
public class CourierController {
    @Autowired
    CourierService courierService;

    /**
     * 确认妥投
     * @param id
     * @return
     */
    @PostMapping("/place")
    public Result ackDelivered(@RequestParam("id") Long id){
        courierService.ackDelivered(id);
        return Result.success("操作成功！");
    }

    /**
     * 揽件确认
     * @param id
     * @return
     */
    @PostMapping("/collect")
    public Result ackCollected(@RequestParam("id") Long id){
        courierService.ackCollected(id);
        return Result.success("操作成功！");
    }
}
