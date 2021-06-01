package com.daihuaiyu.secondskill.aspect;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.secondskill.config.AccessLimit;
import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.service.MiaoshaService;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.service.impl.MiaoshaUserServiceImpl;
import com.daihuaiyu.secondskill.util.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 注解拦截切面
 *
 * @author daihuaiyu
 * @create: 2021-05-31 15:26
 **/
@Component
@Aspect
@Order(value = 1)
public class AccessLimitAspect  {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Before("@annotation(com.daihuaiyu.secondskill.config.AccessLimit)")
    public Object accessLimit(JoinPoint proceedingJoinPoint) throws Throwable {
       MethodSignature methodSignature= (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //获取注解
        AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
        long timeRange = accessLimit.timeRange();
        int maxCount = accessLimit.maxCount();
        boolean needLogin = accessLimit.needLogin();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        String cookieToken = getCookieValue(request, MiaoshaUserServiceImpl.COOKI_NAME_TOKEN);
        MiaoshaUser miaoshaUser = miaoshaUserService.getByToken(response, cookieToken);
        if(needLogin){
            if(miaoshaUser ==null){
                return Result.error(CodeEnum.SESSION_ERROR);
            }
        }
        HashOperations opsForHash = redisTemplate.opsForHash();
        Integer count = JSON.parseObject((String) opsForHash.get(AccessLimitAspect.class.getSimpleName() + "access", "" + miaoshaUser.getId()),Integer.class);
        if(count ==null){
            opsForHash.put(AccessLimitAspect.class.getSimpleName() + "access","" + miaoshaUser.getId(),JSON.toJSONString(1));
            redisTemplate.expire(AccessLimitAspect.class.getSimpleName() + "access",timeRange,TimeUnit.SECONDS);
            return true;
        }else if(count<maxCount){
            Boolean hasKey = redisTemplate.hasKey(AccessLimitAspect.class.getSimpleName() + "access");
            if(hasKey){
                opsForHash.increment(AccessLimitAspect.class.getSimpleName() + "access", "" + miaoshaUser.getId(),1);
            }else{
                opsForHash.put(AccessLimitAspect.class.getSimpleName() + "access","" + miaoshaUser.getId(),JSON.toJSONString(1));
                redisTemplate.expire(AccessLimitAspect.class.getSimpleName() + "access",timeRange,TimeUnit.SECONDS);
            }
            return true;
        }else{
            render(response, CodeEnum.ACCESS_LIMIT_REACHED);
            return false;
        }

    }

    private void render(HttpServletResponse response, CodeEnum accessLimitReached) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            OutputStream out = response.getOutputStream();
            String str  = JSON.toJSONString(Result.error(accessLimitReached));
            out.write(str.getBytes("UTF-8"));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCookieValue(HttpServletRequest request, String cookiNameToken) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie1 = null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookiNameToken)){
                cookie1 =cookie;
                break;
            }
        }
        return cookie1.getValue();
    }
}

