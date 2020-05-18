package com.pixiv.sso.controller;

import com.pixiv.sso.config.exception.CommonException;
import com.pixiv.sso.entity.PixivUser;
import com.pixiv.sso.service.MailService;
import com.pixiv.sso.service.RegisterService;
import com.pixiv.sso.utils.AjaxResponse;
import com.pixiv.sso.utils.RedisUtil;
import com.pixiv.sso.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/sso")
@CrossOrigin
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    MailService mailService;

    @Autowired
    RedisUtil redisUtil;


    @PostMapping("/sendMail")
    public AjaxResponse Checking(@RequestBody PixivUser user) throws MessagingException {

        //先去后台检验数据
        try {
            registerService.Check(user);
        }
        catch (CommonException ce){
            return AjaxResponse.error(ce);
        }

         return AjaxResponse.success();
    }

    @GetMapping("/register")
    public AjaxResponse Checking(String token) {

        return registerService.Register(token);

    }

}

