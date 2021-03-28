package com.daihuaiyu.chat.server.config;

import com.daihuaiyu.chat.server.netty.NettyServer;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Netty启动监听类
 *
 * @author :daihuaiyu
 * @Description: Netty启动监听类：用于SpringBoot启动加载该配置启动Netty服务
 * @create 2021/3/28 10:29
 */
@Component
public class NettybootServerInitConfig implements ApplicationListener<ContextRefreshedEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null) {
            NettyServer.getInstance().startNettyServer(8092);
        }
    }
}
