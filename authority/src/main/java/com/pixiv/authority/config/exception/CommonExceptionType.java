package com.pixiv.authority.config.exception;

public enum CommonExceptionType {

    USER_INPUT_ERROR(400,"用户输入异常"),
    Authority_ERROR(403,"系统权限异常"),
    SYSTEM_ERROR (500,"系统服务异常"),
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
