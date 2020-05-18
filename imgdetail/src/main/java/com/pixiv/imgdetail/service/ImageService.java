package com.pixiv.imgdetail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pixiv.imgdetail.dao.mapper.ImageMapper;
import com.pixiv.imgdetail.entity.Image;

import java.util.List;

public interface ImageService extends IService<Image> {


    public Image GetImgDetail(Integer id);

    public void insertLove(Integer userId,Integer imageId);

    public void deleteLove(Integer userId,Integer imageId);

    public boolean isLoved(Integer userId, Integer imageId);

    public List<Image> getLovedImageListById(Integer userId);
}
