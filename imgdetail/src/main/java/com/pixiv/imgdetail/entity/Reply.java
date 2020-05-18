package com.pixiv.imgdetail.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {

    private Integer id;

    private String content;

    private Integer parentId;

    private String adminId;

    private Date createTime;

    private Integer isHide;

    private UserInfo user;
}
