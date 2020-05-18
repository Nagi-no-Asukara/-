package com.pixiv.index.service.impl;

import com.pixiv.index.entity.Image;
import com.pixiv.index.dao.mapper.ImageMapper;
import com.pixiv.index.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;

    public void insertImage(Image image){
          imageMapper.insert(image);
    }

}
