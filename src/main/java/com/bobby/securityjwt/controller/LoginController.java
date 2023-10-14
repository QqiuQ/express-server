package com.bobby.securityjwt.controller;

import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.common.Const;
import com.bobby.securityjwt.entity.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: LoginController
 * @author: Bobby
 * @date: 10/13/2023
 **/
@RestController
public class LoginController {

    /**
     * 不用写登录控制器，因为url已经被security监控了
     */

//    @RequestMapping("/login")
//    public AjaxResult userLogin(@RequestBody UserDto userDto, HttpServletResponse response) {
//
//
//
//    }
}
