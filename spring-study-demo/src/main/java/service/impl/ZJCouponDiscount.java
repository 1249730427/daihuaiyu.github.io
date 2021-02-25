package service.impl;

import service.ICouponDiscountService;

import java.math.BigDecimal;

/**
 * 直减实现类
 *
 * @author daihuaiyu
 * @create: 2021-02-25 13:07
 **/
public class ZJCouponDiscount implements ICouponDiscountService<Double> {
    /**
     * 直减计算
     * 1. 使用商品价格减去优惠价格
     * 2. 最低支付金额1元
     */
    @Override
    public BigDecimal discountAmount(Double CouponInfo, Double originalPrice) {
        return new BigDecimal(originalPrice-CouponInfo<1.0?1.0:(originalPrice-CouponInfo));
    }
}

