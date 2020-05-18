package com.pixiv.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.authority.entity.PermissionRouter;
import com.pixiv.authority.entity.Role;
import com.pixiv.authority.mapper.PermissionRouterMapper;
import com.pixiv.authority.mapper.RoleMapper;
import com.pixiv.authority.mapper.UserMapper;
import com.pixiv.authority.service.PermissionService;
import com.pixiv.authority.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionRouterMapper permissionRouterMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "roleList",allEntries = true),@CacheEvict(cacheNames = "role",allEntries = true)})
    public void deleteCache(){
        return;
    }

    @Override
   // @Cacheable(cacheNames = "roleList" )
    public List<Role> findAll() {
        return roleMapper.getAll();
    }


    @Transactional
    //@CacheEvict(cacheNames = "role",key = "#id")
    public void assignRouters(String id,String []routerIds){

        permissionRouterMapper.deletePermissionByRoleId(id);

        for(String routerId:routerIds)
            permissionRouterMapper.insertPermission_Role(id,routerId);
    }

    @Override
    //@Cacheable(cacheNames = "role",key="#id")
    public Role findRoleAndRouterByRoleId(String id) {
        return roleMapper.selectPermissionRouterById(id);
    }

    //@Cacheable(cacheNames = "image",key="#id")
}
