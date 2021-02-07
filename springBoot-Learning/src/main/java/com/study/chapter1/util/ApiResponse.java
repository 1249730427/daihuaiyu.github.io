package com.study.chapter1.util;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 方法响应
 *
 * @author daihuaiyu
 * @create: 2021-02-07 13:57
 **/
@Data
public class ApiResponse <T> {

    private ApiResult apiResult;

    private T data;

    private String sign;

    public static <T> ApiResponse success(T data){

        return response(ApiCodeEnum.SUCCESS.getCode(),ApiCodeEnum.SUCCESS.getMsg(),data);
    }

    public static <T> ApiResponse error(String code,String msg){

        return response(code,msg,null);
    }

    public static <T> ApiResponse response(String code,String msg,T data){

        ApiResponse apiResponse = new ApiResponse<>();

        ApiResult apiResult = new ApiResult(code,msg);

        apiResponse.setApiResult(apiResult);

        apiResponse.setData(apiResult);

        String sign = signData(data);

        apiResponse.setSign(sign);

        return apiResponse;
    }

    private static <T> String signData(T data) {

        String signSecurity="";

        try {

            Map<String, String> signDataMap = getData(data);

            String signString = ApiUtil.concatSignString(signDataMap);

            signSecurity = MD5Util.encode(signString);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return signSecurity;
    }



    public static <T> Map<String,String> getData(T data) throws IllegalAccessException, IllegalArgumentException{

        if(data ==null) { return null; }

        Map<String,String> signMap =new HashMap<>();

        Field[] declaredFields = data.getClass().getDeclaredFields();  //获取对象中的列

        for(int i=0;i<declaredFields.length;i++){

            Field declaredField = declaredFields[i];

            String name = declaredField.getName();

            Object o = declaredField.get(data);

            if(declaredField.get(data) !=null){

                signMap.put(name,o.toString());
            }
        }
        return signMap;
    }
}


