package com.pixiv.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pixiv.authority.entity.Permission;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService  {

    public List<Role> findAll();

    public Role findRoleAndRouterByRoleId(String id);

    public void assignRouters(String id,String []routerIds);

    public void deleteCache();


}
