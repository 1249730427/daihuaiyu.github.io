package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品Controller
 *
 * @author daihuaiyu
 * @create: 2021-05-12 10:06
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/to_list")
    public String to_list(Model model, MiaoshaUser miaoshaUser){
        //DO 从数据库中查询出数据用于列表展示，由于是demo，不做分页查询，实际生产过程中做分页查询
        List<GoodsVo> goodsList = goodsService.getGoodsVo();
        model.addAttribute("user",miaoshaUser);
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }
}


