package com.pixiv.sso.service;

import com.pixiv.sso.bean.SsoVO;

public interface LoginService {

    public SsoVO Login(String username, String password);
}
