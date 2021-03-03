package com.niubaide.im.pojo.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author fly
 */
@Data
@Accessors(chain = true)
public class TbFriendReq {

    /**
     * 自动填充雪花算法id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String fromUserid;

    private String toUserid;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

    private String message;

    private Integer status;

}