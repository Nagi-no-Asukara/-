package com.pixiv.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pixiv.authority.entity.Permission;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.entity.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface  PermissionService extends IService<Permission> {

     public void save(Map<String,Object> map) throws Exception;

     public void update(Map<String,Object>map) throws Exception;

     public Map<String,Object> findById(String id);

     public void delete(String id);

     public List<Role> selectPermission(List<Role> roleList);

     public Role selectPermission(Role role);

     public  List<PermissionRouter> selectRouterByPermission(List<Permission> permissionList);

     public Set<Permission> selectPointByToken(String token);

}
