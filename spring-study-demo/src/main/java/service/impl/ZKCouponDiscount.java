package service.impl;

import service.ICouponDiscountService;

import java.math.BigDecimal;

/**
 * 折扣实现类
 *
 * @author daihuaiyu
 * @create: 2021-02-25 13:11
 **/
public class ZKCouponDiscount implements ICouponDiscountService<Double> {
    /**
     * 折扣计算
     * 1. 使用商品价格乘以折扣比例，为最后支付金额
     * 2. 保留两位小数
     * 3. 最低支付金额1元
     */
    @Override
    public BigDecimal discountAmount(Double CouponInfo, Double originalPrice) {
        Integer discountAmount= (new BigDecimal(originalPrice).multiply(
                new BigDecimal(CouponInfo)).setScale(2,BigDecimal.ROUND_HALF_UP)).intValue();
        return new BigDecimal(discountAmount<1?1.00:new Double(String.valueOf(discountAmount)));
    }
}

