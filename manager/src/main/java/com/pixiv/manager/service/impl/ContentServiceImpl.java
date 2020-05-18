package com.pixiv.manager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.manager.entity.Content;

import com.pixiv.manager.dao.mapper.ContentMapper;
import com.pixiv.manager.service.ContentService;
import com.pixiv.manager.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl  extends ServiceImpl<ContentMapper, Content>  implements ContentService {

    @Autowired
    ContentMapper contentMapper;



}
