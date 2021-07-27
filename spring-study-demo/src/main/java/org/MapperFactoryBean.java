package org;

import annotation.Select;
import com.alibaba.fastjson.parser.JSONLexer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * MapperFactoryBean
 *
 * @author daihuaiyu
 * @create: 2021-02-24 13:12
 **/
@Slf4j
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            Select select = method.getAnnotation(Select.class);
//            log.info("SQL：{}", select.value().replace("#{uId}", args[0].toString()));
            return args[0] + ",沉淀、分享、成长，让自己和他人都能有所收获！";
        };
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{mapperInterface},
                invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

