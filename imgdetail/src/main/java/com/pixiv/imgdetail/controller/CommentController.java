package com.pixiv.imgdetail.controller;


import com.pixiv.imgdetail.entity.Comment;
import com.pixiv.imgdetail.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    CommentService commentService;


    @GetMapping("/{id}")
    public List<Comment> get()
    {

        return commentService.SelectByImageId("29");

    }


}
