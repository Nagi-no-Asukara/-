package com.pixiv.userspace.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.userspace.bean.*;
import com.pixiv.userspace.dao.mapper.UserMapper;
import com.pixiv.userspace.entity.Image;
import com.pixiv.userspace.entity.UserInfo;
import com.pixiv.userspace.service.UserService;
import com.pixiv.userspace.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    FastDFSClientWrapper fastDFSClient;


    public UserInfo getUserInfo(Integer id) {
        return userMapper.selectById(id);
    }


    public List<Image> getFavList(Integer id) {
        return userMapper.getFavList(id);
    }

    public List<Image> getUploadList(Integer id){
        return userMapper.getUploadList(id);
    }


    @Override
    public void updateAvatar(UserInfo userInfo) {
        userMapper.updateById(userInfo);
    }

    public boolean updateInfo(UserInfo userInfo) {
        //判断名称是否重复
        List<UserInfo> userList=userMapper.selectList(null);
        if(userList.size()>0){
            for(UserInfo u:userList){
                if(u.getName().equals(userInfo.getName()))
                    return false;
            }
        }

        userMapper.updateById(userInfo);
        return true;
    }

}
