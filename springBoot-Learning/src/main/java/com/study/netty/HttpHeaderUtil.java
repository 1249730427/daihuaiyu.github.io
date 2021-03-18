package com.study.netty;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/3/18 21:08
 */
public class HttpHeaderUtil {

    public static boolean is100ContinueExcepted(HttpRequest httpRequest) {
        HttpHeaders headers = httpRequest.headers();
        return false;
    };
}
