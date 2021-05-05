package com.study.chapter2.config;

/**
 * 数据源上下文环境
 *
 * @ClassName: DataSourceContextHolder
 * @Author: hydai
 * @Date: 2021/5/5 07:35
 * @Description:
 */
public class DataSourceContextHolder {

    //利用ThreadLocal封装的保存数据源上线的上下文context
    private static final ThreadLocal<String> context  = new ThreadLocal<>();

    public static void  set(String dataSourceType){
        context.set(dataSourceType);
    }
    public static String get(){
        return context.get();
    }
    public static void clear(){
        context.remove();
    }
}
