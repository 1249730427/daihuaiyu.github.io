package com.daihuaiyu.springframework.core.io;

import cn.hutool.core.lang.Assert;

import javax.print.DocFlavor;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 默认资源加载器实现
 * @Author: hydai
 * @Date: 2021/6/26 08:56
 * @Description:
 */
public class DefaultResourceLoader implements ResourceLoader{
    /**
     * 根据配置文件的配置找到对应的资源加载器
     *
     * @param location
     * @return
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"location must not be null");
        try {
            if(location.contains(ClassPath_Url_Prefix)){
                return new ClassPathResource(location.substring(ClassPath_Url_Prefix.length()));
            }else{
                URL url = new URL(location);
                return new UrlResource(url);
            }
        } catch (MalformedURLException e) {
            return new FileSystemResource(location);
        }
    }
}
