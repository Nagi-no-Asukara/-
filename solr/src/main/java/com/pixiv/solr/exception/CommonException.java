package com.pixiv.solr.exception;



public class CommonException extends RuntimeException {

    //异常错误编码
    private int code ;
    //异常信息
    private String message;

    private CommonException(){}

    public CommonException(CommonExceptionType exceptionTypeEnum) {
        this.code = exceptionTypeEnum.getCode();
        this.message = exceptionTypeEnum.getMessage();
    }

    public CommonException(CommonExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
