package com.pixiv.manager.controller;

import com.pixiv.manager.dao.mapper.ContentMapper;
import com.pixiv.manager.entity.Content;
import com.pixiv.manager.entity.Image;
import com.pixiv.manager.service.ContentService;
import com.pixiv.manager.utils.AjaxResponse;
import com.pixiv.manager.utils.FastDFSClientWrapper;
import com.pixiv.manager.utils.IDUtils;
import com.pixiv.manager.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页内容管理
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/manage/index")
public class IndexController {

    @Autowired
    private FastDFSClientWrapper dfsClient;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentMapper contentMapper;

    /**
     * 获得首页图片
     * @return
     */
    @GetMapping("/rotations")
    public AjaxResponse getRotations(){
        return AjaxResponse.success(contentService.list());
    }

    @PostMapping("/rotations")
    public AjaxResponse saveOrUpdateRotations(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String data=params.getParameter("body");
        Content content=JsonUtils.jsonToPojo(data,Content.class);
        MultipartFile file=params.getFile("file");
        String src = dfsClient.uploadFile(file);
        content.setSrc(src);
        return AjaxResponse.success(contentService.saveOrUpdate(content));
    }

    @PostMapping("/rotation")//不涉及图片的更新
    public AjaxResponse saveOrUpdateRotations(@RequestBody Content content) throws IOException {

        return AjaxResponse.success(contentService.saveOrUpdate(content));
    }

    @DeleteMapping("/rotation")
    public AjaxResponse deleteRotations(@RequestBody Integer id){
        contentService.removeById(id);
        return AjaxResponse.success();
    }


    @GetMapping("/authors")
    public AjaxResponse getRecommendAuthors(){
        return AjaxResponse.success(contentMapper.getRecommendAuthor());
    }

    @PostMapping("/author")
    public AjaxResponse saveOrUpdateRecommendAuthors(@RequestBody Map<String,String> map){
        String name=map.get("name");
        contentMapper.addRecommendAuthor(name);
        return AjaxResponse.success();
    }

    @DeleteMapping("/author")
    public AjaxResponse deleteRecommendAuthors(@RequestBody Map<String,String> map){
        String name=map.get("name");
        contentMapper.addRecommendAuthor(name);
        return AjaxResponse.success();
    }



    @GetMapping("/images")
    public AjaxResponse getRecommendImages(){
        return AjaxResponse.success(contentMapper.getRecommendImageList());
    }

    @PostMapping("/image")
    public AjaxResponse getRecommendImages(@RequestBody  Map<String,String> map){
        Integer id=Integer.parseInt(map.get("id"));
        List<Integer> list=contentMapper.getRecommendImageId();
        for(int i:list){
            if(id==i)
                return AjaxResponse.error("已经添加进推荐列表了");
        }
        contentMapper.addRecommendImage(id);
        return AjaxResponse.success();
    }

    @DeleteMapping("/image")                                          //第二个一定要是string 服了
    public AjaxResponse deleteRecommendImages(@RequestBody Map<String,String> map){
        Integer id=Integer.parseInt(map.get("id"));
        contentMapper.deleteRecommendImage(id);
        return AjaxResponse.success();
    }



    /*// 上传文件
    @PostMapping(value = "/upload")
    public String upload(MultipartFile[] files,@PathVariable("id")Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Content> contentList=new ArrayList<>();
        for(MultipartFile file:files) {
            String fileUrl = dfsClient.uploadFile(file);
            String name=file.getName();
            Content content=new Content(fileUrl,name);
            content.setId(id);
            contentList.add(content);
        }
        return JsonUtils.objectToJson(contentService.InsertContent(contentList));
    }*/

}
