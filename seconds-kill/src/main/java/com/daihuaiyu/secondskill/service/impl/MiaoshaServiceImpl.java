package com.daihuaiyu.secondskill.service.impl;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.secondskill.dao.OrderDao;
import com.daihuaiyu.secondskill.domain.MiaoshaOrder;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.domain.OrderInfo;
import com.daihuaiyu.secondskill.redis.GoodsKey;
import com.daihuaiyu.secondskill.redis.MiaoshaKey;
import com.daihuaiyu.secondskill.redis.OrderKey;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.service.MiaoshaService;
import com.daihuaiyu.secondskill.service.OrderService;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 秒杀相关业务逻辑
 * @author daihuaiyu
 * @create: 2021-05-21 16:44
 **/
@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Resource
    private OrderDao orderDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    private static char[] ops = new char[]{'+','-','*'};

    /**
     * 根据商品ID查找秒杀订单信息
     *
     * @param goodsId
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        //return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        HashOperations opsForHash = redisTemplate.opsForHash();
        MiaoshaOrder miaoshaOrder = JSON.parseObject((String) opsForHash.get(OrderKey.getMiaoshaOrderByUidGid.getPrefix()+"moug", "" + userId + "_" + goodsId), MiaoshaOrder.class);
        if(miaoshaOrder !=null ){
            return miaoshaOrder;
        }
        miaoshaOrder = orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        return miaoshaOrder;
    }

    /**
     * 下订单，减库存
     *
     * @param user
     * @param goods
     */
    @Override
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        boolean isOver = goodsService.reduceStock(goods);
        if(isOver){
            return orderService.createOrder(user, goods);
        }
        redisTemplate.opsForHash().put(MiaoshaKey.isGoodsOver.getPrefix()+"go",""+goods.getId(),true);

        return null;
    }

    /**
     * 根据用户ID和商品ID查询订单信息
     *
     * @param id
     * @param goodsId
     */
    @Override
    public Long getMiaoshaResult(Long id, long goodsId) {
        MiaoshaOrder miaoshaOrder = getMiaoshaOrderByUserIdGoodsId(id, goodsId);
        if(miaoshaOrder !=null){
            return miaoshaOrder.getOrderId();
        }
        boolean over = false;
        if(redisTemplate.hasKey(MiaoshaKey.isGoodsOver.getPrefix() + "go")){
            over = (boolean) redisTemplate.opsForHash().get(MiaoshaKey.isGoodsOver.getPrefix() + "go", "" + goodsId);
        }
        if(over){
            return -1L;
        }
        return 0L;
    }

    /**
     * 利用BufferImage生成验证码code
     *
     * @param id
     * @param goodsId
     */
    @Override
    public BufferedImage createVerifyCode(Long id, long goodsId) {
        if(id == null || goodsId <=0) {
            return null;
        }
        int width = 80;
        int height = 32;
        //生成图片
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        //设置背景颜色
        graphics.setColor(new Color(0xDCDCDC));
        graphics.fillRect(0, 0, width, height);
        //绘制面板
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, width - 1, height - 1);
        //为生成随机验证码创建一个random对象
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            graphics.drawOval(x, y, 0, 0);
        }
        // 生成随机的验证码
        String verifyCode = generateVerifyCode(rdm);
        graphics.setFont(new Font("Candara", Font.BOLD, 24));
        graphics.drawString(verifyCode, 8, 24);
        graphics.dispose();
        //计算验证码的值并保存到缓存中
        calcAndBgSave(verifyCode,goodsId);
        return bufferedImage;
    }

    /**
     * 校验页面传送回来的验证码code
     *
     * @param goodsId
     * @param verifyCode
     */
    @Override
    public boolean checkVerifyCode(long goodsId, int verifyCode) {
        HashOperations opsForHash = redisTemplate.opsForHash();
        Integer eval = (Integer) opsForHash.get(MiaoshaKey.getMiaoshaVerifyCode.getPrefix()+"vc",""+goodsId);
        return verifyCode==eval;
    }

    /**
     * 生成秒杀请求路径
     *
     * @param id
     * @param goodsId
     */
    @Override
    public String generateMiaoshaPath(Long id, long goodsId) {
        if(id == null || goodsId<0){
            return null;
        }
        String path = UUID.randomUUID().toString().replaceAll("-","");
        HashOperations opsForHash = redisTemplate.opsForHash();
        opsForHash.put(MiaoshaKey.getMiaoshaPath.getPrefix()+"path",id+"_"+goodsId,path);
        redisTemplate.expire(MiaoshaKey.getMiaoshaPath.getPrefix()+"path",300, TimeUnit.SECONDS);
        return path;
    }

    /**
     * 校验秒杀路径
     *
     * @param path
     */
    @Override
    public boolean checkMiaoshaPath(long userId,long goodsId,String path) {
        HashOperations opsForHash = redisTemplate.opsForHash();
        String miaoshaPath = (String) opsForHash.get(MiaoshaKey.getMiaoshaPath.getPrefix()+"path",userId+"_"+goodsId);
        return miaoshaPath.equals(path);
    }

    private void calcAndBgSave(String verifyCode,long goodsId) {
        try {
            //获取JavaScriptEngineManage
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine javaScript = scriptEngineManager.getEngineByName("JavaScript");
            //得出计算值
            Integer eval = (Integer) javaScript.eval(verifyCode);
            HashOperations opsForHash = redisTemplate.opsForHash();
            //放入缓存中
            opsForHash.put(MiaoshaKey.getMiaoshaVerifyCode.getPrefix()+"vc",""+goodsId,eval);
            redisTemplate.expire(MiaoshaKey.getMiaoshaVerifyCode.getPrefix()+"vc",60,TimeUnit.SECONDS);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        return ""+num1+op1+num2+op2+num3;
    }


}

