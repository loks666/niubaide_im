package com.niubaide.im.netty;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fly
 */
public class UserChannelMap {

    /**
     * 用户握手实例
     */
    private static Map<String, Channel> userChannelMap;

    static {
        userChannelMap = new ConcurrentHashMap();
    }

    /**
     * 添加握手实例
     *
     * @param id
     * @param channel
     */
    public static void put(String id, Channel channel) {
        userChannelMap.put(id, channel);
    }

    /**
     * 移除握手实例
     *
     * @param id
     */
    public static void remove(String id) {
        userChannelMap.remove(id);
    }

    /**
     * 输出在线用户实例
     */
    public static void print() {
        Set<String> keys = userChannelMap.keySet();
        keys.stream().forEach(key -> System.out.println("用户id:" + key + "握手实例：" + userChannelMap.get(key).id()));
    }

    /**
     * 根据通道id移除用户与channel的关联
     * @param channelId 通道的id
     */
    public static void removeByChannelId(String channelId) {
        if(!StringUtils.isNotBlank(channelId)) {
            return;
        }

        for (String s : userChannelMap.keySet()) {
            Channel channel = userChannelMap.get(s);
            if(channelId.equals(channel.id().asLongText())) {
                System.out.println("客户端连接断开,取消用户" + s + "与通道" + channelId + "的关联");
                userChannelMap.remove(s);
                break;
            }
        }
    }

    public static Channel get(String friendid) {
        return userChannelMap.get(friendid);
    }
}
