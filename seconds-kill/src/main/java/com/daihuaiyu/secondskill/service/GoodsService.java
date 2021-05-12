package com.daihuaiyu.secondskill.service;

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
    List<GoodsVo> getGoodsVo();
}
