package com.niubaide.im.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niubaide.im.pojo.bean.TbChatRecord;

import java.util.List;

/**
 * @author fly
 */
public interface ChatRecordService extends IService<TbChatRecord> {

    /**
     * 保存聊天记录到服务器
     * @param chatRecord
     */
    String insert(TbChatRecord chatRecord);

    /**
     * 消息已读
     * @param id
     */
    void msgRead(String id);

    /**
     * 获取用户未读消息
     * @param userid
     * @return
     */
    List<TbChatRecord> findUnreadByUserid(String userid);

    /**
     * 查询用户聊天记录
     * @param userid 用户id
     * @param friendId 好友id
     * @return 两个用户发送给对方的聊天记录
     */
    List<TbChatRecord> findByUserIdAndFriendId(String userid,String friendId);
}
