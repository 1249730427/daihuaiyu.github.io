package com.daihuaiyu.videoapplet.api.config;

import com.daihuaiyu.videoapplet.api.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 *
 * @author :daihuaiyu
 * @Description: 拦截器配置
 * @create 2021/3/31 21:32
 */
@Component
public class WebMvc extends WebMvcConfigurationSupport {

    @Autowired
    public Interceptor interceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这里是对swagger2的静态页面资源进行添加
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                //对应的服务器端存放文件的目录
                .addResourceLocations("file:F:/file/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/video/uploadVideo")
                .addPathPatterns("/video/saveComments");
    }
}
