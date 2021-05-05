package com.study.chapter2.mapper;

import com.study.chapter2.domain.Order;

import java.util.List;

/**
 * @Interface: OrderMapper
 * @Author: hydai
 * @Date: 2021/5/5 09:14
 * @Description:
 */
public interface OrderMapper {
    List<Order> listOrders(String orderId);

    List<Order> saveOrder(Order order);
}
