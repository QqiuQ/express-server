package com.bobby.securityjwt.entity.dto;

import lombok.Data;

/**
 * @className: PermissionDto
 * @author: Bobby
 * @date: 10/17/2023
 *
 * DTO Data Transfer Object 数据传输对象
 * 构造这个对象的目的是，减少空间占用
 * 例如我需要Role和Permission的部分字段
 * 通常情况下，我可以
 *      Role role;
 *      Permission permission;
 * 创建两个对象
 **/
@Data
public class PermissionDto {
    private Integer roleId;
    private Integer permissionId;
    private String roleName;
    private String domain;
    private String permission;
}
