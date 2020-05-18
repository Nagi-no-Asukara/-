package com.pixiv.userspace.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pixiv.userspace.bean.FastDFSClientWrapper;
import com.pixiv.userspace.entity.Image;
import com.pixiv.userspace.entity.UserInfo;
import com.pixiv.userspace.service.UserService;
import com.pixiv.userspace.utils.AjaxResponse;
import com.pixiv.userspace.utils.JsonUtils;
import com.pixiv.userspace.utils.RedisUtil;
import com.pixiv.userspace.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@CrossOrigin
@RequestMapping("/userspace")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FastDFSClientWrapper fastDFSClient;



    @Autowired
    RedisUtil redisUtil;

    private static int timeout=7*24*3600;

    private static String SessionPrefix="SESSION:";


    @GetMapping("/{id}")
    public AjaxResponse GetUserInfo(@PathVariable("id")Integer id) {
         return AjaxResponse.success(userService.getUserInfo(id));
    }

    @GetMapping("/upload")
    public AjaxResponse GetUploadList(Integer pageNum,Integer pageSize,Integer userId) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Image> pageInfo = new PageInfo<>(userService.getUploadList(userId));
        return AjaxResponse.success(pageInfo);
    }

    @GetMapping("/favList")
    public AjaxResponse GetFavList(Integer pageNum,Integer pageSize,Integer userId) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Image> pageInfo = new PageInfo<>(userService.getFavList(userId));
        return AjaxResponse.success(pageInfo);
    }


    /**
     * 更换头像   //在header中加上token  利用token去到用户信息  因为把图片连同信息一起上传太麻烦了
     */
     @PostMapping("/faceSetting")
     @Transactional
     public AjaxResponse UpdateAvatar(HttpServletRequest request) {
         MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);

         String token=JsonUtils.jsonToPojo(params.getParameter("token"),String.class);


         System.out.println("SESSION:"+token);
        // redisTemplate.opsForValue().set("nmsl","你妈找的到伐");
        // System.out.println("结果是"+redisTemplate.opsForValue().get("nmsl"));
         String nmsl= (String) redisUtil.get("SESSION:"+token);

         UserInfo userInfo= JsonUtils.jsonToPojo((String) redisUtil.get("SESSION:"+token),UserInfo.class);
         MultipartFile file=params.getFile("file");
         String fileUrl=null;
         String oldPath=userInfo.getAvatar();

         try {
             fileUrl = fastDFSClient.uploadFile(file);
             userInfo.setAvatar(fileUrl);
             userService.updateAvatar(userInfo);
             if (oldPath!=null||oldPath=="")
                 fastDFSClient.deleteFileByPath(oldPath);
             //更新redis缓存
             redisUtil.set(SessionPrefix+token,JsonUtils.objectToJson(userInfo),timeout);
         }
         catch (Exception e){
             //如果数据库出现问题了 就先把上传好的图片删去
             if(fileUrl!=null)
                 fastDFSClient.deleteFileByPath(fileUrl);
             return AjaxResponse.error("上传失败");
         }

         return AjaxResponse.success("上传成功");
     }

    /**
     * 更改用户信息
     */
     @PostMapping("/SettingName")//为了方便 前端提交时会带上id
     public AjaxResponse UpdateName(@RequestBody UserInfo userInfo, HttpServletRequest request){
         String token= request.getHeader("token");
         redisUtil.get(token);
         UserInfo user= JsonUtils.jsonToPojo((String) redisUtil.get(SessionPrefix+token),UserInfo.class);

         if(user != null) {
             user.setName(userInfo.getName());
             //更新redis内的缓存
             redisUtil.set(SessionPrefix + token, JsonUtils.objectToJson(user), timeout);
         }

         if(userService.updateInfo(userInfo))
            return AjaxResponse.success("修改成功");
         else
             return AjaxResponse.error("名称重复");
     }


}
