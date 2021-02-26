package service.impl;

import service.OrderAdapterService;
import service.OrderService;

/**
 * InsideOrderService
 *
 * @author daihuaiyu
 * @create: 2021-02-26 17:47
 **/
public class InsideOrderService implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    @Override
    public boolean isFrist(String userId) {

        return orderService.queryUserOrderCount(userId) <= 1;
    }
}

