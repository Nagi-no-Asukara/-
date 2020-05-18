package com.pixiv.userspace.bean;

import com.pixiv.userspace.entity.Image;
import lombok.Data;

@Data
public class ImageVO {

    private Image image;

    private long total;

    private int pageNum;


}
