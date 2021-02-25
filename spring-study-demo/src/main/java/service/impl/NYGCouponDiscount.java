package service.impl;

import service.ICouponDiscountService;

import java.math.BigDecimal;

/**
 * N元购实现类
 *
 * @author daihuaiyu
 * @create: 2021-02-25 13:42
 **/
public class NYGCouponDiscount implements ICouponDiscountService<Double> {
     /**
     * n元购购买
     * 无论原价多少钱都固定金额购买
     */
    @Override
    public BigDecimal discountAmount(Double CouponInfo, Double originalPrice) {

        return new BigDecimal(CouponInfo);
    }
}

