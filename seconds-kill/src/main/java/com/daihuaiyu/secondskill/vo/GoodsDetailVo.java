package com.daihuaiyu.secondskill.vo;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;

/**
 * 商品详情Vo
 *
 * @author daihuaiyu
 * @create: 2021-05-24 15:10
 **/
public class GoodsDetailVo {

    private MiaoshaUser user;

    private GoodsVo goods;

    private Long remainSeconds;

    private Integer miaoshaStatus;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoodsVo(GoodsVo goods) {
        this.goods = goods;
    }

    public Long getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(Long remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public Integer getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(Integer miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }
}

