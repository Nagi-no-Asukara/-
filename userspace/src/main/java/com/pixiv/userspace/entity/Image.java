package com.pixiv.userspace.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Image {
    private Integer id;

    private String title;

    private String url;

    private String author;

    private Integer authorId;

    private Date created;

    private Integer loved;


}