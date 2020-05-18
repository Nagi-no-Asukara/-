package com.pixiv.chat.bean;

import lombok.Data;

@Data
public class MsgVO {


    private Integer userId;


    private String username;


    private String avatar;


    private String msg;


    private int count;

}
