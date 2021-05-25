package com.daihuaiyu.secondskill.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息发送者配置类
 *
 * @author daihuaiyu
 * @create: 2021-05-25 17:25
 **/
@Service
public class MessageSender {

    private Logger log = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendMessage(Object message){
        String msg = JSON.toJSONString(message);
        log.info("发送消息："+msg);
        rabbitTemplate.convertAndSend("QUEUE",msg);
    }

}

