package com.bobby.securityjwt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

@SpringBootTest
public class UserMapperTests {
    @Resource
    UserMapper userMapper;

    @Test
    void delete() {
        Long id = 1711636588006879234L;
        Assert.assertTrue(userMapper.deleteById(id) > 0);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setUsername("vividbobo");
        user.setPassword("123456");
        Assert.assertTrue(userMapper.insert(user) > 0);
        System.out.println("insert user success");
    }

    @Test
    void testUpdate() {
        User user = userMapper.selectByUsername("vividbobo");
        Assert.assertNotNull(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.updateById(user);
        User newUser = userMapper.selectByUsername("vividbobo");
        System.out.printf("new password: ", newUser.getPassword());
    }

    @Test
    void BCryptValid() {
        String clearPwd = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(clearPwd);
        System.out.println(encoder.matches(clearPwd, encodedPwd));
    }

    @Test
    void createTestUsers() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("user#" + i);
            user.setPassword("user#" + i);
            Assert.assertTrue(userMapper.insert(user) > 0);
            System.out.println(String.format("insert user#{%d} successful", i));
        }
    }

    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
                .like("username", "user"));
        Assert.assertSame(page, userIPage);
        System.out.println("Total Records: " + userIPage.getTotal());
        System.out.println("Total Pages: " + userIPage.getCurrent());
        System.out.println("size in Page: " + userIPage.getSize());
        System.out.println("records: " + userIPage.getRecords());
    }

    @Test
    public void selectByUsername() {
        User user = userMapper.selectByUsername("vividbobo");
        if (Objects.isNull(user)) {
            System.out.println("用户不存在");
        } else {
            System.out.println("username: " + user.getUsername());
            System.out.println("password: " + user.getPassword());
        }

    }

}
