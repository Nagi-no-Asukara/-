package com.pixiv.manager.entity;

import lombok.Data;

@Data
public class UserInfo {

    private Integer id;

    private String name;

    private String username;

    private String avatar;

    private Integer sex;
}
