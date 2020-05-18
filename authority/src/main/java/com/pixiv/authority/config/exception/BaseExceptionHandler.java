package com.pixiv.authority.config.exception;

import com.pixiv.authority.utils.AjaxResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class BaseExceptionHandler {

    //自定义原有的异常
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public AjaxResponse error(HttpServletRequest request, HttpServletResponse response, AuthorizationException exception){
        return AjaxResponse.error(CommonExceptionType.Authority_ERROR);
    }
}
