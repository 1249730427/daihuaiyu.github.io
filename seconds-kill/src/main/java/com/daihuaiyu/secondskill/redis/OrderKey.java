package com.daihuaiyu.secondskill.redis;

/**
 * 订单key
 *
 * @author daihuaiyu
 * @create: 2021-05-11 14:24
 **/
public class OrderKey extends BasePrefix {

	public OrderKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

}
