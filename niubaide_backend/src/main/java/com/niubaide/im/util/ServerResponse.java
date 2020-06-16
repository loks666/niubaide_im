package com.niubaide.im.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: jax
 */
// 生成无参构造，确保在RPC调用时，不会出现反序列失败
@NoArgsConstructor
public class ServerResponse<T> implements Serializable {

    private int status;
    private boolean success;
    private String message;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(ResponseCode code,boolean success) {
        this.success = success;
        this.message = new StringBuilder(code.getCode()).append(":").append(code.getDesc()).toString();
    }

    private ServerResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    //  这里存在一个问题，如果构造函数传入的参数列表为(int,String)，那么是调用上面的（int,String），还是这里的（int,T)，毕竟T作为泛型是可以表示String的
    //  答案是调用上面的（int,String)（可以理解为上面的是专业的）。那么有时候data作为T类型传入的就是String啊，岂不是就出问题了。这里会在下方对应的public函数处理
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private ServerResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ServerResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }


    //    使之不在JSON序列化结果当中
//    @JSONField(serialize = false)
    // 可以快速进行成功与否的条件判断
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    @JSONField(serialize = false)
    // 可以快速进行成功与否的条件判断，判断false时，不用加!。囧
    public boolean isFail() {
        return this.status != ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    // 快速构建返回结果
    //    成功时的调用
    public static <T> ServerResponse<T> success() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> success(String msg) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> success(ResponseCode code) {
        return new ServerResponse(code,true);
    }

    public static <T> ServerResponse<T> success(String msg, T data) {
        return new ServerResponse<T>(true, msg, data);
    }

    public static <T> ServerResponse<T> success(ResponseCode responseCode, T data) {
        return new ServerResponse(true, responseCode.getDesc(), data);
    }

    //    失败时的调用
    public static <T> ServerResponse<T> error() {
        return new ServerResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> error(String errorMessage) {
        return new ServerResponse(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> error(ResponseCode responseCode) {
        return new ServerResponse(responseCode,false);
    }


    public static <T> ServerResponse<T> error(int errorCode, String errorMessage) {
        return new ServerResponse(errorCode, errorMessage);
    }
}
