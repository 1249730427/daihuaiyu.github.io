package com.daihuaiyu.secondskill.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.service.MiaoshaService;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MiaoshaService miaoshaService;

    @RabbitListener(queues = {"QUEUE"})
    public void receiverMessage(Object msg) {
        Object receive = rabbitTemplate.convertSendAndReceive(msg);
        logger.info("接收消息:"+ JSON.parseObject((String) receive,String.class));
    }

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUQE)
    public void receive(String message) {
        MiaoshaMessage miaoshaMessage = JSON.parseObject(message,MiaoshaMessage.class);
        Long goodsId = miaoshaMessage.getGoodsId();
        MiaoshaUser user = miaoshaMessage.getMiaoshaUser();
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goodsVo.getStockCount();
        if(stockCount<=0){
            return ;
        }
        //减库存，写订单信息,写秒杀订单信息
        miaoshaService.miaosha(user, goodsVo);
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive1(String message) {
        logger.info("receive message:" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        logger.info(" topic  queue1 message:" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        logger.info(" topic  queue2 message:" + message);
    }

    @RabbitListener(queues = MQConfig.HEADER_QUEUE)
    public void receiveHeaderQueue(byte[] message) {
        logger.info(" header  queue message:" + new String(message));
    }
}

