package com.daihuaiyu.secondskill.service.impl;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.secondskill.dao.OrderDao;
import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.redis.OrderKey;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.service.MiaoshaService;
import com.daihuaiyu.secondskill.service.OrderService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author daihuaiyu
 * @create: 2021-05-21 16:44
 **/
@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Resource
    private OrderDao orderDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    /**
     * 根据商品ID查找秒杀订单信息
     *
     * @param goodsId
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        //return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        HashOperations opsForHash = redisTemplate.opsForHash();
        MiaoshaOrder miaoshaOrder = JSON.parseObject((String) opsForHash.get(OrderKey.getMiaoshaOrderByUidGid.getPrefix().getClass().getSimpleName()+"moug", "" + userId + "_" + goodsId), MiaoshaOrder.class);
        if(miaoshaOrder !=null ){
            return miaoshaOrder;
        }
        miaoshaOrder = orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        return miaoshaOrder;
    }

    /**
     * 下订单，减库存
     *
     * @param user
     * @param goods
     */
    @Override
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        //order_info maiosha_order
        return orderService.createOrder(user, goods);
    }


}

