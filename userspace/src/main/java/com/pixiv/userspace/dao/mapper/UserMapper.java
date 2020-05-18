package com.pixiv.userspace.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.userspace.entity.Image;

import java.util.List;

import com.pixiv.userspace.entity.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<UserInfo> {


    @Select("Select * from image where author_id=#{id}")
    public List<Image> getUploadList(Integer id);

    public List<Image> getFavList(Integer id);

}