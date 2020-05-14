package com.zhu.demo.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 17:33
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行授权逻辑");
        return null;
    }


    /**
     * 执行认证逻辑
     * @param arg0
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        log.info("执行认证逻辑");
        // 假设用户名和密码，实际应用中可通过UserService获取本地用户和密码信息
        String username = "admin";
        String password = "123456";

        //账号或密码错误及常见异常： UnknownAccountException，IncorrectCredentialsException，LockedAccountException
        // 1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;
        if(!token.getUsername().equals(username)){
            //返回null的效果等价于抛出UnknownAccountException异常
            return null;
        }
        // 2、判断密码
        // 此处第一个参数传入user后，授权方法中就可以获取到用户信息了
        return new SimpleAuthenticationInfo("",password,"");
    }
}
