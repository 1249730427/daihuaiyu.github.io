package controller;

import service.HandlerInterceptor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * LoginSsoIntercaptorDecorator
 *
 * @author daihuaiyu
 * @create: 2021-02-25 16:21
 **/
public class LoginSsoIntercaptorDecorator extends SsoDecorator {

    private static ConcurrentHashMap<String,String> authMap = new ConcurrentHashMap<>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoIntercaptorDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) throws Exception {
        // 模拟获取cookie
        String ticket = request.substring(1, 8);
        // 模拟校验
        boolean success = ticket.equals("success");

        if (!success) return false;

        String userId = request.substring(8);
        String method = authMap.get(userId);

        // 模拟方法校验
        return "queryUserInfo".equals(method);
    }
}

