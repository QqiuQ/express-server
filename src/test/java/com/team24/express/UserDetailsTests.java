package com.team24.express;

import com.team24.express.service.SecurityService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @className: UserDetailsTests
 * @author: Bobby
 * @date: 10/17/2023
 **/
@SpringBootTest
public class UserDetailsTests {
    @Resource
    SecurityService service;

    @Test
    public void getEmployeeDetails() {

        UserDetails employeeDetails = service.getEmployeeDetails("bobby");
        UserDetails normalUesrDetails = service.getEmployeeDetails("normaluser");
        UserDetails userDetails = service.getUserDetails("vividbobo");

        System.out.println(employeeDetails);
        System.out.println(normalUesrDetails);
        System.out.println(userDetails);
    }
}
