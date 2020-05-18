package com.pixiv.sso.controller;

import com.pixiv.sso.config.exception.CommonException;
import com.pixiv.sso.entity.PixivUser;
import com.pixiv.sso.bean.SsoVO;
import com.pixiv.sso.service.LoginService;
import com.pixiv.sso.utils.AjaxResponse;
import com.pixiv.sso.utils.CookieUtils;
import com.pixiv.sso.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sso")
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    //cookie_age
    private static int Maxage=86400*7;

    @PostMapping("/login")
    public AjaxResponse login(@RequestBody PixivUser pixivUser, HttpServletRequest request, HttpServletResponse response){

        SsoVO ssoVO;
        try {
           ssoVO = loginService.Login(pixivUser.getUsername(), pixivUser.getPassword());
        }
        catch (CommonException e){
          return AjaxResponse.error(e);
        }
        //CookieUtils.setCookie(request, response,"token",ssoVO.getToken(),Maxage);
        return AjaxResponse.success(ssoVO);
    }

}
