package com.pixiv.sso.service;

import com.pixiv.sso.entity.UserInfo;
import com.pixiv.sso.utils.Result;

public interface TokenService {

    public UserInfo UpdateToken(String token);
}
