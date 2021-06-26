package com.daihuaiyu.springframework.core.io;

/**
 * 资源加载器加载类
 * @Author: daihuaiyu
 * @Date: 2021/6/26 08:49
 * @Description:包装资源加载器
 */
public interface ResourceLoader {

    String ClassPath_Url_Prefix ="classpath:";

    /**
     * 根据配置文件的配置找到对应的资源加载器
     * @param location
     * @return
     */
    Resource getResource(String location);
}
