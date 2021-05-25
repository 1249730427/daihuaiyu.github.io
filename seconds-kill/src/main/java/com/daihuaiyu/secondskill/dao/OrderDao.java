package com.daihuaiyu.secondskill.dao;

import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import org.apache.ibatis.annotations.*;


@Mapper
public interface OrderDao {
	
	@Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
	@Results({@Result(id=true, column = "id", property = "id"),@Result(column = "user_id",property = "userId"),@Result(column = "order_id",property = "orderId"),
			  @Result(column = "goods_id",property = "goodsId")
	})
	MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

	@Insert("insert into order_info(user_id, goods_id, delivery_addr_id,goods_name, goods_count, goods_price, order_channel, status, create_date)values("
			+ "#{userId}, #{goodsId},#{deliveryAddrId},#{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
	long insert(OrderInfo orderInfo);
	
	@Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
	int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

	@Select("select * from order_info where id = #{orderId}")
	@Results({ @Result(id=true, column = "id", property = "id"),@Result(column = "user_id",property = "userId"),@Result(column = "goods_id",property = "goodsId"),
			   @Result(column = "delivery_addr_id",property = "deliveryAddrId"),@Result(column = "goods_name",property = "goodsName"),@Result(column = "goods_count",property = "goodsCount"),
			   @Result(column = "goods_price",property = "goodsPrice"),@Result(column = "order_channel",property = "orderChannel"),@Result(column = "status",property = "status"),
			   @Result(column = "create_date",property = "createDate"),@Result(column = "pay_date",property = "payDate")
	})
	OrderInfo getOrderById(@Param("orderId") long orderId);

	
}
