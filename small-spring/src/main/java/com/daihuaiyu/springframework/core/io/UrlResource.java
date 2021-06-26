package com.daihuaiyu.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 加载服务器上的配置文件
 * @Author: hydai
 * @Date: 2021/6/26 08:36
 * @Description:用于加载位于服务器上的Spring配置文件
 */
public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"url must not null");
        this.url = url;
    }
    /**
     * 获取IO输入流
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        try {
            return inputStream;
        } catch (Exception e) {
            if(urlConnection instanceof HttpURLConnection){
                ((HttpURLConnection)urlConnection).disconnect();
            }
            throw e;
        }
    }
}
