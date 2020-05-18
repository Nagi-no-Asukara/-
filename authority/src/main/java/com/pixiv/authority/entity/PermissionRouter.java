package com.pixiv.authority.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("permission_router")
public class PermissionRouter {

    private String id;

    private String name;

    private String title;

    private String path;

    private String url;

    private String component;

    private Integer menuId;





}
