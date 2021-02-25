package com.niubaide.im.pojo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class TbFriend {
    private String id;

    private String userid;

    private String friendsId;

    private String comments;

    private Date createtime;

}