package com.team24.express;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team24.express.common.AccountConst;
import com.team24.express.common.RoleConst;
import com.team24.express.entity.Role;
import com.team24.express.entity.User;
import com.team24.express.entity.AccountRole;
import com.team24.express.mapper.RoleMapper;
import com.team24.express.mapper.UserMapper;
import com.team24.express.mapper.AccountRoleMapper;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootTest
public class UserTests {
    @Resource
    UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    RoleMapper roleMapper;
    @Resource
    AccountRoleMapper accountRoleMapper;

    @Test
    void delete() {
        Long id = 1711636588006879234L;
        Assert.assertTrue(userMapper.deleteById(id) > 0);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setUsername("vividbobo");
        user.setPassword(passwordEncoder.encode("123456"));
        Assert.assertTrue(userMapper.insert(user) > 0);
        System.out.println("insert user success");
    }

    @Test
    void testUpdate() {
        User user = userMapper.selectByUsername("vividbobo");
        Assert.assertNotNull(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.updateById(user);
        User newUser = userMapper.selectByUsername("vividbobo");
        System.out.printf("new password: ", newUser.getPassword());
    }

    @Test
    void BCryptValid() {
        String clearPwd = "123456";
        String encodedPwd = passwordEncoder.encode(clearPwd);
        System.out.println(passwordEncoder.matches(clearPwd, encodedPwd));
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
    void updateTestUsers() {
        for (int i = 0; i < 10; i++) {
            User user = userMapper.selectByUsername("user#" + i);
            if (user == null) continue;
            user.setPassword(passwordEncoder.encode("123456"));
            Assert.assertTrue(userMapper.updateById(user) > 0);
            System.out.println(String.format("update user#{%d} successful", i));
        }
    }

    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 2);
//        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
//                .like("username", "user"));

        IPage<User> userIPage = userMapper.selectPage(page, null);
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

    @Test
    public void addUsers() {
        Role userRole = roleMapper.getRoleByRoleName(RoleConst.USER);
        for (int i = 0; i < 20; i++) {
            String username = "user_" + i;
            String password = "123456";
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setCreateTime(LocalDateTime.now());

            // 创建角色关联
            if (userMapper.insert(user) > 0) {
                User curUser = userMapper.selectByUsername(username);
                AccountRole ur = new AccountRole(curUser.getId(), userRole.getId(), AccountConst.TYPE_USER);
                Assert.assertTrue(accountRoleMapper.insert(ur) > 0);
            } else {
                Assert.assertTrue(false);   // insert user failed
            }
        }
    }

}
