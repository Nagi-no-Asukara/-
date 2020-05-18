package com.pixiv.authority;




import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;


public class ShiroTest {

    private SecurityManager manager;

    @Before
    public  void init(){

        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-test-2.ini");

        SecurityManager manager=factory.getInstance();

        //将manager绑定到当前环境上
        SecurityUtils.setSecurityManager(manager);
    }

    @Test
    public void login(){

        Subject subject=SecurityUtils.getSubject();

        String name="zhangsan";
        String password="123";
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);

        //login方法就会执行realm
        subject.login(token);

        //每一次调用这个api都会执行授权方法
        System.out.println(subject.hasRole("role1"));

        System.out.println(subject.isPermitted("user:save"));
    }
}
