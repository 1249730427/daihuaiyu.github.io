package com.study.chapter1.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *NotRepeatSubmit 自定义注解，防止重复提交
 *
 *@author daihuaiyu
 *@create: 2021-02-07 14:02
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotRepeatSubmit {

    long value() default 5000;
}
