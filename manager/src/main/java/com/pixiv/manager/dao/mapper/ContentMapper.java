package com.pixiv.manager.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.manager.entity.Content;
import com.pixiv.manager.entity.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

public interface ContentMapper extends BaseMapper<Content> {


    @Select("SELECT * from content where category_id=1")
    public List<Content> getRotation();

    @Select("SELECT author_name from recommend_author ")
    public List<String> getRecommendAuthor();

    @Select("SELECT image_id from recommend_image ")
    public List<Integer> getRecommendImageId();

    public List<Image> getRecommendImageList();

    @Insert("Insert into recommend_author(author_name) values(#{name})")
    public void addRecommendAuthor(String name);

    @Insert("Insert into recommend_image(image_id) values(#{id})")
    public void addRecommendImage(Integer id);

    @DeleteMapping("Delete from recommend_author where author_name=#{name}")
    public void deleteRecommendAuthor(String name);

    @DeleteMapping("Delete from recommend_image where image_id=#{id}")
    public void deleteRecommendImage(Integer id);




}
