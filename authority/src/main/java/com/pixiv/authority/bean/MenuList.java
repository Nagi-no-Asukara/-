package com.pixiv.authority.bean;

import com.pixiv.authority.entity.Permission;
import com.pixiv.authority.entity.PermissionRouter;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class MenuList {

    private String name;

    private Set<PermissionRouter> routerList=new HashSet<>();
    public MenuList(String name)
    {
        this.name=name;
    }

    public MenuList(Set<PermissionRouter> routerList,String name)
    {
        this.name=name;
        this.routerList=routerList;
    }
}
