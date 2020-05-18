package com.pixiv.solr.exception;

public enum  CommonExceptionType {


    Search_ERROR (501,"搜素服务异常"),
    Mysql_ERROR (503,"数据库处理异常"),
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
