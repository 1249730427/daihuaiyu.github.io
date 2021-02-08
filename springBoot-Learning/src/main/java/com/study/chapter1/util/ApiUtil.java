package com.study.chapter1.util;

import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * 工具类
 *
 * @author daihuaiyu
 * @create: 2021-02-07 15:32
 **/
public class ApiUtil {

    /**
     * 根据map拼接字符串
     * @param parameterMap
     * @return
     */

    public static String concatSignString(Map<String,String>parameterMap){

        Set<String> keySet = parameterMap.keySet();

        Object[] objects = keySet.toArray();

        Arrays.sort(objects);

        StringBuffer sb = new StringBuffer();

        for(int i=0;i<objects.length;i++){

            if("sign".equalsIgnoreCase(objects[i].toString())){

                continue;
            }

            if(parameterMap.get(objects[i].toString()).trim().length()>0){

                sb.append(objects[i].toString()).append("=").append(parameterMap.get(objects[i].toString())).append("&");

            }


        }
        return sb.toString();
    }

    /**
     * 根据HttpRequest拼接字符串
     * @param httpServletRequest
     * @return
     */
    public static String concatSignString(HttpServletRequest httpServletRequest){

        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();

        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        StringBuffer sb = new StringBuffer();

        while(parameterNames.hasMoreElements()){

            if(parameterMap.get(parameterNames.nextElement())[0].equalsIgnoreCase("sign")){

                continue;
            }

            if(parameterMap.get(parameterNames.nextElement())[0].trim().length()>0){

                sb.append(parameterMap.get(parameterNames.nextElement())[0]).append("=").append(parameterMap.get(parameterNames.nextElement())[0]).append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 获取注解方法
     * @param handler
     * @return
     */
    public static NotRepeatSubmit getNotRepeatSubmit(Object handler){

        if(handler !=null){
            if(handler instanceof HandlerMethod){
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                NotRepeatSubmit annotation = method.getAnnotation(NotRepeatSubmit.class);
                return annotation;
            }
        }
        return null;
    }
}

