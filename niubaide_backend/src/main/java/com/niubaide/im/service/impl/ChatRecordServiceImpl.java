package com.niubaide.im.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niubaide.im.mapper.TbChatRecordMapper;
import com.niubaide.im.pojo.bean.TbChatRecord;
import com.niubaide.im.service.ChatRecordService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fly
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<TbChatRecordMapper, TbChatRecord> implements ChatRecordService {

    @Override
    public String insert(TbChatRecord chatRecord) {
        chatRecord.setHasRead(0);
        chatRecord.setCreatetime(new Date());
        chatRecord.setHasDelete(0);
        insert(chatRecord);
        return chatRecord.getId();
    }

    @Override
    public void msgRead(String id) {
        TbChatRecord tb = new TbChatRecord();
        tb.setId(id);
        tb.setHasRead(1);
        updateById(tb);
    }

    @Override
    public List<TbChatRecord> findUnreadByUserid(String id) {
        TbChatRecord tb = new TbChatRecord();
        tb.setId(id);
        tb.setHasRead(0);
        return list(Wrappers.lambdaQuery(tb));
    }

    @Override
    public List<TbChatRecord> findByUserIdAndFriendId(String userid, String friendId) {
        TbChatRecord tb = new TbChatRecord();
        tb.setUserid(userid);
        tb.setFriendid(friendId);
        List<TbChatRecord> abList = list(Wrappers.lambdaQuery(tb));

        tb.setUserid(friendId);
        tb.setFriendid(userid);
        List<TbChatRecord> baList = list(Wrappers.lambdaQuery(tb));

        baList.forEach(ba -> {
            ba.setHasRead(1);
            updateById(ba);
        });
        // 合并List
        return Stream.of(abList, baList).flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }


}
