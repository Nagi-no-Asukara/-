package com.pixiv.userspace;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan(basePackages = "com.pixiv.userspace.dao.mapper")
@Import(FdfsClientConfig.class)
public class UserspaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserspaceApplication.class, args);
    }

}
