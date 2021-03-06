package com.niubaide.im.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Jax
 */
@Component
public class NettyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    public WebSocketServer websocketServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                new Thread(websocketServer).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
