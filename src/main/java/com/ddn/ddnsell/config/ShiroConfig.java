package com.ddn.ddnsell.config;

import com.ddn.ddnsell.entity.Permission.AuthInfo;
import com.ddn.ddnsell.service.AuthInfoService;
import com.ddn.ddnsell.shiro.DdnShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */

//@Configuration
//@Import(ShiroManager.class)
//public class ShiroConfig {
//
//    @Resource
//    private AuthInfoService authInfoService;
//
//    @Bean
//    @DependsOn("securityManager")
//    @ConditionalOnMissingBean
//    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
//        securityManager.setRealm(ddnShiroRealm());
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        //拦截器
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
//
//        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//
//        shiroFilterFactoryBean.setLoginUrl("/seller/login");
//        shiroFilterFactoryBean.setSuccessUrl("/seller/product/list");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/seller/unauthor");
//
//        //配置权限
////*//*        List<Permission> list = resourceService.findAll();
//////        for (Permission resource : list) {
//////            filterChainDefinitionMap.put(resource.getSourceUrl(), "perms[" + resource.getSourceKey() + "]");
//////        }*//*
//
//        //先配置不会拦截 因为拦截是配置是有顺序的
//        filterChainDefinitionMap.put("/static/**", "anon");
//        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/seller/user/unlogin","logout");
//        filterChainDefinitionMap.put("/seller/user/**","anon");
//        filterChainDefinitionMap.put("/seller/login","anon");
//       // filterChainDefinitionMap.put("/sell/seller/user/**","anon");
//        //filterChainDefinitionMap.put("/sell/seller/login","anon");
//        filterChainDefinitionMap.put("/seller/product/list","perms[product_list]");
//
//        List<AuthInfo> authInfoList = authInfoService.findAllList();
//        for (AuthInfo authInfo:authInfoList
//        ) {
//            if (authInfo.getAuthIsmenu() != 1){
//                filterChainDefinitionMap.put(authInfo.getAuthPage(), "perms[" + authInfo.getAuthCode() + "]");
//            }
//
//        }
//        filterChainDefinitionMap.put("/seller/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        return securityManager;
//    }
//
//    /**
//     * 凭证匹配器
//     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//     * ）
//     * @return
//     */
////    @Bean
////    public HashedCredentialsMatcher hashedCredentialsMatcher(){
////        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
////        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
////        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
////        return hashedCredentialsMatcher;
////    }
//    @Bean(name = "ddnShiroRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    @ConditionalOnMissingBean
//    public DdnShiroRealm ddnShiroRealm(){
//        DdnShiroRealm ddnShiroRealm = new DdnShiroRealm();
//       // ddnShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return ddnShiroRealm;
//    }
//
////    @Bean(name="simpleMappingExceptionResolver")
////    public SimpleMappingExceptionResolver
////    createSimpleMappingExceptionResolver() {
////        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
////        Properties mappings = new Properties();
////        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
////        mappings.setProperty("UnauthorizedException","403");
////        r.setExceptionMappings(mappings);  // None by default
////        r.setDefaultErrorView("error");    // No default
////        r.setExceptionAttribute("ex");     // Default is "exception"
////        //r.setWarnLogCategory("example.MvcLogger");     // No default
////        return r;
////    }
//
//}
