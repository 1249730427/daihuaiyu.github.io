package service;

import com.alibaba.fastjson.JSON;
import domain.DeliverReq;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/27 11:10
 */
@Slf4j
public class GoodsService {
    public Boolean deliverGoods(DeliverReq deliverReq) {
        log.info("实物商品：deliverReq "+ JSON.toJSONString(deliverReq));
        return Boolean.TRUE;
    }
}
