package com.pixiv.sso.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PixivUser {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Date created;

    private String phone;

}