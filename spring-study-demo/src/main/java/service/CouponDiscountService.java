package service;

/**
 * java设计模式--策略模式
 *
 * @author daihuaiyu
 * @create: 2021-02-25 11:02
 **/
public class CouponDiscountService {

    /**
     * 根据优惠券的优惠方式计算优惠金额
     * @param type  优惠券类型   1.直减 2.满减 3.折扣券 4.n元购
     * @param typeContent   优惠券内容
     * @param originalPrice  原价
     * @param extendExt    扩展字段
     * @return  优惠后的金额
     */
    public double discountAmount (Integer type,Double typeContent,Double originalPrice,Double extendExt){

        if(type==1){
            return originalPrice-typeContent;
        }
        if(type==2){
            return originalPrice>=typeContent? (originalPrice-typeContent):originalPrice;

        }
        if(type==3){
            return typeContent*originalPrice;
        }
        if(type==4){
            return extendExt;
        }
        return 0;
    }
}

