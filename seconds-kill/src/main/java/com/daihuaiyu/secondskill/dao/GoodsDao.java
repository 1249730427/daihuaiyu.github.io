package com.daihuaiyu.secondskill.dao;

import com.daihuaiyu.secondskill.domain.MiaoshaGoods;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品服务
 *
 * @author daihuaiyu
 * @create: 2021-05-12 13:52
 **/
@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.miaosha_price ,mg.stock_count, mg.start_date, mg.end_date from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    @Results({
            @Result(id=true, column = "id", property = "id"), @Result(column = "goods_name", property = "goodsName"),
            @Result(column = "goods_title", property = "goodsTitle"),@Result(column = "goods_img", property = "goodsImg"),
            @Result(column = "goods_detail",property = "goodsDetail"),@Result(column = "goods_stock",property = "goodsStock"),@Result(column = "miaosha_price",property = "miaoshaPrice"),
            @Result(column = "stock_count",property = "stockCount"),@Result(column = "goods_price",property = "goodsPrice"),@Result(column = "start_date",property = "startDate"),
            @Result(column = "end_date",property = "endDate")
    })
    List<GoodsVo> getGoodsVoList();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    @Results({
            @Result(id=true, column = "id", property = "id"), @Result(column = "goods_name", property = "goodsName"),
            @Result(column = "goods_title", property = "goodsTitle"),@Result(column = "goods_img", property = "goodsImg"),
            @Result(column = "goods_detail",property = "goodsDetail"),@Result(column = "goods_stock",property = "goodsStock"),@Result(column = "miaosha_price",property = "miaoshaPrice"),
            @Result(column = "stock_count",property = "stockCount"),@Result(column = "goods_price",property = "goodsPrice"),@Result(column = "start_date",property = "startDate"),
            @Result(column = "end_date",property = "endDate")
    })
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId")long goodsId);

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count>0")
    int reduceMiaoshaStock(MiaoshaGoods g);
}
