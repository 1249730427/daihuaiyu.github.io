package com.daihuaiyu.secondskill.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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

    public void sendMiaoshaMessage(MiaoshaMessage mm) {
        String msg = JSON.toJSONString(mm);
        log.info("send message:"+msg);
        rabbitTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUQE, msg);
    }

    	public void send(Object message) {
		String msg = JSON.toJSONString(message);
		log.info("send message:"+msg);
            rabbitTemplate.convertAndSend(MQConfig.QUEUE, msg);
	}

	public void sendTopic(Object message) {
		String msg = JSON.toJSONString(message);
		log.info("send topic message:"+msg);
        rabbitTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg+"1");
        rabbitTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg+"2");
	}

	public void sendFanout(Object message) {
		String msg = JSON.toJSONString(message);
		log.info("send fanout message:"+msg);
        rabbitTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
	}

	public void sendHeader(Object message) {
		String msg = JSON.toJSONString(message);
		log.info("send header message:"+msg);
		MessageProperties properties = new MessageProperties();
		properties.setHeader("header1", "value1");
		properties.setHeader("header2", "value2");
		Message obj = new Message(msg.getBytes(), properties);
        rabbitTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
	}

}

