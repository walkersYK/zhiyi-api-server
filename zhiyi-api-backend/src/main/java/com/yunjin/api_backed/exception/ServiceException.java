package com.yunjin.api_backed.exception;

import lombok.Getter;

/**
 * 功能：
 *
 * @anther 韵锦
 * @from
 */
@Getter
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(String code, String msg) {
        super(msg);
        this.code=code;
    }
}
