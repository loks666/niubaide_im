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
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

/**
 * 处理消息的handler
 * TextWebSocketFrame: 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 *
 * @author 李翔
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用来保存所有的客户端连接
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");

    @Autowired
    private ChatRecordService chatRecordService;

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
        switch (msg.getType()) {
            case "REGISTER":
                // 2.2 聊天记录保存到数据库，标记消息的签收状态[未签收]
                TbChatRecord chatRecord = msg.getChatRecord();
                String msgText = chatRecord.getMessage();
                String friendid = chatRecord.getFriendid();
                String userid1 = chatRecord.getUserid();
                // 保存到数据库，并标记为未签收
                String messageId = chatRecordService.insert(chatRecord);
                chatRecord.setId(messageId);
                // 发送消息
                Channel channel1 = UserChannelMap.get(friendid);
                if(channel1 != null) {
                    // 从ChannelGroup查找对应的额Channel是否存在
                    Channel channel2 = clients.find(channel1.id());
                    if(channel2 != null) {
                        // 用户在线,发送消息到对应的通道
                        System.out.println("发送消息到" + JSON.toJSONString(message));
                        channel2.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    }
                }
                break;
            // 将消息记录设置为已读
            case "RECEIVE":
                chatRecordService.msgRead(msg.getChatRecord().getId());
                break;

            case "KEEP_HEARTBEAT":
                System.out.println("接收到心跳消息" + JSON.toJSONString(msg));
                break;
            case "RELOAD_FRIEND":
            case "SINGLE_SENDING":
            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        ctx.channel().close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        System.out.println("关闭通道");
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
