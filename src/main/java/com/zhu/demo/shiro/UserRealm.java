package com.zhu.demo.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.demo.entity.UserEntity;
import com.zhu.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 17:33
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行授权逻辑");
        UserEntity dbUser = (UserEntity)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> permsSet = new HashSet<>();
        permsSet.add(dbUser.getPerms());
        info.setStringPermissions(permsSet);
        return info;
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
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;

        // 实际应用中可通过UserService获取本地用户和密码信息
        UserEntity dbUser = userService.getOne(new QueryWrapper<UserEntity>().eq("name", token.getUsername()));

        //账号或密码错误及常见异常： UnknownAccountException，IncorrectCredentialsException，LockedAccountException
        // 1、判断用户名
        if(ObjectUtils.isEmpty(dbUser)){
            //返回null的效果等价于抛出UnknownAccountException异常
            return null;
        }
        // 2、判断密码
        // 此处第一个参数传入user后，授权方法中就可以获取到用户信息了
        return new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), getName());
    }
}
