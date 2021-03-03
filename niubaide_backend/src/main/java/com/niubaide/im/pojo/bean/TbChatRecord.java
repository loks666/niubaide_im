package com.niubaide.im.pojo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author fly
 */
@Data
@Accessors(chain = true)
public class TbChatRecord {
    private String id;

    private String userid;

    private String friendid;

    private Integer hasRead;

    private Date createtime;

    private Integer hasDelete;

    private String message;

}