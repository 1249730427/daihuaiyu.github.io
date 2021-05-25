package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.service.MiaoshaService;
import com.daihuaiyu.secondskill.service.OrderService;
import com.daihuaiyu.secondskill.util.Result;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 立即秒杀业务逻辑
 *
 * @author daihuaiyu
 * @create: 2021-05-21 15:49
 **/
@Controller
@RequestMapping(value = "/miaosha")
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MiaoshaService miaoshaService;

    @PostMapping(value = "/do_miaosha")
    @ResponseBody
    public Result doMiaosha(MiaoshaUser miaoshaUser, Model model, @RequestParam(value = "goodsId") long goodsId) {
        if(miaoshaUser ==null){
            return Result.error(CodeEnum.SESSION_ERROR);
        }
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goodsVo.getStockCount();
        if(stockCount<=0){
            return Result.error(CodeEnum.MIAO_SHA_OVER);
        }
        MiaoshaOrder miaoshaOrder = miaoshaService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if(miaoshaOrder!=null){
            return Result.error(CodeEnum.REPEATE_MIAOSHA);
        }
        //下订单，下秒杀订单，减库存，减秒杀库存
        OrderInfo orderInfo = miaoshaService.miaosha(miaoshaUser, goodsVo);
        return Result.success(orderInfo);
    }
}

