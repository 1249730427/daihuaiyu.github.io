package service.impl;

import service.OrderAdapterService;
import service.POPOrderService;

/**
 * POPOrderAdapterServiceImpl
 *
 * @author daihuaiyu
 * @create: 2021-02-26 17:55
 **/
public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    POPOrderService popOrderService = new POPOrderService();
    @Override
    public boolean isFrist(String userId) {

        return popOrderService.isFirstOrder(userId);
    }
}

