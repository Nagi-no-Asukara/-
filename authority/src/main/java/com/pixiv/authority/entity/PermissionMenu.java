package com.pixiv.authority.entity;

import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * Author:xzengsf
 * Date:2018/3/22 10:24
 * Description: 菜单权限实体类
 */

@Data
public class PermissionMenu implements Serializable {
    private static final long serialVersionUID = -1002411490113957485L;

    /**
     * 主键
     */

    private String id;

    //展示图标
    private String menuIcon;

    //排序号
    private String menuOrder;

    private String title;



    private List<PermissionRouter> routerList;
}