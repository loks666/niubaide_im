package com.niubaide.im.pojo.vo;

import lombok.Data;

/**
 * @Author 飞飞
 * @Description
 * @Date 2020/6/16 13:41
 */
public class ResultVo<T> {

    private boolean success;
    private String message;
    private T data;

    public ResultVo(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResultVo(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultVo(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResultVo(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
