package com.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author :daihuaiyu
 * @Description: 消息接收器
 * @create 2021/3/23 20:56
 */
@Slf4j
public class Receiver {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public Receiver(final CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public Receiver() {
    }

    @KafkaListener(topics = "${kafka.topic.helloworld}")
    public void receive(String payLoad){
        log.info("接收到消息payLoad:{}",payLoad);
        countDownLatch.countDown();
    }

}
