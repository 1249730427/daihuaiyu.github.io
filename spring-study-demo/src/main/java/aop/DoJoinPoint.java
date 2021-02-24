package aop;

import annotation.Door;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.StartService;

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

    @Autowired
    private StartService startService;

    @Pointcut("@annotation(annotation.Door)")
    public void aopPoint(){

    }
    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method=getMethod(joinPoint);
        Door annotation = method.getAnnotation(Door.class);
        String key = getDeclaredField(annotation.keyValue(),joinPoint.getArgs());
        log.info("handleMethod="+method+"key="+key);
        if(null != key && "".equalsIgnoreCase(key)){
            return joinPoint.proceed();
        }
        String[] splitUserIds = startService.splitUserIds(",");
        for(int i=0;i<splitUserIds.length;i++){
            if(splitUserIds[i].equals(key)){
                return joinPoint.proceed();
            }
        }
        return returnObject(annotation,method);
    }

    private Object returnObject(Door door,Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String returnJson = door.returnJson();
        if("".equals(returnJson)){
            return  returnType.newInstance();
        }
        return JSON.parseObject(returnJson,returnType);
    }

    /**
     * 获取自定义注解的字段值
     * @param keyValue
     * @param args
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
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

    /**
     * 获取执行的方法
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        return method;
    }




}

