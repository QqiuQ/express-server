package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.common.RoleConst;
import com.team24.express.controller.RoleController;
import com.team24.express.entity.Role;
import com.team24.express.mapper.RoleMapper;
import com.team24.express.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: RoleServiceImpl
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;  // 注入依赖 Mapper

    @Override
    public boolean insert(Role role) {
        if (roleMapper.insert(role) > 0) return true;
        return false;
    }

    @Override
    public boolean edit(Role role) {
        if (roleMapper.updateById(role) > 0) return true;
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (roleMapper.deleteById(id) > 0) return true;
        return false;
    }

    @Override
    public List<Role> selectEmployeeRoles() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.ne("name", RoleConst.USER);
        return roleMapper.selectList(wrapper);
    }



}
