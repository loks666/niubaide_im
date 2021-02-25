package com.niubaide.im.expection;

/**
 * @Author 飞飞
 * @Description
 * @Date 2020/6/24 13:14
 */
public class ImException extends RuntimeException{

    /**
     * 异常信息
     */
    private String message;

    /**
     * 构造函数
     * @param message
     */
    public ImException(String message){
        super(message);
        this.message = message;
    }


}
