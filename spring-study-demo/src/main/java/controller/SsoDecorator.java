package controller;

import service.HandlerInterceptor;

/**
 * SsoDecorator
 *
 * @author daihuaiyu
 * @create: 2021-02-25 16:15
 **/
public class SsoDecorator implements HandlerInterceptor {

    private HandlerInterceptor handlerInterceptor;

    public SsoDecorator() {
    }

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) throws Exception {
        return handlerInterceptor.preHandle(request,response,handler);
    }
}

