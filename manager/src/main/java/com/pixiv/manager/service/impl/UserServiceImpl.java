package com.pixiv.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.manager.dao.mapper.UserMapper;
import com.pixiv.manager.entity.UserInfo;
import com.pixiv.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService   {

    @Autowired
    UserMapper userMapper;





}
