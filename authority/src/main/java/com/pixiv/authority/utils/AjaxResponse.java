package com.pixiv.authority.utils;

import com.pixiv.authority.config.exception.CommonException;
import com.pixiv.authority.config.exception.CommonExceptionType;
import lombok.Data;

@Data
public class AjaxResponse {

    private  boolean isok;
    private  int code;
    private  String message;
    private  Object data;

    private AjaxResponse() {

    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CommonException e) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if(e.getCode()== CommonExceptionType.Authority_ERROR.getCode()){
            resultBean.setMessage(CommonExceptionType.Authority_ERROR.getMessage());
        }
        else if(e.getCode() == CommonExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if(e.getCode() == CommonExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage() + ",请将该异常信息发送给管理员!");
        }else{
            resultBean.setMessage("系统出现未知异常，请联系管理员!");
        }
        //TODO 这里最好将异常信息持久化
        return resultBean;
    }


    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CommonExceptionType commonExceptionType,
                                     String errorMessage) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(commonExceptionType.getCode());
        resultBean.setMessage(errorMessage);
        return resultBean;
    }


    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CommonExceptionType commonExceptionType) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(commonExceptionType.getCode());
        resultBean.setMessage(commonExceptionType.getMessage());
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
