package com.niubaide.im.pojo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class TbFriendReq {
    private String id;

    private String fromUserid;

    private String toUserid;

    private Date createtime;

    private String message;

    private Integer status;

}