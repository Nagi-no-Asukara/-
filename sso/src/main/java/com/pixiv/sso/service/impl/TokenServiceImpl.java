package com.pixiv.sso.service.impl;

import com.pixiv.sso.config.exception.CommonException;
import com.pixiv.sso.config.exception.CommonExceptionType;
import com.pixiv.sso.entity.UserInfo;
import com.pixiv.sso.service.TokenService;
import com.pixiv.sso.utils.AjaxResponse;
import com.pixiv.sso.utils.JsonUtils;
import com.pixiv.sso.utils.RedisUtil;
import com.pixiv.sso.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisUtil redisUtil;

    private static Integer SESSION_EXPIRE=3600*24*7;

    @Override
    public UserInfo UpdateToken(String token) {

        String json=(String)redisUtil.get("SESSION:"+token);
        System.out.println("SESSION:"+token);
        if(StringUtils.isBlank(json))
        {
            throw new CommonException(CommonExceptionType.TOKEN_ERROR);
        }
        //取到了 更新token的过期时间
        UserInfo user = JsonUtils.jsonToPojo(json, UserInfo.class);
        redisUtil.expire("SESSION:"+token, SESSION_EXPIRE);
        return user;
    }
}
