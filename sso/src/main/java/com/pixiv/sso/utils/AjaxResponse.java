package com.pixiv.sso.utils;


import lombok.Data;
import com.pixiv.sso.config.exception.*;

@Data
public class AjaxResponse {

    private  boolean isok;
    private  int code;
    private  String message;
    private  Object data;

    private AjaxResponse() {

    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse error(int code,String message) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(code);
        resultBean.setMessage(message);
        //TODO 这里最好将异常信息持久化
        return resultBean;
    }


    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CommonExceptionType customExceptionType,
                                     String errorMessage) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(customExceptionType.getCode());
        resultBean.setMessage(errorMessage);
        return resultBean;
    }

    public static AjaxResponse error(CommonException customException) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(customException.getCode());
        resultBean.setMessage(customException.getMessage());
        return resultBean;
    }

    //请求处理成功时的数据响应
    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    //请求处理成功，并响应结果数据
    public  static AjaxResponse success(Object data) {
        AjaxResponse resultBean = AjaxResponse.success();
        resultBean.setData(data);
        return resultBean;
    }

}
