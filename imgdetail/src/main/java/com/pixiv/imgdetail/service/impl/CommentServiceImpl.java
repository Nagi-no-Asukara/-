package com.pixiv.imgdetail.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pixiv.imgdetail.entity.Comment;
import com.pixiv.imgdetail.dao.mapper.CommentMapper;
import com.pixiv.imgdetail.entity.Reply;
import com.pixiv.imgdetail.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService  {


    @Autowired
    CommentMapper commentMapper;


    public List<Comment> SelectByImageId(String ImageId)
    {
        List<Comment> commentList= commentMapper.getCommentByImageId(ImageId);

        List<Reply> replyList;

        for(Comment comment:commentList){
            replyList=commentMapper.getReplyByCommentId(comment.getId());
            comment.setReplyList(replyList);
        }
        return commentList;
    }


}
