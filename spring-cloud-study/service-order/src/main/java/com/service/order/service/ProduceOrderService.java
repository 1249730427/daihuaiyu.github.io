package com.service.order.service;


import com.service.order.model.ProduceOrder;

/**
 * @Description: 订单业务类
 *
 * @author daihuaiyu
 * @date 2019/7/12 下午12:57
 */
public interface ProduceOrderService {

     /**
       * @Description: 下单接口
       * @author daihuaiyu
       */
     ProduceOrder save(int userId, int produceId);
}
