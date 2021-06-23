package com.daihuaiyu.secondskill.service;

import com.daihuaiyu.secondskill.domain.Goods;
import com.daihuaiyu.secondskill.mybatis.Pager;
import com.daihuaiyu.secondskill.vo.GoodsVo;

import java.util.List;

/**
 * 商品服务
 *
 * @author daihuaiyu
 * @create: 2021-05-12 13:48
 **/
public interface GoodsService {

    /**获取商品列表*/
    /**分页获取商品列表*/
    List<GoodsVo> getGoodsVo(Pager pager);

    /**获取商品详情*/
    GoodsVo getGoodsVoByGoodsId(long goodsId);

    /**减库存*/
    boolean reduceStock(Goods goods);
}
