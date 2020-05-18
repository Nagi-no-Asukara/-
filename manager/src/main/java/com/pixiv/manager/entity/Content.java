package com.pixiv.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Content {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String redirect;

    private String src;

    private String title;

}