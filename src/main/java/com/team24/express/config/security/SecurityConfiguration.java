package com.team24.express.config.security;

import com.team24.express.common.AccountConst;
import com.team24.express.common.AjaxResult;
import com.team24.express.common.Const;
import com.team24.express.common.Result;
import com.team24.express.config.security.filter.JwtAuthenticationFilter;
import com.team24.express.config.security.filter.RequestLogFilter;
import com.team24.express.config.security.userdetails.AccountDetailService;
import com.team24.express.config.security.userdetails.AccountDetails;
import com.team24.express.entity.Account;
import com.team24.express.entity.Employee;
import com.team24.express.entity.User;
import com.team24.express.service.EmployeeService;
import com.team24.express.service.UserService;
import com.team24.express.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * SpringSecurity相关配置
 */
@Slf4j
@Configuration
@EnableMethodSecurity(securedEnabled = true)    // 开启注解权限配置
public class SecurityConfiguration {

    @Resource
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    RequestLogFilter requestLogFilter;

//    @Resource
//    AccountAuthenticationFilter accountAuthenticationFilter;

    @Resource
    JwtUtils utils;

    @Resource
    UserService userService;

    @Resource
    EmployeeService employeeService;


    @Resource
    AccountDetailService accountDetailService;

    //    /**
//     * 是否开启 hasPermission 注解的自定义校验
//     *
//     * @param employeePermissionEvaluator
//     * @return
//     */
//    @Bean
//    static MethodSecurityExpressionHandler expressionHandler(EmployeePermissionEvaluator employeePermissionEvaluator) {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(employeePermissionEvaluator);
//        return expressionHandler;
//    }
//    @Bean
//    public SecurityFilterChain employeeFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .securityMatcher("/employee/**")
//                .userDetailsService(employeeDetailService)
//                .authorizeHttpRequests(conf -> conf
//                                .requestMatchers("/employee/login").permitAll() //员工登录接口放行
//                                .anyRequest().permitAll()
//                        /**
//                         * 哪些角色可以访问
//                         * 1.普通员工
//                         * 2.快递员
//                         * 3.站点管理员
//                         * 4.超级管理员
//                         *
//                         * 不同角色访问哪些接口
//                         * 可以通过对Controller的具体方法添加角色验证注解
//                         */
//                )
//                .formLogin(conf -> conf
//                        .loginProcessingUrl("/employee/login")
//                        .failureHandler(this::handleFailure)
//                        .successHandler(this::handleEmployeeLoginSuccess)
//                        .permitAll()
//                )
//                .logout(conf -> conf
//                        .logoutUrl("/employee/logout")
//                        .logoutSuccessHandler(this::onLogoutSuccess)
//                )
//                .exceptionHandling(conf -> conf
//                        .accessDeniedHandler(this::handleProcess)
//                        .authenticationEntryPoint(this::handleProcess)
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(conf -> conf
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(requestLogFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthenticationFilter, RequestLogFilter.class);   // 验证token
//
//        return httpSecurity.build();
//    }

    /**
     * 针对于 SpringSecurity 6 的新版配置方法
     *
     * @param http 配置器
     * @return 自动构建的内置过滤器链
     * @throws Exception 可能的异常
     */
    @Bean
    public SecurityFilterChain accountFilterChain(HttpSecurity http) throws Exception {
        return http
                .userDetailsService(accountDetailService)
                .authorizeHttpRequests(conf -> conf
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .anyRequest().permitAll()
                        // hasAnyRole 会自动在role名称前添加"ROLE_"
                        // 注意自己数据库中的 role
                )
                .formLogin(conf -> conf
                        // 登录接口，不需要自己再写controller
                        // 登录采用Params username & password
                        .loginProcessingUrl("/login")
                        .failureHandler(this::handleFailure)
                        .successHandler(this::handleLoginSuccess)
                        .permitAll()
                )
                .logout(conf -> conf
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf -> conf
                        .accessDeniedHandler(this::handleProcess)
                        .authenticationEntryPoint(this::handleProcess)
                )

                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(requestLogFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, RequestLogFilter.class)   // 每次请求时，验证是否有token
                .build();
    }

    /**
     * 将多种类型的Handler整合到同一个方法中，包含：
     * - 登录成功
     * - 登录失败
     * - 未登录拦截/无权限拦截
     *
     * @param request                   请求
     * @param response                  响应
     * @param exceptionOrAuthentication 异常或是验证实体
     * @throws IOException 可能的异常
     */
    private void handleProcess(HttpServletRequest request,
                               HttpServletResponse response,
                               Object exceptionOrAuthentication) throws IOException {
        response.setContentType(Const.CONTENT_TYPE);
        PrintWriter writer = response.getWriter();
        if (exceptionOrAuthentication instanceof AccessDeniedException exception) { // 访问拒绝
            /**
             * 角色认证不通过时，可能产生访问拒绝
             */
            writer.write(AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, exception.getMessage()).asJsonString());
        } else if (exceptionOrAuthentication instanceof Exception exception) {
            // 异常
            writer.write(AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, exception.getMessage()).asJsonString());
        } else if (exceptionOrAuthentication instanceof Authentication authentication) {
            // 据此判断是普通用户还是员工
            log.info(authentication.getPrincipal().toString());


            // 认证
            UserDetails authUser = (UserDetails) authentication.getPrincipal();
            com.team24.express.entity.User user = userService.selectByUsername(authUser.getUsername());
            // 利用用户名和ID生成token
            String jwt = utils.createJwt(authUser, user.getUsername(), user.getId());
            if (jwt == null) {
                writer.write(AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, "登录验证频繁，请稍后再试").asJsonString());
            } else {
                // token 写到Header
                response.setHeader(Const.HEADER, "Bearer " + jwt);

                AjaxResult ajax = AjaxResult.success("登录成功");
                ajax.put("username", user.getUsername());
                ajax.put("token", jwt);
                ajax.put("expire", utils.expireTime());
                writer.write(ajax.asJsonString());
            }
        }
    }

    /**
     * 退出登录处理，将对应的Jwt令牌列入黑名单不再使用
     *
     * @param request        请求
     * @param response       响应
     * @param authentication 验证实体
     * @throws IOException 可能的异常
     */
    private void onLogoutSuccess(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader(Const.HEADER);
        if (utils.invalidateJwt(authorization)) {   // 登出时，使jwt失效
            writer.write(AjaxResult.success("退出登录成功").asJsonString());
            return;
        }
        writer.write(AjaxResult.error(AjaxResult.HttpStatus.BAD_REQUEST, "退出登录失败").asJsonString());
    }

    /**
     * 处理登录失败
     */
    private void handleFailure(HttpServletRequest request,
                               HttpServletResponse response,
                               Object exceptionOrAuthentication) throws IOException {
        response.setContentType(Const.CONTENT_TYPE);
        PrintWriter writer = response.getWriter();
        if (exceptionOrAuthentication instanceof AccessDeniedException exception) { // 访问拒绝
            /**
             * 角色认证不通过时，可能产生访问拒绝
             */
            writer.write(AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, exception.getMessage()).asJsonString());
        } else if (exceptionOrAuthentication instanceof Exception exception) {
            // 异常
            writer.write(AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, exception.getMessage()).asJsonString());
        }
    }

    private void handleLoginSuccess(HttpServletRequest httpServletRequest
            , HttpServletResponse response
            , Authentication authentication) throws IOException {
        response.setContentType(Const.CONTENT_TYPE);
        PrintWriter writer = response.getWriter();
        // 认证
        AccountDetails authUser = (AccountDetails) authentication.getPrincipal();
        log.info(authUser.getAccountType());
        Account account = null;
        if (authUser.getAccountType().equals(AccountConst.TYPE_USER)) {
            account = userService.selectByUsername(authUser.getUsername());
        } else if (authUser.getAccountType().equals(AccountConst.TYPE_EMPLOYEE)) {
            account = employeeService.selectByUsername(authUser.getUsername());
        }
        if (account == null) {
            writer.write(Result.error("用户不存在").asJsonString());
        }
        // 利用用户名和ID生成token
        String jwt = utils.createJwt(authUser, account.getUsername(), account.getId());
        if (jwt == null) {
            writer.write(AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, "登录验证频繁，请稍后再试").asJsonString());
        } else {
            // token 写到Header
            String authorization = "Bearer " + jwt;
            response.setHeader(Const.HEADER, authorization);
            Result result = Result.success("登录成功");
            Map<String, Object> data = new HashMap<>();
            data.put("token", authorization);
            data.put("expire", utils.expireTime());
            data.put("accountType", authUser.getAccountType());
            result.setData(data);
            // 登录成功
            if (authUser.getAccountType().equals(AccountConst.TYPE_USER)) {
                userService.updateLastLoginTime((User) account);
            } else if (authUser.getAccountType().equals(AccountConst.TYPE_EMPLOYEE)) {
                employeeService.updateLastLoginTime((Employee) account);
            }

            writer.write(result.asJsonString());
        }
    }

//    private void handleEmployeeLoginSuccess(HttpServletRequest httpServletRequest
//            , HttpServletResponse response
//            , Authentication authentication) throws IOException {
//
//        response.setContentType(Const.CONTENT_TYPE);
//        PrintWriter writer = response.getWriter();
//
//        UserDetails authUser = (UserDetails) authentication.getPrincipal();
//        Employee employee = employeeService.selectByUsername(authUser.getUsername());
//        String jwt = utils.createJwt(authUser, employee.getUsername(), employee.getId());
//        if (jwt == null) {
//            writer.write(AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, "登录验证频繁，请稍后再试").asJsonString());
//        } else {
//            // token 写到Header
//            String authorization = "Bearer " + jwt;
//            response.setHeader(Const.HEADER, authorization);
//            Result result = Result.success("登录成功");
//            Map<String, Object> data = new HashMap<>();
//            data.put("token", authorization);
//            data.put("expire", utils.expireTime());
//            result.setData(data);
//            writer.write(result.asJsonString());
//        }
//    }


}
