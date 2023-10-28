package com.bobby.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.securityjwt.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: PermissionMapper
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> getByDomain(String domain);

    Permission getByDomainAndPermission(String domain, String permission);
}
