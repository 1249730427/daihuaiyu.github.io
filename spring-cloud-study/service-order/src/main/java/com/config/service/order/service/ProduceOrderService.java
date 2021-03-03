package com.config.service.order.service;


import com.config.service.order.model.ProduceOrder;

/**
 * @Description: 订单业务类
 *
 * @author xub
 * @date 2019/7/12 下午12:57
 */
public interface ProduceOrderService {

     /**
       * @Description: 下单接口
       * @author xub
       */
     ProduceOrder save(int userId, int produceId);
}
