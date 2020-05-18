package com.pixiv.manager.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pixiv.manager.bean.ImageVO;
import com.pixiv.manager.entity.Image;
import com.pixiv.manager.service.ImageService;
import com.pixiv.manager.service.P2PService;
import com.pixiv.manager.utils.AjaxResponse;
import com.pixiv.manager.utils.FastDFSClientWrapper;
import com.pixiv.manager.utils.IDUtils;
import com.pixiv.manager.utils.JsonUtils;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/manage")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    private FastDFSClientWrapper dfsClient;

    @Autowired
    P2PService p2PService;

    @PostMapping("/test")
    public void tt(@RequestBody Image image) {
        imageService.insertImage(image);
    }

    @GetMapping("/images")
    public AjaxResponse lists(int pageNum,int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        PageInfo<Image> pageInfo = new PageInfo<>(imageService.getImageAll());

        ImageVO imageVO=new ImageVO(pageInfo.getList(), pageInfo.getTotal(),pageInfo.getPageSize());

        return AjaxResponse.success(imageVO);
    }

    /*
    上传图片
     */
    @PostMapping("/image")
    @Transactional
    public AjaxResponse InsertImg(HttpServletRequest request)  {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);

        String data=params.getParameter("body");
        Image image=JsonUtils.jsonToPojo(data,Image.class);

        MultipartFile file=params.getFile("file");
        String fileUrl=null;
    try {
         fileUrl = dfsClient.uploadFile(file);
        image.setUrl(fileUrl);
        //设置创建时间
        image.setCreated(new Date());
        imageService.insertImage(image);
    }
    catch (Exception e){
        //如果数据库出现问题了 就先把上传好的图片删去
        if(fileUrl!=null)
        dfsClient.deleteFileByPath(fileUrl);
    }
        return AjaxResponse.success("上传成功");
    }

    @DeleteMapping("/image/{id}")
    public AjaxResponse DeleteImage(@PathVariable String id) {
        imageService.delete(id);
        return AjaxResponse.success("删除成功");
    }

    @PutMapping("/image") //之前报错“跨域问题“实际上是参数接收错误
    public AjaxResponse PatchImage(@RequestBody Image image){
        //String data=request.getParameter("body");
        imageService.updateImage(image);
        return AjaxResponse.success("修改成功");
    }

}
