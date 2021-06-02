package com.daihuaiyu.secondskill.config;

import com.daihuaiyu.secondskill.interceptor.MiaoshaUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 用户信息注入到Spring中
 *
 * @author daihuaiyu
 * @create: 2021-05-12 11:20
 **/
@Configuration
public class WebConfig  extends WebMvcConfigurationSupport {

    @Autowired
    MiaoshaUserInterceptor miaoshaUserInterceptor;

    @Autowired
    UserArgumentResolver userArgumentResolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(miaoshaUserInterceptor)
        .addPathPatterns("/goods/*").addPathPatterns("/miaosha/*")
         .addPathPatterns("/order/*")
        .excludePathPatterns("/login/*");
    }
}

