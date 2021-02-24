package com.niubaide.im.controller;

import com.niubaide.im.pojo.bean.TbFriendReq;
import com.niubaide.im.service.FriendService;
import com.niubaide.im.util.ResponseCode;
import com.niubaide.im.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fly
 */
@RestController
@Slf4j
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/sendRequest")
    public ServerResponse sendRequest(@RequestBody TbFriendReq req) {
        try {
            boolean result = friendService.sendRequest(req);
            if (result) {
                return ServerResponse.success("发送好友请求成功！");
            }
            return ServerResponse.error("发送好友请求失败！");
        } catch (Exception e) {
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

}
