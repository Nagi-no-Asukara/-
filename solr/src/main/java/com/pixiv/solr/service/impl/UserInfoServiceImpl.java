package com.pixiv.solr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.solr.dao.mapper.UserInfoMapper;
import com.pixiv.solr.entity.UserInfo;
import com.pixiv.solr.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
