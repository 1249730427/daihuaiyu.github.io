package com.daihuaiyu.chat.server.factory;

import com.daihuaiyu.chat.server.service.ChatHandleService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ConcurrentHashMap;

/**
 * MsgType的工厂类
 *
 * @author :daihuaiyu
 * @Description: MsgType的工厂类：根据不同的MsgType返回不同的服务
 * @create 2021/3/27 22:16
 */
public class MagTypeFactory implements ApplicationContextAware {

    private static final String MSG_TYPE = null;

    private ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap<>();

    //在Spring容器启动时把Bean放入容器中
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ChatHandleService bean = applicationContext.getBean(ChatHandleService.class);
        concurrentHashMap.put(MSG_TYPE,bean);
    }
}
