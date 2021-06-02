package com.daihuaiyu.secondskill.interceptor;

import com.daihuaiyu.secondskill.config.CodeEnum;
import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import com.daihuaiyu.secondskill.service.MiaoshaUserService;
import com.daihuaiyu.secondskill.service.impl.MiaoshaUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 秒杀用户信息拦截器
 *
 * @author daihuaiyu
 * @create: 2021-06-01 17:06
 **/
@Component
public class MiaoshaUserInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(MiaoshaUserInterceptor.class);


    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是SpringWebMVC请求
        if(handler instanceof HandlerMethod){
            String cookieValue = getCookieValue(request, MiaoshaUserServiceImpl.COOKI_NAME_TOKEN);
            MiaoshaUser token = miaoshaUserService.getByToken(response, cookieValue);
            if(token ==null ){
                logger.info(CodeEnum.LOGIN_FRIST.getMessage());
                response.sendRedirect("/login/to_login");
//                response.setContentType("application/json;charset=UTF-8");
//                ServletOutputStream outputStream = response.getOutputStream();
//                outputStream.write(JSON.toJSONString(Result.error(CodeEnum.LOGIN_FRIST)).getBytes("UTF-8"));
//                outputStream.flush();
//                outputStream.close();
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
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

