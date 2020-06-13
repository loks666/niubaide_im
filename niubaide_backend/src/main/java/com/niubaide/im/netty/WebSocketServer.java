package com.niubaide.im.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer {

    private EventLoopGroup bossGroup;       // 主线程池
    private EventLoopGroup workerGroup;     // 工作线程池
    private ServerBootstrap server;         // 服务器
    private ChannelFuture future;           // 回调
    @Value("${websocket.port}")
    private int port;

    public void start() {
        future = server.bind(port);
        System.err.println("netty server - " + port + " 启动成功");
    }

    public WebSocketServer() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        server = new ServerBootstrap();
        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebsocketInitializer());
    }
}
