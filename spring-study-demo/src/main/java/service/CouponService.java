package service;

import domain.CouponResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :daihuaiyu
 * @Description: 优惠券服务
 * @create 2021/2/27 10:55
 */
@Slf4j
public class CouponService {
    public CouponResult sendCoupon(String uId, String awardNumber, String bizId) {
        log.info("调用优惠券服务：uID :"+uId+" 奖品编号"+awardNumber+" 业务ID"+bizId);
        return new CouponResult("0000","111");
    }
}
