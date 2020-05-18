package com.pixiv.solr.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.solr.dao.mapper.ImageMapper;
import com.pixiv.solr.entity.Image;
import com.pixiv.solr.service.ImageService;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper,Image> implements ImageService {

}
