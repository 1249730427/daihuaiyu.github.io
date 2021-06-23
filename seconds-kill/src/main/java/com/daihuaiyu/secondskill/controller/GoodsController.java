package com.daihuaiyu.secondskill.controller;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.mybatis.Pager;
import com.daihuaiyu.secondskill.redis.GoodsKey;
import com.daihuaiyu.secondskill.service.GoodsService;
import com.daihuaiyu.secondskill.util.Result;
import com.daihuaiyu.secondskill.vo.GoodsDetailVo;
import com.daihuaiyu.secondskill.vo.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 商品Controller
 *
 * @author daihuaiyu
 * @create: 2021-05-12 10:06
 **/
@Controller
@RequestMapping("/goods")
@Api("商品信息的API")
public class GoodsController {

    private Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/to_list",produces = "text/html;charset=UTF-8",method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "商品列表")
    public String to_list(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser miaoshaUser, @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,@RequestParam(value = "pageSize",required = false ,defaultValue = "20")Integer pageSize,@RequestParam(value = "sortBy",required = false,defaultValue = "id") String sortBy,@RequestParam(value = "rank",required = false,defaultValue = "DESC")String rank){
        //从缓存中获取页面文件，有的话直接返回
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String html = opsForValue.get(GoodsKey.getGoodsList.getPrefix()+"gl");
        if(html !=null){
//            log.info("从页面缓存中获取页面列表信息：{}",html);
            return html;
        }
        //DO 从数据库中查询出数据用于列表展示，由于是demo，不做分页查询，实际生产过程中做分页查询
        Pager pager = new Pager();
        pager.setPageNo(currentPage);
        pager.setPageSize(currentPage);
        pager.setRank(rank);
        pager.setSortBy(sortBy);
        List<GoodsVo> goodsList = goodsService.getGoodsVo(pager);
        model.addAttribute("user",miaoshaUser);
        model.addAttribute("goodsList",goodsList);
        //构建静态化模板
        SpringWebContext ctx =new SpringWebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);
        html =thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        if(html !=null){
            //放入缓存
            opsForValue.set(GoodsKey.getGoodsList.getPrefix()+ "gl",html,5*60, TimeUnit.SECONDS);
        }
        return html;
    }

    @RequestMapping(value = "/to_detail/{goodsId}",produces = "text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "商品详情")
    public String to_detail(HttpServletRequest request, HttpServletResponse response,@PathVariable Long goodsId, Model model, MiaoshaUser miaoshaUser){
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String html = opsForValue.get(GoodsKey.getGoodsDetail.getPrefix()+"gd"+goodsId);
        if(html !=null){
            log.info("从页面缓存中获取商品详情信息：{}",html);
            return html;
        }
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date now = new Date();
        int miaoshaStatus ;
        long remainSeconds = 0;
        if(startDate.getTime()<now.getTime() && endDate.getTime()>now.getTime()){
            miaoshaStatus =1; //秒杀进行中
        }else if(startDate.getTime()>now.getTime()){
            miaoshaStatus =0; //秒杀未开始，倒计时
            remainSeconds = (startDate.getTime()-now.getTime())/1000;//倒计时秒
        }else{
            miaoshaStatus =2; //秒杀结束
            remainSeconds = -1;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("user",miaoshaUser);
        model.addAttribute("goods",goodsVo);
        //构建静态化模板
        SpringWebContext ctx =new SpringWebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);
        html =thymeleafViewResolver.getTemplateEngine().process("goods_detail",ctx);
        if(html !=null){
            //放入缓存
            opsForValue.set(GoodsKey.getGoodsDetail.getPrefix()  +"gd"+goodsId, html,2*60,TimeUnit.SECONDS);
        }
        return html;
    }

    @RequestMapping(value = "/detail/{goodsId}",method = RequestMethod.GET)
    @ResponseBody
    public Result<GoodsDetailVo> detail(@PathVariable Long goodsId,MiaoshaUser miaoshaUser){
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date now = new Date();
        int miaoshaStatus ;
        long remainSeconds = 0;
        if(startDate.getTime()<now.getTime() && endDate.getTime()>now.getTime()){
            miaoshaStatus =1; //秒杀进行中
        }else if(startDate.getTime()>now.getTime()){
            miaoshaStatus =0; //秒杀未开始，倒计时
            remainSeconds = (startDate.getTime()-now.getTime())/1000;//倒计时秒
        }else{
            miaoshaStatus =2; //秒杀结束
            remainSeconds = -1;
        }
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setGoodsVo(goodsVo);
        goodsDetailVo.setUser(miaoshaUser);
        goodsDetailVo.setRemainSeconds(remainSeconds);
        goodsDetailVo.setMiaoshaStatus(miaoshaStatus);
        return Result.success(goodsDetailVo);
    }
}


