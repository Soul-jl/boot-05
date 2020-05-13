package com.jy.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 生成shiro过滤器的工厂实例
     * */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager, CustomRolesAuthorizationFilter customRolesAuthorizationFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //在过滤器工厂中注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置多个角色过滤器or的关系
        Map<String, Filter> map = new HashMap<>();
        map.put("roles", customRolesAuthorizationFilter);
        shiroFilterFactoryBean.setFilters(map);

        // 过滤器链定义映射，就是定义拦截规则
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*
         * anon:所有url都都可以匿名访问，
         * authc:所有url都必须认证通过才可以访问;
         * 过滤链定义，从上向下顺序执行，authc 应放在 anon 下面
         * 越严格的条件越靠上
         * 越宽松的条件越靠下
         *
         * anon要放在上面
         *
         * authc要放在下面
         * */
        filterChainDefinitionMap.put("/login", "anon");
        // 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
        filterChainDefinitionMap.put("/toLogin", "anon");
        filterChainDefinitionMap.put("/imgcode", "anon");
        filterChainDefinitionMap.put("/jq/**", "anon");
        filterChainDefinitionMap.put("/echarts/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/my97/**", "anon");
        filterChainDefinitionMap.put("/wangEditor/**", "anon");
        filterChainDefinitionMap.put("/zTree/**", "anon");


        //配置授权过滤规则(权限)
        filterChainDefinitionMap.put("/book/deleteAll", "perms[/book/deleteAll1]");
        filterChainDefinitionMap.put("/book/toAdd", "perms[/book/toAdd]");
        filterChainDefinitionMap.put("/book/insertl", "perms[/book/insert]");
        filterChainDefinitionMap.put("/book/toEdit", "perms[/book/toEdit1]");
        filterChainDefinitionMap.put("/book/update", "perms[/book/update1]");
        //配置授权过滤规则(角色)
       // filterChainDefinitionMap.put("/book/toList", "roles[admin,normal]");
          filterChainDefinitionMap.put("/book/toList", "roles[超级管理员,书籍管理员]");

        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了, 位置放在 anon、authc下面
        filterChainDefinitionMap.put("/logout", "logout");
        // 所有url都必须认证通过才可以访问
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        // 配器shirot认登录累面地址，前后端分离中登录累面跳转应由前端路由控制，后台仅返回json数据, 对应LoginController中unauth请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        // 登录成功后要跳转的链接, 此项目是前后端分离，故此行注释掉，登录成功之后返回用户基本信息及token给前端
        // shiroFilterFactoryBean.setSuccessUrl("/index");

        // 未授权界面, 对应LoginController中 unauthorized 请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        //把过滤规则这个map设置到过滤器工厂中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //返回工厂实例
        return shiroFilterFactoryBean;
    }

    //安全管理器，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(RealmConfig realmConfig){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(realmConfig);        //设置realm.
        // 自定义缓存实现 使用redis
        // securityManager.setCacheManager(redisCacheManager());
        // 自定义session管理 使用redis
        //securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //生成自定义realm的实例
    @Bean
    public RealmConfig myShiroRealm(){//参数：@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hCM
        RealmConfig myShiroRealm = new RealmConfig();
        //myShiroRealm.setCredentialsMatcher(getMatcher());
        return myShiroRealm;
    }

    //生成自定义roleOr关系处理的实例
    @Bean
    public CustomRolesAuthorizationFilter roleOrFilter(){//参数：@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hCM
        CustomRolesAuthorizationFilter rolesAuthorizationFilter = new CustomRolesAuthorizationFilter();
        return rolesAuthorizationFilter;
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
