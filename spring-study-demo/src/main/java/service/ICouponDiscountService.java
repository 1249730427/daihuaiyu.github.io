package service;

import java.math.BigDecimal;

/**
 * ICouponDiscountService接口
 *
 * @author daihuaiyu
 * @create: 2021-02-25 11:18
 **/
public interface ICouponDiscountService<T> {

    /**
     *根据优惠券类型获取优惠后的金额
     *
     * @param CouponInfo  优惠券信息 CouponInfo中type表示优惠券类型，有4种枚举值 1.直减 2.满减 3.折扣 4.n元购 typeContent 优惠券内容 typeExt 扩展字段
     * @param originalPrice  产品原价
     * @return  优惠后的金额
     */
    BigDecimal discountAmount(T CouponInfo, Double originalPrice);
}
