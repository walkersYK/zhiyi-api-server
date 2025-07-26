package com.yunjin.api_backed.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：返回包装类
 *
 * @anther 韵锦
 * @from
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR = "500";

    private String code;
    private String msg;
    private Object data;


    public static com.yunjin.api_backed.common.Result success() {
        return new com.yunjin.api_backed.common.Result(CODE_SUCCESS, "请求成功", null);
    }

    public static com.yunjin.api_backed.common.Result success(Object data) {
        return new com.yunjin.api_backed.common.Result(CODE_SUCCESS, "请求成功", data);
    }

    public static com.yunjin.api_backed.common.Result error(String msg) {
        return new com.yunjin.api_backed.common.Result(CODE_SYS_ERROR, msg, null);
    }

    public static com.yunjin.api_backed.common.Result error(String code, String msg) {
        return new com.yunjin.api_backed.common.Result(code, msg, null);
    }

    public static com.yunjin.api_backed.common.Result error() {
        return new com.yunjin.api_backed.common.Result(CODE_SYS_ERROR, "系统错误", null);
    }

}