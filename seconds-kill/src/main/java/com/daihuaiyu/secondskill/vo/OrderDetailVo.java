package com.daihuaiyu.secondskill.vo;


import com.daihuaiyu.secondskill.domain.OrderInfo;

/**
 * 订单详情Vo
 *
 * @author daihuaiyu
 * @create: 2021-05-24 17:10
 */
public class OrderDetailVo {
	private GoodsVo goods;
	private OrderInfo order;
	public GoodsVo getGoods() {
		return goods;
	}
	public void setGoods(GoodsVo goods) {
		this.goods = goods;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
}
