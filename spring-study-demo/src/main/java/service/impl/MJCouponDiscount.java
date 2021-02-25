package service.impl;

import service.ICouponDiscountService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 满减实现类
 *
 * @author daihuaiyu
 * @create: 2021-02-25 11:27
 **/
public class MJCouponDiscount implements ICouponDiscountService<Map<String,String>> {

    /**
     * 满减计算
     * 1. 判断满足x元后-n元，否则不减
     * 2. 最低支付金额1元
     */
    @Override
    public BigDecimal discountAmount(Map<String,String> CouponInfo, Double originalPrice) {
        String x= CouponInfo.get("x");
        String n= CouponInfo.get("n");
        if(originalPrice<new Double(x)){
            return  new BigDecimal(originalPrice-new Double(n)<1?1:originalPrice-new Double(n));
        }
        return new BigDecimal(0);
    }
}

