package org.wzxy.breeze.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-04
 */
@Configuration
public class shiroConfig {

    //不加这个注解不生效，具体不详
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return  defaultAAP;
    }

    //将自己的验证方式加入容器
    @Bean
    public  myRealm  myshiroRealm(){
        myRealm myRea= new myRealm();
        return  myRea;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager
                = new DefaultWebSecurityManager();
        securityManager.setRealm(myshiroRealm());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //未登录
        shiroFilterFactoryBean.setLoginUrl("/system/notLogin");
        //shiroFilterFactoryBean.setLoginUrl("/pages/login.html");
        Map<String,String> map = new LinkedHashMap<String, String>();
        //登录
        map.put("/system/login", "anon");
        map.put("/user/toregister", "anon");
        map.put("/personInfo/addPerson", "anon");
        map.put("/pages/register.html", "anon");
        //对静态资源放行
        map.put("/images/**","anon");
        map.put("/assets/**","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/json/**","anon");
        //退出
        map.put("/logout", "logout");
        //对所有用户认证
       map.put("/**", "authc");
        //首页
       // shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
         return  shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
