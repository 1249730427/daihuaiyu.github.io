package service;

import lombok.extern.slf4j.Slf4j;

/**
 * OrderService
 *
 * @author daihuaiyu
 * @create: 2021-02-26 17:50
 **/
@Slf4j
public class OrderService {

    public Long queryUserOrderCount(String userId){
        log.info("自营商家，查询用户的订单是否为首单：{}", userId);
        return 10L;
    }
}

