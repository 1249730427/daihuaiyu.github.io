package service.impl;

import com.alibaba.fastjson.JSON;
import domain.DeliverReq;
import lombok.extern.slf4j.Slf4j;
import service.GoodsService;
import service.ICommodity;

import java.util.Map;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/27 11:35
 */
@Slf4j
public class GoodsCommodityService implements ICommodity {

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) {
        GoodsService goodsService = new GoodsService();
        DeliverReq deliverReq = new DeliverReq();
        deliverReq.setUserName(queryUserName(uId));
        deliverReq.setUserPhone(queryUserPhoneNumber(uId));
        deliverReq.setSku(commodityId);
        deliverReq.setOrderId(bizId);
        deliverReq.setConsigneeUserName(extMap.get("consigneeUserName"));
        deliverReq.setConsigneeUserPhone(extMap.get("consigneeUserPhone"));
        deliverReq.setConsigneeUserAddress(extMap.get("consigneeUserAddress"));
        Boolean isSuccess = goodsService.deliverGoods(deliverReq);
        if(!isSuccess) throw new RuntimeException("奖品发放失败");
        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠券]：{}", isSuccess);
    }

    private String queryUserPhoneNumber(String uId) {
        return "15256656158";
    }


    private String queryUserName(String uId) {
        return "花花";
    }
}
