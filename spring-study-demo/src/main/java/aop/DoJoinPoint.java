package aop;

import annotation.Door;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * DoJoinPoint
 *
 * @author daihuaiyu
 * @create: 2021-02-23 17:34
 **/
@Aspect
@Component
@Slf4j
public class DoJoinPoint {

    @Pointcut("@annotation(annotation.Door)")
    public void aopPoint(){

    }
    @Around("aopPoint()")
    public String doRouter(JoinPoint joinPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Signature signature = joinPoint.getSignature();
        Method method=getMethod(signature);
        Door annotation = method.getAnnotation(Door.class);
        String key = getDeclaredField(annotation.keyValue(),joinPoint.getArgs());
        return null;
    }

    private String getDeclaredField(String keyValue, Object[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String fieldValue =null;

        for (Object arg:args){
            if(null == fieldValue || "".equalsIgnoreCase(fieldValue)){
                fieldValue= BeanUtils.getProperty(arg, keyValue);
            }else {
                break;
            }
        }
        return fieldValue;
    }

    private Method getMethod(Signature signature) {
        MethodSignature methodSignature = (MethodSignature) signature;
        methodSignature.getMethod();
        return null;
    }




}

