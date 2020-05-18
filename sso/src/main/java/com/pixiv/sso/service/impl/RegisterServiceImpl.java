package com.pixiv.sso.service.impl;

import com.pixiv.sso.config.exception.CommonException;
import com.pixiv.sso.config.exception.CommonExceptionType;
import com.pixiv.sso.entity.PixivUser;
import com.pixiv.sso.dao.mapper.PixivUserMapper;
import com.pixiv.sso.dao.mapper.UserInfoMapper;
import com.pixiv.sso.entity.UserInfo;
import com.pixiv.sso.service.MailService;
import com.pixiv.sso.service.RegisterService;
import com.pixiv.sso.utils.AjaxResponse;
import com.pixiv.sso.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    PixivUserMapper userMapper;

    @Autowired
    MailService mailService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserInfoMapper userInfoMapper;



    public AjaxResponse Check(PixivUser user) throws MessagingException {

        if (userMapper.selectByUsername(user.getUsername())!=null)
            throw  new CommonException(CommonExceptionType.USERNAME_ERROR);
        if (userMapper.selectByEmail(user.getEmail())!=null)
            throw  new CommonException(CommonExceptionType.EMAIL_ERROR);
        //验证通过
        String token = mailService.sendSimpleMail(user.getEmail(), "kirikiri邮箱验证");

        redisUtil.set("Code" + token,user.getUsername(), 3600 * 2);//设置过期时间两小时
        redisUtil.set("Code1" + token,user.getPassword(), 3605 * 2);
        redisUtil.set("Code2" + token, user.getEmail(),3605 * 2);
        //msg默认是OK
        return AjaxResponse.success();

    }

    public AjaxResponse Register(String token) {
        boolean exist = redisUtil.hasKey("Code" + token);

        if (!exist)
            return AjaxResponse.error(400, "验证码过期");
        else {
            PixivUser user = new PixivUser();
            String username=(String) redisUtil.get("Code" + token);
            user.setUsername(username);
            user.setEmail((String) redisUtil.get("Code2" + token));
            user.setPassword((String) redisUtil.get("Code1" + token));
            //对password进行md5加密
           // String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            //user.setPassword(md5);
            user.setCreated(new Date());

            redisUtil.del("Code" + token);
            redisUtil.del("Code1" + token);
            redisUtil.del("Code2" + token);
            userMapper.insert(user);
            UserInfo userInfo=new UserInfo();
            userInfo.setUsername(username);
            userInfo.setName(username);

            userInfoMapper.insert(userInfo);

            return AjaxResponse.success();

        }
    }


}
