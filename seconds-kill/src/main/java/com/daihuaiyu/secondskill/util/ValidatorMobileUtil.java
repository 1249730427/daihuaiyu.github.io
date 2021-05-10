package com.daihuaiyu.secondskill.util;

import java.util.regex.Pattern;

/**
 * 校验手机号工具类
 *
 * @author daihuaiyu
 * @create: 2021-05-10 17:25
 **/
public class ValidatorMobileUtil {

    /**校验手机号正则表达式*/
    private static final String MOBILE_PATTERN="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}";

    public static boolean require(String value){
        Pattern pattern = Pattern.compile(MOBILE_PATTERN);
        if(pattern.matcher(value).find()){
            return true;
        }
        return false;
    }

}

