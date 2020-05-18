package com.pixiv.index.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.index.entity.Content;
import com.pixiv.index.entity.Image;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContentMapper extends BaseMapper<Content> {


    @Select("SELECT * from content ")
    public List<Content> getRotation();

    @Select("SELECT author_name from recommend_author ")
    public List<String> getRecommendAuthor();

    public List<Image> getRecommendImageList();


}
