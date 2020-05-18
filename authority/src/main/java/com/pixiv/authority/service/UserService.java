package com.pixiv.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.entity.User;

import java.util.List;

public interface UserService  {

    public void deleteCache();

    public void assignRoles(String userId, List<String> roleIds);

    public String login(String username,String password);

    public User getUserAndRoleById(String id);

    public User insertUser(User user);

    public List<PermissionRouter> getRouter(User user);

    public List<User> findAll();

    public void delete(String id);



}
