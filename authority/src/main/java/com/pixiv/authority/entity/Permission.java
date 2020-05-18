package com.pixiv.authority.entity;

import lombok.Data;

@Data
public class Permission {


    private String id;

    private String name;
    //父权限的id  比如管理员菜单的id是1 那么所有管理员菜单下的次级按钮pid为1
    private Integer pid;

    //是否可见
    private Integer enVisible;

    //权限编码
    private String code;

    //1为路由 2为按钮 3为api
    private Integer type;

}
