package com.pixiv.imgdetail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.pixiv.imgdetail.dao.mapper")
public class ImgdetailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImgdetailApplication.class, args);
    }

}
