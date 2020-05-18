package com.pixiv.manager.bean;

import com.pixiv.manager.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ImageVO {

    private  List<Image> imageList;

    private long total;

    private Integer pageSize;


}
