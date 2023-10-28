package com.bobby.securityjwt.service.impl;

import com.bobby.securityjwt.mapper.PermissionMapper;
import com.bobby.securityjwt.mapper.RoleMapper;
import com.bobby.securityjwt.service.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: PermissionServiceImpl
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    PermissionMapper permissionMapper;

}
