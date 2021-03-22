package com.spring.kafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka生产者配置类
 *
 * @author :daihuaiyu
 * @Description: Kafka生产者配置类
 * @create 2021/3/22 21:48
 */
@Configuration
public class SenderConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String,Object> configs(){
        final Map<String,Object> producerConfigs = new HashMap<>();
        producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        producerConfigs.put(ProducerConfig.ACKS_CONFIG,"0");
        return producerConfigs;
    }

    @Bean
    public ProducerFactory<String,String> producerFactory(){

        return new DefaultKafkaProducerFactory(configs());
    }

    @Bean
    public KafkaTemplate kafkaTemplate(){

        return new KafkaTemplate(producerFactory());
    }

    @Bean
    public Sender sender(){
        return new Sender();
    }
}
