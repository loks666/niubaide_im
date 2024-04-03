package com.niubaide.im.netty;

import com.alibaba.fastjson.JSON;
import com.niubaide.im.pojo.bean.TbChatRecord;
import com.niubaide.im.service.ChatRecordService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * 处理消息的handler
 * TextWebSocketFrame: 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 *
 * @author 李翔
 */
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用来保存所有的客户端连接
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");

    private final ChatRecordService chatRecordService;

    public ChatHandler(ChatRecordService chatRecordService) {
        this.chatRecordService = chatRecordService;
    }

    /**
     * 当Channel中有新的事件消息会自动调用
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame message) throws Exception {
        // 当接收到数据后会自动调用

        // 获取客户端发送过来的文本消息
        String text = message.text();
        System.out.println("接收到消息数据为：" + text);

        Message msg = JSON.parseObject(text, Message.class);
        // 判断如果没有channel, 则添加
        TbChatRecord chatRecord = msg.getChatRecord();
        String msgText = chatRecord.getMessage();
        String friendid = chatRecord.getFriendid();
        String userid = chatRecord.getUserid();
        UserChannelMap.put(userid, ctx.channel());

        switch (msg.getType()) {
            case "REGISTER":
                // 2.2 聊天记录保存到数据库，标记消息的签收状态[未签收]
                // 保存到数据库，并标记为未签收
                String messageId = chatRecordService.insert(chatRecord);
                chatRecord.setId(messageId);
                // 发送消息
                instantSendMsg(msg, friendid);
                break;
            // 将消息记录设置为已读
            case "RECEIVE":
                chatRecordService.msgRead(chatRecord.getId());
                break;
            case "KEEP_HEARTBEAT":
                System.out.println("接收到心跳消息" + JSON.toJSONString(msg));
                break;
            case "RELOAD_FRIEND":
            case "SINGLE_SENDING":
                chatRecordService.insert(chatRecord);
                instantSendMsg(msg, chatRecord.getFriendid());
            default:
                break;
        }
    }

    /**
     * 即时发送消息
     *
     * @param message
     * @param friendid
     */
    private void instantSendMsg(Message message, String friendid) {
        Channel channel1 = UserChannelMap.get(friendid);
        if (channel1 != null) {
            // 从ChannelGroup查找对应的额Channel是否存在
            Channel channel2 = clients.find(channel1.id());
            if (channel2 != null) {
                // 用户在线,发送消息到对应的通道
                System.out.println("发送消息到" + JSON.toJSONString(message));
                channel2.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("异常信息：" + cause.getMessage());
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        ctx.channel().close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("执行移除");
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        UserChannelMap.print();
    }


    /**
     * 当有新的客户端连接服务器之后，会自动调用这个方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 将新的通道加入到clients
        clients.add(ctx.channel());
    }
}
