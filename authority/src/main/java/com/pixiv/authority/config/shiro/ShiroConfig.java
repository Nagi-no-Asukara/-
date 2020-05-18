package com.pixiv.authority.config.shiro;

import com.pixiv.authority.realm.CustomRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    //redis过期时间
    private Integer timeout=3600*1000;

    @Value("${security.imageRole}")
    private  String ImageRole;

    @Value("${security.userRole}")
    private  String userRole;

    @Value("${security.securityRole}")
    private  String securityRole;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    private static String loginURL="localhost:8079/security/login";
    //1:创建realm对象
    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }


    //2:DeafultWebSecurityManager 创建manager管理器
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getdefaultWebSecurityManager(@Qualifier("customRealm")CustomRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //多reaml 授权原理 只要有一个realm里面有这个角色或者资源就代表有这个权限
        securityManager.setRealm(userRealm);
        //securityManager.setRealm(jwtRealm);

        //使用自定义的会话管理器
        securityManager.setSessionManager(sessionManager());
        //使用自定义的缓存管理器
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    //3:ShiroFilterFactoryBean
    /**
     *
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){

        //创建过滤器工厂
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //设置登录路径 没有登录时会跳转到登录页面  但在
        //bean.setLoginUrl("/user/login");
        //设置未授权时会跳转到的界面
        bean.setUnauthorizedUrl("/error");

         /*
         添加shiro内置过滤器
         anon：无需认证
         authc：必须认证
         perms：拥有某个资源权限才能访问（拥有某字符串)
         role：拥有某个角色才能访问
         */
        Map<String,String> filterMap=new LinkedHashMap<>();

        /*在这里统一设置所有权限服务的拦截路径*/



        //前面一定要有一个/号 否则路径匹配会失败
        //同时 自己定义的LoginUrl和UnauthorURL也要再配置。。。
        filterMap.put("/user/home","anon");
        //filterMap.put("/user/login","anon");
        filterMap.put("/manage/**","roles[Image]");
       // filterMap.put("/security/roles/**","roles[Miuna]");

        //filterMap.put("/user/*","authc");
        //filterMap.put("/**","authc");

        //filterMap.put("/user/add","perms[user:add]");
        //filterMap.put("/user/add","roles[user:add]");
        bean.setFilterChainDefinitionMap(filterMap);

        return  bean;
    }


    /*
        接下来是相关配置
     */

    //开启对shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    /**
     * redisManager
     *
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        // 配置过期时间
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * redisSessionDAO
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());

        return redisSessionDAO;
    }

    //会话管理器
    public DefaultWebSessionManager sessionManager(){
        CustomSessionManager sessionManager=new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //禁用cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //也可以禁用url重写
        return sessionManager;
    }

    //缓存管理器
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


}

