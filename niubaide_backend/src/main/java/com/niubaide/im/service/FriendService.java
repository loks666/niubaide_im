package com.niubaide.im.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niubaide.im.pojo.bean.TbFriend;
import com.niubaide.im.pojo.bean.TbFriendReq;

/**
 * @author fly
 */
public interface FriendService extends IService<TbFriend> {

    /**
     * 发送好友请求
     * @param req
     * @return
     */
    boolean sendRequest(TbFriendReq req);
}
