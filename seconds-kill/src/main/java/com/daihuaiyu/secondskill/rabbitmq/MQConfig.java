package com.daihuaiyu.secondskill.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * MQ配置类
 *
 * @author daihuaiyu
 * @create: 2021-05-25 18:01
 **/
@Configuration
public class MQConfig {

    //定义队列名称以及交换机名称
    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String HEADER_QUEUE = "header.queue";
    public static final String TOPIC_EXCHANGE = "topicExchage";
    public static final String FANOUT_EXCHANGE = "fanoutxchage";
    public static final String HEADERS_EXCHANGE = "headersExchage";

    /**
     * Direct模式 交换机Exchange
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(MQConfig.QUEUE,true);
    }

    /**
     * Topic模式 交换机Exchange
     * @return
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }



}

