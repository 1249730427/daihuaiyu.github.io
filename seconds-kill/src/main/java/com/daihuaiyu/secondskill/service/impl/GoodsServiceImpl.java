package com.daihuaiyu.secondskill.service.impl;

import com.daihuaiyu.secondskill.dao.GoodsDao;
import com.daihuaiyu.secondskill.domain.Goods;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daihuaiyu
 * @create: 2021-05-12 13:51
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    /**
     * 获取商品列表
     */
    @Override
    public List<GoodsVo> getGoodsVo() {
        List<GoodsVo> goodsVoList = goodsDao.getGoodsVoList();
        return goodsVoList;
    }

    /**
     * 获取商品详情
     *
     * @param goodsId
     */
    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }
}

