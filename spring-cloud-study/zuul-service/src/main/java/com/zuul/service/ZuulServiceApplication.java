package com.zuul.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author daihuaiyu
 * @ClassName: ZuulServiceApplication
 * @Description: 网关
 * @date 2019/7/12 下午3:53
 */
@SpringBootApplication
//加上网关注解
@EnableZuulProxy
public class ZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class, args);
    }

}
