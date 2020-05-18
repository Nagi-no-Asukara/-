package com.pixiv.imgdetail.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {

    private Integer id;

    private String name;

    private String username;

    private String avatar;

    private Integer sex;

    private List<Image> userLoved;

    private List<Image> userUpload;
}
