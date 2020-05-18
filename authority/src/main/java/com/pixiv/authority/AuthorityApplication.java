package com.pixiv.authority;

import com.pixiv.authority.config.JWT.JwtToken;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.pixiv.authority.mapper")
@EnableConfigurationProperties
@EnableFeignClients
@EnableCaching
public class AuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class, args);
    }

    @Bean
    public JwtToken jwtUtils(){
        return new JwtToken();
    }

}
