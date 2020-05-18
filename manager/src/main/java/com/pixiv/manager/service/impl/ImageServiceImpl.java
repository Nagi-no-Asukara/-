package com.pixiv.manager.service.impl;

import com.pixiv.manager.entity.Image;
import com.pixiv.manager.dao.mapper.ImageMapper;
import com.pixiv.manager.service.ImageService;
import com.pixiv.manager.utils.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    private FastDFSClientWrapper dfsClient;


    @Override
    public List<Image> getImageAll() {
        return imageMapper.selectList(null);
    }

    public List<Image> GetImageByAuthor(String name){
        return  imageMapper.selectByAuthor(name);
    }

    @Override
    public Image insertImage(Image image) {
        imageMapper.insert(image);

        return image;
    }

    @Override
    @Transactional
    public void delete(String id) {
        Image image=imageMapper.selectById(id);
        imageMapper.deleteById(id);
        dfsClient.deleteFileByPath(image.getUrl());
    }

    @Override
    public void updateImage(Image image) {
        imageMapper.updateById(image);
    }

    public Image getImageById(String id) {
        Image image=imageMapper.selectById(id);
        return  image;
    }

/*
    @Caching(evict ={@CacheEvict(cacheNames = "ImageList",allEntries = true)},//true则删除所有数据
            put={@CachePut(cacheNames = "Image",key="#id")})//put 会把return 的对象重新放入key对应的value上 所以不要忘记return 哦
    public  Image update(Image image)
    {
        imageMapper.updateById(image);
        return  image;
    }


    @Caching(evict = {@CacheEvict(cacheNames = "ImageList",allEntries = true),@CacheEvict(cacheNames = "Image",key="#id")})
    public  void delete(Integer id)
    {
        imageMapper.deleteById(id);
    }
*/



}
