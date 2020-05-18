package com.pixiv.imgdetail.entity;

import lombok.Data;
import org.apache.catalina.User;

import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Integer id;

    private Integer adminId;

    private Integer imageId;

    private Integer parentId;

    private Integer replyCommentId;

    private String content;

    private Date createTime;

    private Integer isHide;

    private List<Reply> replyList;

    private UserInfo user;

}