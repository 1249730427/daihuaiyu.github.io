package com.daihuaiyu.secondskill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.daihuaiyu.secondskill.dao.GoodsDao;
import com.daihuaiyu.secondskill.domain.Goods;
import com.daihuaiyu.secondskill.domain.MiaoshaGoods;
import com.daihuaiyu.secondskill.mybatis.Pager;
import com.daihuaiyu.secondskill.redis.GoodsKey;
import com.daihuaiyu.secondskill.redis.OrderKey;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 商品服务
 *
 * @author daihuaiyu
 * @create: 2021-05-12 13:51
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 获取商品列表
     */
    @Override
    public List<GoodsVo> getGoodsVo(Pager pager) {
        //增加对象级缓存
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        List<GoodsVo> goodsVoList = JSON.parseObject(opsForValue.get(GoodsKey.getGoodsList.getPrefix() +"goods_list"), new TypeReference<ArrayList<GoodsVo>>() {
        });
        if (goodsVoList != null) {
            return goodsVoList;
        }
        goodsVoList = goodsDao.getGoodsVoList();
        goodsVoList = goodsDao.getGoodsVoList(pager);
        if (goodsVoList != null && goodsVoList.size() > 0) {
            opsForValue.set(GoodsKey.getGoodsList.getPrefix() +  "goods_list", JSON.toJSONString(goodsVoList), 60 * 10, TimeUnit.SECONDS);
        }
        return goodsVoList;
    }

    /**
     * 获取商品详情
     *
     * @param goodsId
     */
    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        GoodsVo goodsVo = JSON.parseObject((String) opsForHash.get(GoodsKey.getGoodsDetail.getPrefix()  + "good_detail", "" + goodsId), GoodsVo.class);
        if (goodsVo != null) {
            return goodsVo;
        }
        goodsVo = goodsDao.getGoodsVoByGoodsId(goodsId);
        if (goodsVo != null) {
            opsForHash.put(GoodsKey.getGoodsDetail.getPrefix() +  "good_detail", "" + goodsId, JSON.toJSONString(goodsVo));
            redisTemplate.expire(GoodsKey.getGoodsDetail.getPrefix() +  "good_detail", 60 * 10, TimeUnit.SECONDS);
        }
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    /**
     * 减库存
     *
     * @param goods
     */
    @Override
    public boolean reduceStock(Goods goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        int ret = goodsDao.reduceMiaoshaStock(g);
        //1.删除商品列表页面缓存
        Boolean hasGlKey = redisTemplate.hasKey(GoodsKey.getGoodsList.getPrefix() + "gl");
        if(hasGlKey){
            redisTemplate.delete(GoodsKey.getGoodsList.getPrefix() + "gl");
        }
        //2.删除商品信息缓存
        Boolean hasGoodList = redisTemplate.hasKey(GoodsKey.getGoodsList.getPrefix() +  "goods_list");
        if(hasGoodList){
            redisTemplate.delete(GoodsKey.getGoodsList.getPrefix() +  "goods_list");
        }
        //3.删除商品详情缓存
        Boolean hasGoodsDetail = redisTemplate.hasKey(GoodsKey.getGoodsDetail.getPrefix() +  "good_detail");
        if(hasGoodsDetail){
            redisTemplate.opsForHash().delete(GoodsKey.getGoodsDetail.getPrefix() + "good_detail", "" + goods.getId());
        }
        return ret>0;
    }
}

