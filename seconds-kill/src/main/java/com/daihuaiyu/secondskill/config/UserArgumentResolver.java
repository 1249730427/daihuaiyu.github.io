package com.daihuaiyu.secondskill.config;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.redis.MiaoshaUserKey;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.service.impl.MiaoshaUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一处理用户信息，定义拦截器
 *
 * @author daihuaiyu
 * @create: 2021-05-12 10:16
 **/
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> type = methodParameter.getParameterType();
        return type == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //1.获取HttpServletRequest和HttpServletResponse
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        //2.获取token
        String paramToken = request.getParameter(MiaoshaUserServiceImpl.COOKI_NAME_TOKEN);//1.获取参数信息中的token信息
        String cookieToken = getCookieValue(request,MiaoshaUserServiceImpl.COOKI_NAME_TOKEN);//2.获取cookie中的token信息
        String token =paramToken==null ?cookieToken:paramToken;
        //返回缓存中的对象信息
        MiaoshaUser miaoshaUser = miaoshaUserService.getByToken(response, token);
        return miaoshaUser;
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

