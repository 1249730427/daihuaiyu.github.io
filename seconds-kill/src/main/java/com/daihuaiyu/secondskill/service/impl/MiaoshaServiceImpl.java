package com.daihuaiyu.secondskill.service.impl;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.secondskill.dao.OrderDao;
import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.redis.MiaoshaKey;
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
import org.springframework.transaction.annotation.Transactional;

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
        MiaoshaOrder miaoshaOrder = JSON.parseObject((String) opsForHash.get(OrderKey.getMiaoshaOrderByUidGid.getPrefix()+"moug", "" + userId + "_" + goodsId), MiaoshaOrder.class);
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
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        boolean isOver = goodsService.reduceStock(goods);
        if(isOver){
            return orderService.createOrder(user, goods);
        }
        redisTemplate.opsForHash().put(MiaoshaKey.isGoodsOver.getPrefix()+"go",""+goods.getId(),true);
        return null;
    }

    /**
     * 根据用户ID和商品ID查询订单信息
     *
     * @param id
     * @param goodsId
     */
    @Override
    public Long getMiaoshaResult(Long id, long goodsId) {
        MiaoshaOrder miaoshaOrder = getMiaoshaOrderByUserIdGoodsId(id, goodsId);
        if(miaoshaOrder !=null){
            return miaoshaOrder.getOrderId();
        }
        boolean over = (boolean) redisTemplate.opsForHash().get(MiaoshaKey.isGoodsOver.getPrefix() + "go", "" + goodsId);
        if(over){
            return -1L;
        }
        return 0L;
    }


}

