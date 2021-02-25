package com.niubaide.im.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niubaide.im.pojo.bean.TbFriend;
import com.niubaide.im.pojo.bean.TbFriendReq;
import com.niubaide.im.pojo.vo.User;

import java.util.List;

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

    /**
     * 获取好友请求的好友信息
     * @param userid
     * @return List<User>请求的好友列表
     */
    List<User> getFriendReq(String userid);

    /**
     * 接受好友请求
     * @param reqId
     * @return boolean 操作结果
     */
    boolean acceptFriendReq(String reqId);

    /**
     * 忽略好友请求
     * @param reqId
     * @return boolean 操作结果
     */
    boolean ignoreFriendReq(String reqId);

    /**
     * 查询好友列表
     * @param userid 当前登录用户id
     * @return List<User> 好友列表
     */
    List<User> findFriendByUserid(String userid);

}
