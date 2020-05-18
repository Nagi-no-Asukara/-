package com.pixiv.imgdetail.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pixiv.imgdetail.entity.Comment;
import com.pixiv.imgdetail.entity.Reply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {



    List<Comment> getCommentByImageId(String imageId);

    List<Reply> getReplyByCommentId(Integer parentId);

}
