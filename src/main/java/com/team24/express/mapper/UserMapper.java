package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team24.express.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @className: UserMapper
 * @author: Bobby
 * @date: 10/10/2023
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // @Select()写法
    @Select("select * from user where username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Select("select * from user")
    IPage<User> getAllUsers(Page<User> page);

    IPage<User> queryUsersByPage(@Param("page") Page<User> page, @Param("user") User user);

}
