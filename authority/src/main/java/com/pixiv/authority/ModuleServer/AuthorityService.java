package com.pixiv.authority.ModuleServer;

import com.pixiv.authority.config.exception.CommonExceptionType;
import com.pixiv.authority.utils.AjaxResponse;
import com.pixiv.authority.utils.JsonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authority/ModuleService")
public class AuthorityService {


    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;


    @PostMapping("/checkPermission")
    public boolean checkPermission(String url) {

        Subject subject = SecurityUtils.getSubject();
        System.out.println(url);
        //这个api 有点nb 自动从header中取数据 然后完成一连串的授权鉴权
        return subject.isPermitted(url);

    }
}
