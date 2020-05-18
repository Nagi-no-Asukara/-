package com.pixiv.index.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.index.dao.mapper.ContentMapper;
import com.pixiv.index.entity.Content;
import com.pixiv.index.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    ContentMapper contentMapper;


    @Override
    public List<Content> getRotation() {
        return  contentMapper.getRotation();
    }
}
