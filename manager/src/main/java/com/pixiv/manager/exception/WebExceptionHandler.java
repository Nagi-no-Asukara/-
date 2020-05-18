package com.pixiv.manager.exception;

import com.pixiv.manager.exception.bean.CustomExceptionType;
import com.pixiv.manager.utils.AjaxResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//捕获controller层异常
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e) {
        if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            System.out.println("系统服务异常");
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR, "系统服务异常");
        }

        else if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode())
            return AjaxResponse.error(CustomExceptionType.USER_INPUT_ERROR, "用户输入异常");
            //400异常不需要持久化，将异常信息以友好的方式告知用户就可以
        else
            return AjaxResponse.error(CustomExceptionType.OTHER_ERROR, "未知异常");
        //TODO 将500异常信息持久化处理，方便运维人员处理
     }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResponse exception(Exception e) {
        //TODO 将异常信息持久化处理，方便运维人员处理

        //没有被程序员发现，并转换为CustomException的异常，都是其他异常或者未知异常.
        return AjaxResponse.error(new CustomException(CustomExceptionType.OTHER_ERROR,"未知异常"));
    }

}
