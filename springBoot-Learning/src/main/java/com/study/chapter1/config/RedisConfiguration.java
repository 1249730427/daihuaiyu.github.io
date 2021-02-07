package com.study.chapter1.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * redis配置文件
 *
 * @author daihuaiyu
 * @create: 2021-02-07 13:07
 **/
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisConnectionFactory jedisConnectionFactory(){

        return new JedisConnectionFactory();
    }

    /**
     * 支持存储对象
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate(){

        RedisTemplate<String,String> redisTemplate = new StringRedisTemplate();
        //1.设置redis工厂为jedisConnectionFactory
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        //2.构建Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //3.redisTemplate设置ValueSerializer
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}

