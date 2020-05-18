package com.pixiv.sso.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.sso.entity.PixivUser;

import org.apache.ibatis.annotations.Select;

public interface PixivUserMapper extends BaseMapper<PixivUser> {


    @Select("SELECT * from pixiv_user where email=#{email}")
    public PixivUser selectByEmail(String email);

    @Select("SELECT * from pixiv_user where username=#{username}")
    public PixivUser selectByUsername(String username);
}