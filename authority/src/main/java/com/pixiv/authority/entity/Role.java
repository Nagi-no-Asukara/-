package com.pixiv.authority.entity;

import lombok.Data;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data  //AuthCachePrincipal
public class Role implements Serializable {


    String id;

    String name;


    List<PermissionRouter> permissionRouterList;//角色与权限多对多


    /*public String getAuthCacheKey() {
        return null;
    }*/
}
