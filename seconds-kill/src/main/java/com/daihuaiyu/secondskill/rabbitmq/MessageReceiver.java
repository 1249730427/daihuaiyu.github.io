package com.daihuaiyu.secondskill.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息接收者配置类
 *
 * @author daihuaiyu
 * @create: 2021-05-25 17:37
 **/
@Service
public class MessageReceiver {

    private Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitListener(queues = {"QUEUE"})
    public void reviceMessage(Object msg){
        Object receive = rabbitTemplate.convertSendAndReceive(msg);
        logger.info("接收消息:"+ JSON.parseObject((String) receive,String.class));
    }
}

