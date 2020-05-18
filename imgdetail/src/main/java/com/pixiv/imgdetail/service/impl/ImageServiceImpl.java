package com.pixiv.imgdetail.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.imgdetail.entity.Image;
import com.pixiv.imgdetail.dao.mapper.ImageMapper;

import com.pixiv.imgdetail.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper,Image> implements ImageService {


    @Autowired
    ImageMapper imageMapper;

    
    @Override
    public Image GetImgDetail(Integer  id) {

        return imageMapper.selectById(id);
    }

    public boolean isLoved(Integer userId, Integer imageId){
             List<Image> imageList=imageMapper.getLovedImageListById(userId);
             for(Image image:imageList){
                 if(image.getId().equals(imageId))
                     return true;
             }
             return  false;

    }

    public List<Image> getLovedImageListById(Integer userId){
        return imageMapper.getLovedImageListById(userId);
    }



    @Override
    public void insertLove(Integer userId, Integer imageId) {

        imageMapper.AddLoved(userId,imageId);
    }

    @Override
    public void deleteLove(Integer userId, Integer imageId) {
        imageMapper.DeleteLoved(userId,imageId);
    }
}
