package com.pixiv.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.service.UserService;
import com.pixiv.authority.entity.Role;
import com.pixiv.authority.entity.User;
import com.pixiv.authority.mapper.RoleMapper;
import com.pixiv.authority.mapper.UserMapper;
import com.pixiv.authority.config.JWT.JwtToken;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    JwtToken jwtToken;

    @Override
    @Cacheable(cacheNames = "userList")
    public List<User> findAll() {
        return userMapper.findAll();
    }


    @Override
    @Cacheable(cacheNames="user",key = "#id")
    public User getUserAndRoleById(String id) {

        return userMapper.SelectUserAndRoleById(id);
    }

    @Override
    @CacheEvict(cacheNames = "user",allEntries=true)
    public void deleteCache() {
        return;
    }

    /*
         给用户分配角色
        */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "user",key = "#id")
    public void assignRoles(String id, List<String> roleIds) {
        roleMapper.deleteRoleByUserId(id);

        for(String str:roleIds)
            roleMapper.insertUser_Role(id,str);
    }

    @CachePut(value = "user",key = "#user.id")
    public User insertUser(User user){
        userMapper.insert(user);
        return user;
    }


    @Override
    @CacheEvict(cacheNames = "user",key = "#id")
    public void delete(String id) {
          userMapper.deleteById(id);
    }


    @Override
    public String login(String username, String password) {

        User user=userMapper.selectByUsername(username);
        if (user==null||!user.getPassword().equals(password))
            return null;

        String token= jwtToken.createJwt(user.getId(),user.getUsername(),null);
        return token;
    }


    //这个影响范围太大了 就不缓存了
    public List<PermissionRouter> getRouter(User user) {

        List<PermissionRouter> list=new ArrayList<>();
         user=userMapper.SelectUserAndRoleById(user.getId());
         for(Role role:user.getRoleList())
         {
             roleMapper.selectPermissionRouterById(role.getId());
         }
        return list;

    }




}
