package com.pixiv.authority.controller;


import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class AuthorityController {

    @RequestMapping("/user/update")
    public String updateImage()
    {
        return  "user/add";
    }

    @RequestMapping("/")
    public String Image()
    {
        return  "test";
    }

    @RequestMapping("/tologin")
    public String login()
    {
        return  "login";
    }

    @RequestMapping("/login")
    public String login(String username,String password)
    {
       Subject subject= SecurityUtils.getSubject();
       //封装用户登录数据  token对象可称为令牌
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
      try {

          subject.login(token);//执行登录方法
          return "test";
      }
      catch (UnknownAccountException e)
      {
          System.out.println("用户名不存在");
          return "login";
      }
      catch (IncorrectCredentialsException e)
      {
          System.out.println("密码错误");
          return "login";
      }

    }






}
