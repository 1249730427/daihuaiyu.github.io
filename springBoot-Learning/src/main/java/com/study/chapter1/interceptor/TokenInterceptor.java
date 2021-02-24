package com.study.chapter1.interceptor;

import com.study.chapter1.entity.TokenInfo;
import com.study.chapter1.util.ApiUtil;
import com.study.chapter1.util.NotRepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Token拦截器
 * 1.获取用户请求头中的参数信息token（令牌）、sign（签名）,nonce（随机数）、timestamp（时间戳）
 * 2.判断参数是否为空，为空则返回false
 * 3.业务处理：判断请求是否超时，根据服务器当时时间—timestamp得出时间差，时间差>注解中时间则返回false，请求超时，请重新请求
 *   判断token是否过期，根据token的值去缓存中取token信息，获取不到则返回false token已过期
 *   判断是否重复提交，从缓存中获取key为sign的信息，获取不到则返回false
 *   把sign作为key,设置到缓存中去
 *
 * @author daihuaiyu
 * @create: 2021-02-08 10:06
 **/
@Configuration
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token"); //从request头中取得token信息

        String sign = request.getHeader("sign");  //从request头中获取签名数据

        String nonce = request.getHeader("nonce"); //从request头中获取随机数

        String timestamp = request.getHeader("timestamp"); //从request中获取时间戳

        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(sign) ||StringUtils.isEmpty(nonce) || StringUtils.isEmpty(timestamp)){
            return false;
        }
        log.info("参数token="+token+"&sign="+sign+"&nonce="+nonce+"&timestamp="+timestamp);

        NotRepeatSubmit notRepeatSubmit = ApiUtil.getNotRepeatSubmit(handler);

        long expireTime =notRepeatSubmit ==null? 5*60*1000:notRepeatSubmit.value();

        long timeInterval = System.currentTimeMillis() - Long.parseLong(timestamp);

        if(timeInterval>expireTime){

            log.error("请求超时，请重新请求");
            return false;
        }
        //验证token存不存在
        ValueOperations valueOperations = redisTemplate.opsForValue();
        TokenInfo o = (TokenInfo) valueOperations.get(token);
        if(o==null){

            log.error("token信息已过期");
            return false;
        }


        //验证签名，签名为：MD5(nonce+token+timestamp)
        String encode = ApiUtil.concatSignString(request)+o.getAppInfo().getSecurityKey()+nonce + token + timestamp;
        if(!sign.equalsIgnoreCase(encode)){

           log.error("验签不通过,原签名："+sign+" 计算得出签名："+encode);
           return false;
        }
        //验证是否重复提交
        if(notRepeatSubmit!=null){
            ValueOperations operations = redisTemplate.opsForValue();
            if(redisTemplate.hasKey(sign)){

                log.error("请勿重复提交");
                return false;
            }
            operations.set(sign,0,expireTime, TimeUnit.MILLISECONDS);
        }
        return true;
    }
}

