package com.team24.express.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.team24.express.common.AccountConst;
import com.team24.express.common.Result;
import com.team24.express.entity.Account;
import com.team24.express.entity.User;
import com.team24.express.service.AccountRoleService;
import com.team24.express.service.EmployeeService;
import com.team24.express.service.UserService;
import com.team24.express.util.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: LoginController
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Tag(name = "AccountController", description = "账户相关(用户、员工)、用户注册")
@RestController()
public class AccountController {

    /**
     * 不用写登录控制器，因为url已经被security监控了
     */
    @Resource
    UserService userService;
    @Resource
    EmployeeService employeeService;
    @Resource
    AccountRoleService accountRoleService;
    @Resource
    JwtUtils jwtUtils;

    /**
     * 前端用于获取用户信息，包括roles用的
     * api/account.js
     * <p>
     * 将 user 和 employee 统一起来
     *
     * @param accountType
     * @param token
     * @return
     */
    @Operation(summary = "返回给前端用户信息", description = "vue-element-admin前端框架中，用户登陆后再次通过token获取用户角色等信息")
    @Parameters(value = {
            @Parameter(name = "accountType", description = "登录的账户类型，前端登录页面可以选择。用户登录(user),员工登录(employee)"),
            @Parameter(name = "token", description = "登录成功的token，这里是jwt形式的token")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "50014", description = "token失效", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "用户不存在", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "200", description = "登录成功，返回用户角色等信息", content = @Content(mediaType = "application/json")
                            , extensions = @Extension(name = "data", properties = {
                            @ExtensionProperty(name = "username", value = "vividbobo"),
                            @ExtensionProperty(name = "avatar", value = "avatar path"),
                            @ExtensionProperty(name = "roles", value = "[ROLE_USER, ROLE_SUPER_ADMIN]")}))
            }
    )
    @GetMapping("/account/info")
    public Result info(String accountType, String token) {

        if (token == null || token.equals("")) {
            Result result = new Result();
            result.setCode(Result.HttpStatus.TOKEN_EXPIRED);
            result.setMessage("登陆失败，token失效");
            return result;
        } else {
            DecodedJWT jwt = jwtUtils.resolveJwt(token);
            UserDetails userDetails = jwtUtils.toUser(jwt);
            Account account = null;
            if (AccountConst.TYPE_USER.equals(accountType)) {
                account = userService.selectByUsername(userDetails.getUsername());
            } else if (AccountConst.TYPE_EMPLOYEE.equals(accountType)) {
                account = employeeService.selectByUsername(userDetails.getUsername());
            }

            if (account == null) {
                Result result = new Result();
                result.setCode(Result.HttpStatus.ERROR);
                result.setMessage("登陆失败，用户不存在");
                return result;
            } else {
                Result result = Result.success("登录成功");
                Map<String, Object> data = new HashMap<>();
                data.put("username", account.getUsername());
                data.put("avatar", account.getAvatar());
                data.put("introduction", "xxxxxxx");
                List<String> roleNames = accountRoleService.getRoleNamesByIdAndType(account.getId(), accountType);
                data.put("roles", roleNames);
                result.setData(data);
                return result;
            }
        }
    }

    @Operation(summary = "用户注册", description = "根据前端返回的对象添加用户",
            parameters = {
                    @Parameter(name = "user", schema = @Schema(implementation = User.class)),
            },
            responses = {
                    @ApiResponse(description = "返回注册结果消息"),
            }
    )
    @PostMapping("/register")
    public Result userRegister(@RequestBody User user) {
        if (userService.add(user)) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }
}
