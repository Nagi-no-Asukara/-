package com.pixiv.sso.service.impl;

import com.pixiv.sso.bean.*;
import com.pixiv.sso.config.exception.CommonException;
import com.pixiv.sso.config.exception.CommonExceptionType;
import com.pixiv.sso.dao.mapper.PixivUserMapper;
import com.pixiv.sso.dao.mapper.UserInfoMapper;
import com.pixiv.sso.entity.PixivUser;
import com.pixiv.sso.entity.UserInfo;
import com.pixiv.sso.service.LoginService;
import com.pixiv.sso.utils.JsonUtils;
import com.pixiv.sso.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    PixivUserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserInfoMapper userInfoMapper;

    //cookie过期时间
    private static Integer SESSION_EXPIRE = 3600*24*7;

    public SsoVO Login(String username, String password) {

        PixivUser user;
        //判断是用户名还是邮箱
        if (!username.contains("@"))
            user = userMapper.selectByUsername(username);
        else
            user = userMapper.selectByEmail(username);

        if (user == null) {
            //返回登录失败
            throw new CommonException(CommonExceptionType.USER_INPUT_ERROR, "用户名或密码错误");
        }

        //判断密码是否正确 方便起见不采用md5加密了
        /*if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            // 2、如果不正确，返回登录失败
            throw new CommonException(CommonExceptionType.USER_INPUT_ERROR, "用户名或密码错误");
       }*/
        if(!user.getPassword().equals(password))
            throw new CommonException(CommonExceptionType.USER_INPUT_ERROR, "用户名或密码错误");

        // 如果正确生成token。
        String token = UUID.randomUUID().toString();
        // 把用户信息写入redis，key：token value：用户信息
        //在userInfo中查找用户信息 存到redis中 同时返回给前端生成cookie

        UserInfo userInfo = userInfoMapper.selectByUsername(user.getUsername());

        redisUtil.set("SESSION:" + token, JsonUtils.objectToJson(userInfo));

        // 设置Session的过期时间
        redisUtil.expire("SESSION:" + token, SESSION_EXPIRE);


        // 把token返回 在controller端写入cookie
        SsoVO SSOVO = new SsoVO();
        SSOVO.setToken(token);
        SSOVO.setUserInfo(userInfo);
        return SSOVO;
    }

}