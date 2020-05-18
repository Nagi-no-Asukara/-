package com.pixiv.imgdetail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pixiv.imgdetail.entity.Comment;

import java.util.List;


public interface CommentService extends IService<Comment> {

    public List<Comment> SelectByImageId(String ImageId);




}
