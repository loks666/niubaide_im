package com.niubaide.im.netty;

import com.niubaide.im.service.ChatRecordService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author fly
 */
@Component
public class WebSocketServer implements Runnable{

    @Autowired
    private ChatRecordService chatRecordService;

    /**
     * 主线程池
     */
    private EventLoopGroup bossGroup;

    /**
     * 工作线程池
     */
    private EventLoopGroup workerGroup;

    /**
     * 服务器
     */
    private ServerBootstrap server;

    /**
     * 回调
     */
    private ChannelFuture future;
    @Value(" ${websocket.port}")
    private int port;

    @Override
    public void run() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        server = new ServerBootstrap();
        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebsocketInitializer(chatRecordService));
        future = server.bind(port);
        System.err.println("netty server - " + port + " 启动成功");
    }
}
