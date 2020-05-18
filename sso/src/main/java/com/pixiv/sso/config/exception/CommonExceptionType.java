package com.pixiv.sso.config.exception;

public enum CommonExceptionType {

    TOKEN_ERROR(401,"TOKEN已过期"),
    USER_INPUT_ERROR(400,"用户输入异常"),
    USERNAME_ERROR(401,"用户名已被注册"),
    EMAIL_ERROR(402,"邮箱已被注册"),
    SYSTEM_ERROR (500,"系统服务繁忙"),
    OTHER_ERROR(999,"其他未知异常");

    CommonExceptionType(int code, String typeDesc) {
        this.code = code;
        this.message = typeDesc;

    }

    private String message;//异常类型中文描述

    private int code; //code

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
