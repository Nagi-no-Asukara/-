package com.pixiv.sso.service;

import com.pixiv.sso.entity.PixivUser;
import com.pixiv.sso.utils.AjaxResponse;
import com.pixiv.sso.utils.Result;

import javax.mail.MessagingException;

public interface RegisterService {

    public AjaxResponse Check(PixivUser user) throws MessagingException;
    public AjaxResponse Register(String token);
}
