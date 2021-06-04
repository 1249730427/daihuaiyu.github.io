package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.service.OrderService;
import com.daihuaiyu.secondskill.util.Result;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import com.daihuaiyu.secondskill.vo.OrderDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 订单业务逻辑
 *
 * @author daihuaiyu
 * @create: 2021-05-24 17:48
 **/
@Controller
@RequestMapping("/order")
@Api("订单相关API")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value ="/detail" ,method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "订单详情")
    public Result<OrderDetailVo> orderDetail(MiaoshaUser miaoshaUser, @RequestParam("orderId") long orderId){
        if(miaoshaUser == null) {
            return Result.error(CodeEnum.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeEnum.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }

}

