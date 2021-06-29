package com.daihuaiyu.springframework.beans.factory;

/**
 * 销毁方法接口
 * @Author: hydai
 * @Date: 2021/6/30 07:22
 * @Description:
 */
public interface DisposableBean {

    /**
     * 销毁
     * @throws Exception
     */
    void destroy() throws Exception;
}
