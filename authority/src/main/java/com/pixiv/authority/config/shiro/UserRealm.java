package com.pixiv.authority.config.shiro;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pixiv.authority.entity.User;
import com.pixiv.authority.mapper.UserMapper;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//自定义realm

public class UserRealm extends AuthorizingRealm {

    @Resource
    UserMapper userMapper;

    /**
     * 自定义realm名
     * @param name
     */
    public void setName(String name){
           super.setName("userRealm");
    }

    //授权
    /*
     进入页面它就会进行授权
     prCol包含了所以已认证的安全数据
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //授权交给jwtRealm去做
        return new SimpleAuthorizationInfo();
    }

    /*认证
      只要登录就会走这个方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");

        UsernamePasswordToken userToken=(UsernamePasswordToken)token;
        Subject subject=SecurityUtils.getSubject();

        String username=userToken.getUsername();

        //User user=userMapper.selectByUsername(username);

        /*if(user==null)
        {
            return  null;//return null的话就会自动抛出异常
        }*/
        //密码认证它自动会做
        // 可以设置如何加密 最常见的md5 或者md5盐值加密
        clearCachedAuthorization();

        return new SimpleAuthenticationInfo(username,"123",getName());

    }

    /**
     * 清除权限缓存
     */
    private void clearCachedAuthorization() {
        //清空权限缓存
        clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
