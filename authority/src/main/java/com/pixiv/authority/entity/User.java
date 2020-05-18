package com.pixiv.authority.entity;


import lombok.Data;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.List;

@Data
// redis-shiro 插件包提供的接口 可以指定数据的key是啥
public class User implements Serializable {

    String id;

    String username;

    String password;

    String name;

    String avatar;

    List<Role> roleList;


    //public String getAuthCacheKey() { return this.id; }
}
