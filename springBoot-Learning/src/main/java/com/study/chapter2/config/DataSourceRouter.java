package com.study.chapter2.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源路由
 *
 * @ClassName: DataSourceRouter
 * @Author: hydai
 * @Date: 2021/5/5 07:31
 * @Description:
 */
public class DataSourceRouter extends AbstractRoutingDataSource {
    /**
     * 最终的determineCurrentLookupKey返回的是从DataSourceContextHolder中拿到的,因此在动态切换数据源的时候注解
     * 应该给DataSourceContextHolder设值
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
