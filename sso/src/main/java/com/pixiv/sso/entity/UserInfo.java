package com.pixiv.sso.entity;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;

    private String username;

    private String name;

    private String avatar;

    private Integer sex;


}