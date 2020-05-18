package com.pixiv.sso.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pixiv.sso.entity.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper extends BaseMapper<UserInfo> {


    @Select("Select * from user_info where username=#{username}")
    public UserInfo selectByUsername(String username);
}