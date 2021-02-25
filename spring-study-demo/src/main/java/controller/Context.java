package controller;

import service.ICouponDiscountService;

import java.math.BigDecimal;

/**
 * 策略控制类
 *
 * @author daihuaiyu
 * @create: 2021-02-25 14:14
 **/
public class Context<T> {

    private ICouponDiscountService iCouponDiscountService;

    public Context(ICouponDiscountService iCouponDiscountService) {
        this.iCouponDiscountService = iCouponDiscountService;
    }

    public BigDecimal discountAmount(T CouponInfo, Double originalPrice){

        return iCouponDiscountService.discountAmount(CouponInfo,originalPrice);
    }
}

