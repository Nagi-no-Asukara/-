package com.pixiv.authority.realm;

import com.pixiv.authority.entity.Permission;
import com.pixiv.authority.entity.Role;
import com.pixiv.authority.entity.User;
import com.pixiv.authority.mapper.PermissionMapper;
import com.pixiv.authority.mapper.RoleMapper;
import com.pixiv.authority.mapper.UserMapper;
import com.pixiv.authority.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//用户登录realm
public class CustomRealm extends AuthorizingRealm {



    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    public void setName(){
        super.setName("customRealm");//一般命名规则
    }

    @Override  //用于确定采用哪一种认证方式
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取安全数据 就是你在认证中提交的那个  从HttpSession中提取
        User user=(User)principalCollection.getPrimaryPrincipal();

        //Set<String> perms=new HashSet<>();//所有角色
        Set<String> roles=new HashSet<>();//所有权限



        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        for(Role role:user.getRoleList())
        {
           roles.add(role.getName());
        }

        info.setRoles(roles);
        //info.setStringPermissions(perms);

        return info;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken=(UsernamePasswordToken)token;


        String username=userToken.getUsername();
        String password=new String(userToken.getPassword());

        //user信息不一定要包含全部 把一些要用的写入redis即可
        User user=userMapper.SelectUserAndRoleByUsername(username);


        if(user==null)
        {
            return  null;//return null的话就会自动抛出异常
        }

        if(!user.getPassword().equals(password))
            return null;

        //密码认证它自动会做   同时把user信息放在了HttpSession中进行保存
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }

    /**
     * 清除权限缓存
     */
    private void clearCachedAuthorization() {
        //清空权限缓存
        clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
