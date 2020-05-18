package com.pixiv.index.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pixiv.index.entity.Content;

import java.util.List;

public interface ContentService extends IService<Content> {

    public List<Content> getRotation();
}
