package com.daihuaiyu.secondskill.service;

import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.vo.GoodsVo;

import java.awt.image.BufferedImage;

/**
 * 秒杀接口服务
 *
 * @author daihuaiyu
 * @create: 2021-05-21 16:41
 **/
public interface MiaoshaService {

    /**根据用户ID和商品ID查找秒杀订单信息*/
    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId,long goodsId);

    /**下订单，减库存*/
    OrderInfo miaosha(MiaoshaUser user, GoodsVo goods);

    /**根据用户ID和商品ID查询订单信息*/
    Long getMiaoshaResult(Long id, long goodsId);

    /**利用BufferImage生成验证码code*/
    BufferedImage createVerifyCode(Long id, long goodsId);

    /**校验页面传送回来的验证码code*/
    boolean checkVerifyCode(long goodsId, int verifyCode);

    /**生成秒杀请求路径*/
    String generateMiaoshaPath(Long id, long goodsId);

    /**校验秒杀路径*/
    boolean checkMiaoshaPath(long userId,long goodsId,String path);
}
