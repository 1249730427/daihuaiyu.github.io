package service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import service.ICommodity;
import service.IQiYiCardService;

import java.util.Map;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/27 11:36
 */
@Slf4j
public class CardCommodityService implements ICommodity {

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) {
        String bindMobileNumber = queryUserPhoneNumber(uId);
        IQiYiCardService iQiYiCardService = new IQiYiCardService();
        iQiYiCardService.grantToken(bindMobileNumber, commodityId);
        log.info("请求参数[爱奇艺兑换卡] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[爱奇艺兑换卡]：success");
    }

    private String queryUserPhoneNumber(String uId) {
        return "花花";
    }
}
