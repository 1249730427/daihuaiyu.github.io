package com.study.chapter2.service;

import com.study.chapter2.config.DataSourceEnum;
import com.study.chapter2.config.DataSourceSwitcher;
import com.study.chapter2.domain.Order;
import com.study.chapter2.mapper.OrderMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试读写分离Service
 *
 * @ClassName: OrderService
 * @Author: hydai
 * @Date: 2021/5/5 09:12
 * @Description:
 */
public class OrderService {
    @Resource
    private OrderMapper orderMapper;


    /**
     * 读操作
     *
     * @param orderId
     * @return
     */
    @DataSourceSwitcher(DataSourceEnum.SLAVER)
    public List<Order> getOrder(String orderId) {
        return orderMapper.listOrders(orderId);

    }

    /**
     * 写操作
     *
     * @param orderId
     * @return
     */
    @DataSourceSwitcher(DataSourceEnum.MASTER)
    public List<Order> insertOrder(Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        return orderMapper.saveOrder(order);
    }
}
