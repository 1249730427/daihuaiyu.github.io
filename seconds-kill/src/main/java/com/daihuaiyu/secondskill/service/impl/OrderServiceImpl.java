package com.daihuaiyu.secondskill.service.impl;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.secondskill.dao.OrderDao;
import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.redis.OrderKey;
import com.daihuaiyu.secondskill.service.OrderService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author daihuaiyu
 * @create: 2021-05-21 16:16
 **/
@Service
public class OrderServiceImpl implements OrderService {


    @Resource
    OrderDao orderDao;

    @Autowired
    private RedisTemplate redisTemplate;


    public OrderInfo getOrderById(long orderId) {
        return orderDao.getOrderById(orderId);
    }

    /**
     * 下单接口
     *
     * @param user
     * @param goods
     */
    @Override
    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setCreateDate(new Date());
            orderInfo.setDeliveryAddrId(0L);
            orderInfo.setGoodsCount(1);
            orderInfo.setGoodsId(goods.getId());
            orderInfo.setGoodsName(goods.getGoodsName());
            orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
            orderInfo.setOrderChannel(1);
            orderInfo.setStatus(0);
            orderInfo.setUserId(user.getId());
            long orderId = orderDao.insert(orderInfo);
            MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
            miaoshaOrder.setGoodsId(goods.getId());
            miaoshaOrder.setOrderId(orderId);
            miaoshaOrder.setUserId(user.getId());
            orderDao.insertMiaoshaOrder(miaoshaOrder);

        redisTemplate.opsForHash().put(OrderKey.getMiaoshaOrderByUidGid.getPrefix().getClass().getSimpleName()+"moug", ""+user.getId()+"_"+goods.getId(),JSON.toJSONString(miaoshaOrder));

            return orderInfo;
        }
    }

