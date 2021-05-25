package com.daihuaiyu.secondskill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.daihuaiyu.secondskill.dao.GoodsDao;
import com.daihuaiyu.secondskill.domain.Goods;
import com.daihuaiyu.secondskill.domain.MiaoshaGoods;
import com.daihuaiyu.secondskill.redis.GoodsKey;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
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
    public List<GoodsVo> getGoodsVo() {
        //增加对象级缓存
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        List<GoodsVo> goodsVoList = JSON.parseObject(opsForValue.get(GoodsKey.getGoodsList.getPrefix() + getClass().getSimpleName() + "gl"), new TypeReference<ArrayList<GoodsVo>>() {
        });
        if (goodsVoList != null) {
            return goodsVoList;
        }
        goodsVoList = goodsDao.getGoodsVoList();
        if (goodsVoList != null && goodsVoList.size() > 0) {
            opsForValue.set(GoodsKey.getGoodsList.getPrefix() + getClass().getSimpleName() + "gl", JSON.toJSONString(goodsVoList), 60 * 10, TimeUnit.SECONDS);
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
        GoodsVo goodsVo = JSON.parseObject((String) opsForHash.get(GoodsKey.getGoodsDetail.getPrefix() + getClass().getSimpleName() + "gd", "" + goodsId), GoodsVo.class);
        if (goodsVo != null) {
            return goodsVo;
        }
        goodsVo = goodsDao.getGoodsVoByGoodsId(goodsId);
        if (goodsVo != null) {
            opsForHash.put(GoodsKey.getGoodsDetail.getPrefix() +  "gd", "" + goodsId, JSON.toJSONString(goodsVo));
            redisTemplate.expire(GoodsKey.getGoodsDetail.getPrefix() + getClass().getSimpleName() + "gd", 60 * 10, TimeUnit.SECONDS);
        }
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    /**
     * 减库存
     *
     * @param goods
     */
    @Override
    @Transactional
    public void reduceStock(Goods goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
        goodsDao.reduceMiaoshaStock(g);
        redisTemplate.opsForHash().delete(GoodsKey.getGoodsDetail.getPrefix() +getClass().getSimpleName() + "gd", "" + goods.getId());
    }
}

