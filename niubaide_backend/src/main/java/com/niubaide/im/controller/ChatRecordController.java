package com.niubaide.im.controller;

import com.niubaide.im.pojo.bean.TbChatRecord;
import com.niubaide.im.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatRecordController {

    @Autowired
    private ChatRecordService chatRecordService;

    @RequestMapping("/findUnreadByUserid")
    public List<TbChatRecord> findUnreadByUserid(String userid) {
        try {
            return chatRecordService.findUnreadByUserid(userid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<TbChatRecord>();
        }
    }

    @RequestMapping("/findByUserIdAndFriendId")
    public List<TbChatRecord> findByUserIdAndFriendId(String userid, String friendId) {
        try {
            return chatRecordService.findByUserIdAndFriendId(userid, friendId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

}
