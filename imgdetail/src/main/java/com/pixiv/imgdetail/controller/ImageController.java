package com.pixiv.imgdetail.controller;


import com.pixiv.imgdetail.entity.Image;

import com.pixiv.imgdetail.service.ImageService;
import com.pixiv.imgdetail.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {

    @Autowired
    ImageService imageService;



    @GetMapping("/{id}")
    public AjaxResponse GetImage(@PathVariable("id")Integer id){

        Image image= imageService.GetImgDetail(id);

        return AjaxResponse.success(image);
    }

    @PostMapping("/loved")
    public AjaxResponse isLoved(@RequestBody Map<String,Integer> map){
        Integer userId=map.get("userId");
        Integer imageId=map.get("imageId");

        return  AjaxResponse.success(imageService.isLoved(userId,imageId));

    }

    @PutMapping("/loved")
    @Transactional
    public AjaxResponse AddLoved(@RequestBody Map<String,Integer> map) {

        Integer userId=map.get("userId");
        Integer imageId=map.get("imageId");

        imageService.insertLove(userId,imageId);
        Image image=imageService.getById(imageId);
        image.setLoved(image.getLoved()+1);
        imageService.updateById(image);
        return  AjaxResponse.success();
    }


    // deleteMapping接收参数好像有问题 用@requestBody是最稳的
    @DeleteMapping("/loved")
    @Transactional
    public AjaxResponse DeleteLoved(@RequestBody Map<String,Integer> map) {

        Integer userId=map.get("userId");
        Integer imageId=map.get("imageId");

        imageService.deleteLove(userId,imageId);
        Image image=imageService.getById(imageId);
        image.setLoved(image.getLoved()-1);
        imageService.updateById(image);
        return  AjaxResponse.success();
    }

}
