package service.impl;

import domain.AwardReq;
import service.ICommodity;

/**
 * @author :daihuaiyu
 * @Description: 兑换工厂
 * @create 2021/2/27 11:49
 */
public class StoreFactory {

    public ICommodity store(String type){
        if("1".equals(type)){
            return new CouponCommodityService();
        }else if("2".equals(type)){
            return new GoodsCommodityService();
        }else{
            return new CardCommodityService();
        }
    }

}
