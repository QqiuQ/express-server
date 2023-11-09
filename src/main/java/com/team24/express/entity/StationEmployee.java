package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: StationEmployee
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Schema(name = "StationEmployee", title = "网点员工关系表")
@Data
@TableName("station_employee")
public class StationEmployee {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)

    private Long stationId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)

    private Long employeeId;
    private String position;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
