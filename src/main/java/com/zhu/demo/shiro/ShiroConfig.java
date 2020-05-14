package com.zhu.demo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 17:31
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //添加Shiro内置过滤器 anon,authc,user,perms,role
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/checkLogin","anon");

        //过滤Swagger2相关路径
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/doc.html", "anon");
        filterMap.put("/webjars/**", "anon");

        filterMap.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //添加未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        return shiroFilterFactoryBean;
    }


    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 创建Realm
     */
    @Bean("userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }

    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

}
