package com.daihuaiyu.secondskill.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.redis.GoodsKey;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.service.MiaoshaService;
import com.daihuaiyu.secondskill.service.OrderService;
import com.daihuaiyu.secondskill.util.Result;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 立即秒杀业务逻辑
 *
 * @author daihuaiyu
 * @create: 2021-05-21 15:49
 **/
@Controller
@RequestMapping(value = "/miaosha")
public class MiaoshaController implements InitializingBean {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private RedisTemplate redisTemplate;

    private Map<String,Object> concurrentHashMap =  new ConcurrentHashMap<>();

    @PostMapping(value = "/do_miaosha")
    @ResponseBody
    public Result doMiaosha(MiaoshaUser miaoshaUser, @RequestParam(value = "goodsId") long goodsId) {
        if(miaoshaUser ==null){
            return Result.error(CodeEnum.SESSION_ERROR);
        }
        /**
         * 异步下单：
         * 1.系统初始化时将秒杀库存预加载到redis中，从redis中获取
         * 2.利用内存标记减少对redis的访问
         * 3.进入消息队列中，入队，返回排队中
         * 4.异步处理减库存，写订单
         * 5.客户端轮询访问是否秒杀到商品信息
         * */
        Boolean check = (Boolean) concurrentHashMap.get(""+goodsId);
        if(!check.booleanValue()){
           return Result.error(CodeEnum.MIAO_SHA_OVER);
        }
        HashOperations opsForHash = redisTemplate.opsForHash();
        Integer stock = JSON.parseObject((String) opsForHash.get(GoodsKey.getMiaoshaGoodsStock.getPrefix() + "gs", "" + goodsId),Integer.class);
        if(stock<=0){
            return Result.error(CodeEnum.MIAO_SHA_OVER);
        }
         opsForHash.increment(GoodsKey.getMiaoshaGoodsStock.getPrefix() + "gs", "" + goodsId,-1);
//        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
//        Integer stockCount = goodsVo.getStockCount();
//        if(stockCount<=0){
//            return Result.error(CodeEnum.MIAO_SHA_OVER);
//        }
        MiaoshaOrder miaoshaOrder = miaoshaService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if(miaoshaOrder!=null){
            return Result.error(CodeEnum.REPEATE_MIAOSHA);
        }
        //下订单，下秒杀订单，减库存，减秒杀库存

        OrderInfo orderInfo = miaoshaService.miaosha(miaoshaUser, goodsVo);
        return Result.success(orderInfo);
    }

    @Override
    public void afterPropertiesSet() {
        HashOperations opsForHash = redisTemplate.opsForHash();
        List<GoodsVo> goodsVo = goodsService.getGoodsVo();
        if(goodsVo ==null || goodsVo.size()>0){
            return;
        }
        goodsVo.stream().forEach(goods ->{
            opsForHash.put(GoodsKey.getMiaoshaGoodsStock.getPrefix()+"gs",""+goods.getId(), JSON.toJSONString(goods.getStockCount()));
            concurrentHashMap.put(""+goods.getId(),true);
                }

        );

    }
}

