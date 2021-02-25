package controller;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟登录功能
 *
 * @author daihuaiyu
 * @create: 2021-02-25 15:41
 **/
public class LoginSsoInterceptor extends SsoInterceptor {

    private static ConcurrentHashMap<String,String> authMap = new ConcurrentHashMap<>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
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

