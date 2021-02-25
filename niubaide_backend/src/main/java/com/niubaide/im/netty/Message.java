package com.niubaide.im.netty;

import com.niubaide.im.pojo.bean.TbChatRecord;
import lombok.Data;

/**
 * 消息实体类
 *
 * @author fly
 */
@Data
public class Message {

    /**
     * 类型
     */
    private String type;

    /**
     * 消息类
     */
    private TbChatRecord chatRecord;

    /**
     * 消息扩展
     */
    private Object ext;

}
