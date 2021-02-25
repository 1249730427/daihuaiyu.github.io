package controller;

import service.HandlerInterceptor;

/**
 * SSO登录功能模拟
 *
 * @author daihuaiyu
 * @create: 2021-02-25 15:33
 **/
public class SsoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(String request, String response, Object handler) throws Exception {

        String ticket = request.substring(1,7);

        return ticket.equals("success");
    }
}

