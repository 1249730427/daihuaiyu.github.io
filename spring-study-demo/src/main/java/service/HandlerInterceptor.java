package service;


/**
 * HandlerInterceptor
 *
 * @author daihuaiyu
 * @create: 2021-02-25 15:34
 **/
public interface HandlerInterceptor {

     boolean preHandle(String request, String response, Object handler) throws Exception;
}
