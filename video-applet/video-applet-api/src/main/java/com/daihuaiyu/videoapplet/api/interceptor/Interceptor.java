package com.daihuaiyu.videoapplet.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.api.util.RedisOperatorUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 拦截器用户信息做相关判断
 *
 * @author daihuaiyu
 * @create: 2021-03-31 16:47
 **/
@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperatorUtil redisOperator;

    private final static String USERSESSIONID="user-session-id";
    /**
     * 在请求到达controller前的操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        String userToken = request.getHeader("userToken");
        log.info("用户ID:{} 用户Token:{}",userId,userToken);
        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(userToken)){
            return returnErrorResponse(response, ApiResponse.errorTokenMsg("用户未登陆,请登陆!"));
        }
        boolean hasKey = redisOperator.hasKey(USERSESSIONID + userId);
        if(!hasKey){
            return returnErrorResponse(response, ApiResponse.errorTokenMsg("登陆已过期,请重新登陆!"));
        }
        if(!userToken.equalsIgnoreCase(redisOperator.get(USERSESSIONID + userId))){
            return returnErrorResponse(response, ApiResponse.errorTokenMsg("该用户已登陆!"));
        }
        return true;
    }

    private boolean returnErrorResponse(HttpServletResponse response, ApiResponse errorMsg) throws IOException {
        PrintWriter printWriter =null;
        try {
            response.setHeader("content-type", "text/json; charset=UTF-8");
            printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(errorMsg));
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        return false;
    }

    /**
     * 请求controller后 页面渲染前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 拦截于方法成功返回后，视图渲染前
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

