package com.daihuaiyu.secondskill.redis;

/**
 * 商品key
 *
 * @author daihuaiyu
 * @create: 2021-05-11 14:24
 **/
public class GoodsKey extends BasePrefix{

	private GoodsKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
	public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
}
