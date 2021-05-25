package com.daihuaiyu.secondskill.service;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.vo.GoodsVo;

/**
 * 订单服务
 *
 * @author daihuaiyu
 * @create: 2021-05-21 16:06
 **/
public interface OrderService {

    /**下单接口*/
    OrderInfo createOrder(MiaoshaUser user, GoodsVo goods);

    /**根据订单ID获取订单信息*/
    OrderInfo getOrderById(long orderId);
}
