package com.team24.express.controller;

import com.team24.express.common.AjaxResult;
import com.team24.express.common.Result;
import com.team24.express.entity.Station;
import com.team24.express.entity.User;
import com.team24.express.service.StationService;
import com.team24.express.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Tag(name = "StationController", description = "站点相关接口")
@Slf4j
@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    StationService stationService;
    @PostMapping()
    public Result list() {

        List<Station> stationList = stationService.queryList();
        Result result = Result.success("查询成功");
        result.setData(stationList);
        return result;
    }
    @PostMapping("/add")
    public Result add(@RequestBody Station station) {
        stationService.insert(station);
        Result result = Result.success("查询成功");
        result = list();
        return result;
    }
    @PostMapping("/edit")
    public Result edit(@RequestBody Station station) {
        System.out.println(station);
        int res = stationService.update(station);
        stationService.update(station);

        Result result = Result.success("查询成功");
        result = list();
        return result;
    }
    @PostMapping("/deleteone")
    public Result delete(@RequestBody Long id) {
        boolean num = stationService.deleteById(id);
        System.out.println(id);
        if (num){
            Result result = Result.success("删除成功");
            result = list();
            return result;
        }
        else{
            Result result = Result.error("删除失败");
            return result;
        }
    }
    @PostMapping("/deletemany")
    public Result deletemany(@RequestBody List<Long> ids) {
        boolean num = stationService.deleteBatchIds(ids);
        if (num){
            Result result = Result.success("删除成功");
            result = list();
            return result;
        }
        else{
            Result result = Result.error("删除失败");
            return result;
        }
    }
}
