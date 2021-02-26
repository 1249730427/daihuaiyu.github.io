package service;

import lombok.extern.slf4j.Slf4j;

/**
 * POPOrderService
 *
 * @author daihuaiyu
 * @create: 2021-02-26 17:53
 **/
@Slf4j
public class POPOrderService {

    public boolean isFirstOrder(String uId) {
        log.info("POP商家，查询用户的订单是否为首单：{}", uId);
        return true;
    }
}

