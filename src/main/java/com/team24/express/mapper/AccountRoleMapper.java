package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Role;
import com.team24.express.entity.AccountRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @className: UserRoleMapper
 * @author: Bobby
 * @date: 10/29/2023
 **/
@Mapper
public interface AccountRoleMapper extends BaseMapper<AccountRole> {
    @Select("select role.* from role, account_role where account_role.account_id=#{userId} and account_role.role_id=role.id and account_role.type=#{type}")
    List<Role> getRolesByIdAndType(Long userId, String type);

    @Select("select role.name from role, account_role where account_role.account_id=#{userId} and account_role.role_id=role.id and account_role.type=#{type}")
    List<String> getRoleNamesByIdAndType(Long userId, String type);

    @Delete("delete form account_role where account_id=#{id} and type=#{type}")
    int deleteByAccountIdAndType(Long id, String type);
}
