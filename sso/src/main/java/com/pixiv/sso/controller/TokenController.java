package com.pixiv.sso.controller;


import com.pixiv.sso.bean.SsoVO;
import com.pixiv.sso.config.exception.CommonException;
import com.pixiv.sso.entity.UserInfo;
import com.pixiv.sso.service.TokenService;
import com.pixiv.sso.utils.AjaxResponse;
import com.pixiv.sso.utils.JsonUtils;
import com.pixiv.sso.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/sso")
public class TokenController {

    @Autowired
    private TokenService tokenService;


    @GetMapping(value="/token")
    public AjaxResponse UpdateToken(String token) {

        UserInfo userInfo=null;
        try {
            userInfo=tokenService.UpdateToken(token);
        }
        catch (CommonException e){
            return AjaxResponse.error(e);
        }
        SsoVO ssoVO=new SsoVO();
        ssoVO.setToken(token);
        ssoVO.setUserInfo(userInfo);
        return AjaxResponse.success(ssoVO);
    }
}
