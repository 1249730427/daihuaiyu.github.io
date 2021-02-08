package com.study.chapter1.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 *
 * @author daihuaiyu
 * @create: 2021-02-08 09:59
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private static final String [] patterns= {"/api/token/api_token"};

    @Autowired
    private TokenInterceptor tokenInterceptor;

    //重写父类WebMvcConfigurationSupport中的addInterceptors
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(tokenInterceptor)
        .addPathPatterns("/api/**")
        .excludePathPatterns(patterns);
    }
}

