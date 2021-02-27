package controller;

import com.alibaba.fastjson.JSON;
import domain.AwardReq;
import domain.AwardRes;
import domain.CouponResult;
import domain.DeliverReq;
import lombok.extern.slf4j.Slf4j;
import service.CouponService;
import service.GoodsService;
import service.IQiYiCardService;

/**
 * @author :daihuaiyu
 * @Description: PrizeController
 * @create 2021/2/27 10:37
 */
@Slf4j
public class PrizeController {

    public AwardRes awardToUser(AwardReq awardReq){

        String reqJson = JSON.toJSONString(awardReq);
        log.info("奖品发放开始{}。req:{}", awardReq.getuId(), reqJson);
        // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
        AwardRes awardRes = null;
        try{
        if(awardReq.getAwardType()==1) {  //1优惠券
            CouponService couponService = new CouponService();
            CouponResult couponResult = couponService.sendCoupon(awardReq.getuId(), awardReq.getAwardNumber(), awardReq.getBizId());
            if ("0000".equals(couponResult.getCode())) {
                awardRes = new AwardRes("0000", "发放成功");
            } else {
                awardRes = new AwardRes("0001", couponResult.getInfo());
            }
        }else if (awardReq.getAwardType() == 2) {
                GoodsService goodsService = new GoodsService();
                DeliverReq deliverReq = new DeliverReq();
                deliverReq.setUserName(queryUserName(awardReq.getuId()));
                deliverReq.setUserPhone(queryUserPhoneNumber(awardReq.getuId()));
                deliverReq.setSku(awardReq.getAwardNumber());
                deliverReq.setOrderId(awardReq.getBizId());
                deliverReq.setConsigneeUserName(awardReq.getExtMap().get("consigneeUserName"));
                deliverReq.setConsigneeUserPhone(awardReq.getExtMap().get("consigneeUserPhone"));
                deliverReq.setConsigneeUserAddress(awardReq.getExtMap().get("consigneeUserAddress"));
                Boolean isSuccess = goodsService.deliverGoods(deliverReq);
                if (isSuccess) {
                    awardRes = new AwardRes("0000", "发放成功");
                } else {
                    awardRes = new AwardRes("0001", "发放失败");
                }
            } else if (awardReq.getAwardType() == 3) {
                String bindMobileNumber = queryUserPhoneNumber(awardReq.getuId());
                IQiYiCardService iQiYiCardService = new IQiYiCardService();
                iQiYiCardService.grantToken(bindMobileNumber, awardReq.getAwardNumber());
                awardRes = new AwardRes("0000", "发放成功");
            }
            log.info("奖品发放完成{}。", awardReq.getuId());
        } catch (Exception e) {
            log.error("奖品发放失败{}。req:{}", awardReq.getuId(), reqJson, e);
            awardRes = new AwardRes("0001", e.getMessage());
        }
        return awardRes;
    }

    private String queryUserPhoneNumber(String uId) {
        return "花花";
    }

    private String queryUserName(String uId) {
        return "15256656158";
    }


}
