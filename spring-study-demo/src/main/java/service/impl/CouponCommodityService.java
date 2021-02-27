package service.impl;

import com.alibaba.fastjson.JSON;
import domain.CouponResult;
import lombok.extern.slf4j.Slf4j;
import service.CouponService;
import service.ICommodity;

import java.util.Map;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/27 11:35
 */
@Slf4j
public class CouponCommodityService implements ICommodity {


    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) {
        CouponService couponService = new CouponService();
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bizId);
        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        if(!couponResult.getCode().equals("0000")){
           throw new RuntimeException(couponResult.getInfo());
        }
    }
}
