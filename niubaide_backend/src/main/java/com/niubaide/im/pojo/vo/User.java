package com.niubaide.im.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author 飞飞
 * @Description
 * @Date 2020/6/14 16:49
 */
@Data
public class User {

    /**
     * 数据库用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 缩略图
     */
    private String picSmall;
    /**
     * 正常图片
     */
    private String picNormal;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 二维码
     */
    private String qrcode;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 签名
     */
    private String sign;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 手机号
     */
    private String phone;

}
