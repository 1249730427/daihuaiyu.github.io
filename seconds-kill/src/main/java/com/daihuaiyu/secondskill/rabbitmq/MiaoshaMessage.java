package com.daihuaiyu.secondskill.rabbitmq;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;

/**
 * 秒杀消息信息
 *
 * @author daihuaiyu
 * @create: 2021-05-26 15:45
 **/
public class MiaoshaMessage {

    private MiaoshaUser miaoshaUser;

    private long goodsId;

    public MiaoshaUser getMiaoshaUser() {
        return miaoshaUser;
    }

    public void setMiaoshaUser(MiaoshaUser miaoshaUser) {
        this.miaoshaUser = miaoshaUser;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}

