package com.pixiv.index.controller;


import com.pixiv.index.entity.Image;
import com.pixiv.index.service.ImageService;
import com.pixiv.index.utils.AjaxResponse;
import com.pixiv.index.utils.FastDFSClientWrapper;
import com.pixiv.index.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    ImageService imageService;

    @Autowired
    private FastDFSClientWrapper dfsClient;

    @PostMapping("/image")
    @Transactional
    public AjaxResponse InsertImage(HttpServletRequest request) throws IOException {
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
            e.printStackTrace();
            return AjaxResponse.error("上传失败");
        }
        return AjaxResponse.success("上传成功");
    }



}
