package com.spring.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 消息发送者
 *
 * @author :daihuaiyu
 * @Description: 消息发送者
 * @create 2021/3/22 22:12
 */
@Slf4j
public class Sender {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topic,String payload){
        log.info("发送消息 sending payload:{} topic:{}",payload,topic);
        kafkaTemplate.send(topic, payload);
    }
}
