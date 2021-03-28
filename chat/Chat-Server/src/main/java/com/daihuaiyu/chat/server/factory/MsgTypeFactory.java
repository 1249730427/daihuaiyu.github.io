package com.daihuaiyu.chat.server.factory;

import com.daihuaiyu.chat.server.service.ChatHandleService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MsgType的工厂类
 *
 * @author :daihuaiyu
 * @Description: MsgType的工厂类：根据不同的MsgType返回不同的服务
 * @create 2021/3/27 22:16
 */
@Component
public class MsgTypeFactory implements ApplicationContextAware {

    private static ConcurrentHashMap<String,ChatHandleService> concurrentHashMap = new ConcurrentHashMap<>();

    //在Spring容器启动时把Bean放入容器中
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ChatHandleService> chatHandleServiceMap = applicationContext.getBeansOfType(ChatHandleService.class);
        chatHandleServiceMap.forEach((key,value) ->concurrentHashMap.put(value.getMSG_TYPE(),value)) ;
    }

    public static ChatHandleService getChatHandleService(String msgType){
        ChatHandleService chatHandleService = concurrentHashMap.get(msgType);
        if(chatHandleService != null){
            return chatHandleService;
        }else{
            return null;
        }
    }
}
