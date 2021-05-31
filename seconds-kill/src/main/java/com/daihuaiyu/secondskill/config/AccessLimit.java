package com.daihuaiyu.secondskill.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口限流注解
 *
 * @author daihuaiyu
 * @create: 2021-05-31 15:17
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    long timeRange(); //时间范围，单位：秒

    int maxCount() default 5 ; //时间范围内最大可访问多少次，默认需要登录

    boolean needLogin() ; //是否需要登录

}
