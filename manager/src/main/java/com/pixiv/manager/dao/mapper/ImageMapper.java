package com.pixiv.manager.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.manager.entity.Image;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface ImageMapper extends BaseMapper<Image> {


    @Select("Select * from image where author=#{name}")
    public List<Image> selectByAuthor(String name);


}