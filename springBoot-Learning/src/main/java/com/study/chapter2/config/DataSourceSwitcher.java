package com.study.chapter2.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSourceSwitcher {

    /**默认数据源，默认主库*/
    DataSourceEnum value() default DataSourceEnum.MASTER;

    /**是否清理，默认清理*/
    boolean clear() default true;
}
