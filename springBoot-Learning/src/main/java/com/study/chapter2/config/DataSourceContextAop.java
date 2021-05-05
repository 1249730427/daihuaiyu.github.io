package com.study.chapter2.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AOP切面类，用于拦截注解
 *
 * @ClassName: DataSourceContextAop
 * @Author: hydai
 * @Date: 2021/5/5 08:49
 * @Description:
 */
@Component
@Aspect
@Order(value=1)
@Slf4j
public class DataSourceContextAop {

    @Around("@annotation(DataSourceSwitcher)")
    public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable{
        boolean clear = false;
        try {
            MethodSignature signature= (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            clear = method.getAnnotation(DataSourceSwitcher.class).clear();
            DataSourceEnum dataSourceEnum = method.getAnnotation(DataSourceSwitcher.class).value();
            DataSourceContextHolder.set(dataSourceEnum.getSourceName());
            log.info("数据源切换至：{}", dataSourceEnum.getSourceName());
            return pjp.proceed();
        } finally {
            if(clear){
                DataSourceContextHolder.clear();
            }
        }
    }

}
