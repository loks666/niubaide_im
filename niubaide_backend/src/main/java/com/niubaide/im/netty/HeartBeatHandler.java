package com.niubaide.im.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 检测Channel的心跳Handler
 *
 * @author fly
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端在一定的时间没有动作就会触发这个事件
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 用于触发用户事件，包含读空闲/写空闲
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("读空闲...");
            }
            if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("写空闲...");
            }
            if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("关闭客户端通道");
                // 关闭通道，避免资源浪费
                ctx.channel().close();
            }
        }
    }
}
