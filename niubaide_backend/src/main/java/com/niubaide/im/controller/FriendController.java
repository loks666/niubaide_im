package com.niubaide.im.controller;

import com.niubaide.im.pojo.bean.TbFriendReq;
import com.niubaide.im.pojo.vo.User;
import com.niubaide.im.service.FriendService;
import com.niubaide.im.service.FriendServiceReq;
import com.niubaide.im.util.ResponseCode;
import com.niubaide.im.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fly
 */
@RestController
@Slf4j
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendServiceReq reqService;

    @PostMapping("/sendRequest")
    public ServerResponse sendRequest(@RequestBody TbFriendReq req) {
        try {
            Assert.hasText(req.getFromUserid(), "发送人不能为空！");
            Assert.hasText(req.getToUserid(), "接收人不能为空！");
            boolean result = friendService.sendRequest(req);
            if (result) {
                return ServerResponse.success("发送好友请求成功！");
            }
            return ServerResponse.error("发送好友请求失败！");
        } catch (Exception e) {
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @PostMapping("/findFriendReqByUserid")
    public ServerResponse findFriendReqByUserid(String userid) {
        try {
            Assert.hasText(userid, "用户id不能为空！");
            List<User> result = friendService.getFriendReq(userid);
            return ServerResponse.success(result);
        } catch (Exception e) {
            log.error("ImController#findFriendReqByUserid", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @PostMapping("/acceptFriendReq")
    public ServerResponse acceptFriendReq(String reqId) {
        try {
            Assert.hasText(reqId, "请求id不能为空！");
            boolean result = friendService.acceptFriendReq(reqId);
            return ServerResponse.success(result);
        } catch (Exception e) {
            log.error("ImController#acceptFriendReq", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @PostMapping("/ignoreFriendReq")
    public ServerResponse ignoreFriendReq(String reqId) {
        try {
            Assert.hasText(reqId, "请求id不能为空！");
            boolean result = friendService.ignoreFriendReq(reqId);
            return ServerResponse.success(result);
        } catch (Exception e) {
            log.error("ImController#ignoreFriendReq", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    /**
     * 加载通讯录好友
     * @param userid
     * @return
     */
    @PostMapping("/findFriendByUserid")
    public ServerResponse findFriendByUserid(String userid) {
        try {
            Assert.hasText(userid, "用户id不能为空！");
            List<User> result = friendService.findFriendByUserid(userid);
            return ServerResponse.success(result);
        } catch (Exception e) {
            log.error("ImController#findByUserName", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }


}
