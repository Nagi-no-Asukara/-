package com.pixiv.imgdetail.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.imgdetail.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageMapper extends BaseMapper<Image> {


    public void AddLoved(@Param("userId")Integer userId, @Param("imageId")Integer imageId);

    public void DeleteLoved(@Param("userId")Integer userId, @Param("imageId")Integer imageId);

    public List<Image> getLovedImageListById(@Param("userId")Integer userId);
}