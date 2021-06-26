package com.daihuaiyu.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源定义接口
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/26 08:01
 * @Description:定义获取资源API
 */
public interface Resource {

    /**
     * 获取IO输入流
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
