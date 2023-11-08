package com.team24.express.entity;

import lombok.Data;

/**
 * @className: PageParams
 * @author: Bobby
 * @date: 10/31/2023
 **/
@Data
@Deprecated
public class PageParams {
    private Integer page;
    private Integer limit;
}
