package com.niubaide.im.util;

public enum ResponseCode {
    NEED_LOGIN(10, "需要登录！"),
    SUCCESS(200, "成功！"),
    LOGIN_SUCCESS(201, "登录成功！"),
    ERROR(300, "错误"),
    NULL_PARAM(301, "参数不能为空！"),
    ILLEGAL_PARAMETER(302, "非法参数！"),
    LOGIN_FAILED(303, "登录失败！请检查用户名和密码是否正确！"),
    SERVER_ERROR(500, "服务器内部错误！");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
