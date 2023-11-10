package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.AccountRole;
import com.team24.express.entity.vo.EmployeeRoleVo;
import com.team24.express.mapper.AccountRoleMapper;
import com.team24.express.service.AccountRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: UserRoleServiceImpl
 * @author: Bobby
 * @date: 10/29/2023
 **/
@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    @Resource
    AccountRoleMapper accountRoleMapper;

    public List<String> getRoleNamesByIdAndType(Long userId, String accountType) {
        return accountRoleMapper.getRoleNamesByIdAndType(userId, accountType);
    }

    @Override
    public List<EmployeeRoleVo> getEmployeeVoListByRoleName(String roleName) {
        return accountRoleMapper.getEmployeeVoListByRoleName(roleName);
    }

    @Override
    public List<EmployeeRoleVo> getEmployeeVoListByRoleId(Integer id) {
        return accountRoleMapper.getEmployeeVoListByRoleId(id);
    }

    @Override
    public boolean changeRole(Long employeeId, Integer curRoleId, Integer newRoleId) {
        return accountRoleMapper.changeRole(employeeId, curRoleId, newRoleId) > 0;
    }

    @Override
    public boolean setRole(Long employeeId, Integer roleId) {
        return accountRoleMapper.setRole(employeeId, roleId) > 0;
    }

    @Override
    public AccountRole selectByAccountIdAndRoleId(Long accountId, Integer roleId) {
        QueryWrapper<AccountRole> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", accountId);
        wrapper.eq("role_id", roleId);
        return accountRoleMapper.selectOne(wrapper);
    }

    @Override
    public boolean add(AccountRole accountRole) {
        return accountRoleMapper.insert(accountRole) > 0;
    }
}
