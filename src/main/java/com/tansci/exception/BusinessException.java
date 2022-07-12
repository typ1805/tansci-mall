package com.tansci.exception;

import lombok.Getter;

/**
 * @path：com.tansci.exception.BusinessException.java
 * @className：BusinessException.java
 * @description：业务异常处理
 * @author：tanyp
 * @dateTime：2022/03/29 10:27
 * @editNote：
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    private int code = 500;

    /**
     * 异常描述
     */
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

}
