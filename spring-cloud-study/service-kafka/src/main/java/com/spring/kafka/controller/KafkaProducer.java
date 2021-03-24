package com.spring.kafka.controller;

import com.spring.kafka.producer.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Kafka生产者请求类
 *
 * @author :daihuaiyu
 * @Description: Kafka生产者请求类
 * @create 2021/3/23 21:03
 */
@Slf4j
@RestController
public class KafkaProducer {

    @Autowired
    private Sender sender;

    @RequestMapping(path = "/sender",method= RequestMethod.POST )
    public void exec(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,String data) throws IOException {
        log.info("开始发送消息：");
        sender.sendMessage("testTopic",data);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json");
        httpServletResponse.getWriter().write("success");
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
        log.info("结束发送消息：");
    }
}
