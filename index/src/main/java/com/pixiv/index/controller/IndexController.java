package com.pixiv.index.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pixiv.index.dao.mapper.ContentMapper;
import com.pixiv.index.entity.Image;
import com.pixiv.index.service.ContentService;
import com.pixiv.index.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页内容管理
 */
@RestController
@CrossOrigin
@RequestMapping("/index")
public class IndexController {

    @Autowired
    ContentService contentService;

    @Autowired
    ContentMapper contentMapper;

    @GetMapping("/rotations")
    public AjaxResponse getRotation(){
        return AjaxResponse.success(contentService.getRotation());
    }

    @GetMapping("/image")//先显示前面10张 如果用户有需求点开"查看所有" 在全部返回给它
    public AjaxResponse getRecommendImageListOnce(){
        PageHelper.startPage(1,10);
        PageInfo<Image> pageInfo=new PageInfo<>(contentMapper.getRecommendImageList());
        return AjaxResponse.success(pageInfo);
    }

    @GetMapping("/images")//先显示前面x张 如果用户有需求点开"查看所有" 在全部返回给它 未完成
    public AjaxResponse getRecommendImageList(){
        return AjaxResponse.success(contentMapper.getRecommendImageList());
    }

    @GetMapping("/author")
    public AjaxResponse getRecommendAuthor(){
        return AjaxResponse.success(contentMapper.getRecommendAuthor());
    }




}
