package com.daihuaiyu.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.daihuaiyu.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类ClassPath目录下文件加载器
 * @Author: daihuaiyu
 * @Date: 2021/6/26 08:06
 * @Description:用于加载claspath目录下的Spring配置文件
 */
public class ClassPathResource implements Resource{

    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"Path must not null");
        this.path = path;
        this.classLoader = classLoader==null? ClassUtils.getDefaultClassLoader():classLoader;
    }

    /**
     * 获取IO输入流
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream stream = classLoader.getResourceAsStream(path);
        if (stream == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be load because it does not exist");
        }
        return stream;
    }
}
