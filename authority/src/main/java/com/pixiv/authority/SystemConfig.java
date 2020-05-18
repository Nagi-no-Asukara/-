package com.pixiv.authority;

import com.pixiv.authority.config.JWT.JWTInterceptor;
import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SystemConfig extends WebMvcConfigurationSupport {


    @Autowired
    JWTInterceptor jwtInterceptor;


   /* @Override
    protected void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") //指定拦截地址
                .excludePathPatterns("/user/login","/user/register");//不拦截的地址
    }*/

}
