package com.study.chapter1.util;

/**
 * @Description:
 * @Author: gaoxinfu
 * @Date: 2020-11-11 10:34
 */
public class StringUtils extends org.springframework.util.StringUtils {

    public static final char UNDERLINE = '_';

    public static String subString(String str,int maxLength){
        if (StringUtils.isEmpty(str)){
            return str;
        }
        if (str.length()>maxLength){
            return str.substring(0,maxLength);
        }
        return str;
    }

    //驼峰转下划线
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
            }
            //统一都转小写
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

}
