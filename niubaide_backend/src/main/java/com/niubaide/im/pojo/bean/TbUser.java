package com.niubaide.im.pojo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * @author 飞飞
 * @description 数据库用户对象
 * @date 2020/6/14 17:30
 */
@Data
@Accessors(chain = true)
public class TbUser {

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
    private Date createtime;
    /**
     * 手机号
     */
    private String phone;

}