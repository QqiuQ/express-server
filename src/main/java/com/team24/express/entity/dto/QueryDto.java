package com.team24.express.entity.dto;

import java.io.Serializable;

/**
 * @className: QueryDto
 * @author: Bobby
 * @date: 10/31/2023
 * 查询语句封装对象
 **/
public class QueryDto implements Serializable {
    public Integer page;
    public Integer limit;
}
