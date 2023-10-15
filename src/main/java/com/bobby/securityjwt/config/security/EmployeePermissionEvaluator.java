package com.bobby.securityjwt.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @className: EmployeePermissionEvaluator
 * @author: Bobby
 * @date: 10/15/2023
 * description: 自定义注解验证流程
 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/access/PermissionEvaluator.html
 **/
@Slf4j
@Component
public class EmployeePermissionEvaluator implements PermissionEvaluator {
    /**
     * eg:  user/list
     *      user/edit/{id}
     *      user/insert
     *      user/delete/{id}
     * 定义这个权限的目的是，使得授权进一步具体化。举个不太恰当的例子，例如，角色为站点管理员拥有 user/list权限，而没有insert, eidt和delete权限
     * 而 超级管理员 拥有上述的所有权限。在具体方法上可添加注解 @PreAuthorize("hasPermission(xxx,xxx)")
     *
     *      @PreAuthorize("hasPermission('user','edit')")
     *      @RequestMapping("/user/{id}")
     *      public AjaxResult edit(@PathVariable("id") Long id){
     *          ...
     *      }
     *
     * 然后在下面的方法内验证，authentication 是否拥有域为user的edit权限
     *
     * 因此，稍稍复杂化一些，同时也需要将角色对特定域所拥有的权限也存入数据库
     *
     * Quesiton: 如何读取这些权限呢? 因为 authentication 只拥有UserDetails的信息
     *          1. 根据角色从数据库读取权限? 相应的就要引入 PermissionService
     *          2.
     *
     *
     * @param authentication    会自动传过来当前登录的用户
     * @param targetDomainObject    目标域，例如上面url中的 user
     * @param permission    权限：例如上面中的 edit
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.info("in EmployeePermissionEvaluator -> hasPermission");
        log.info("----> " + authentication.getPrincipal().toString());
        log.info("----> " + targetDomainObject.toString());
        log.info("----> " + permission.toString());


        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
