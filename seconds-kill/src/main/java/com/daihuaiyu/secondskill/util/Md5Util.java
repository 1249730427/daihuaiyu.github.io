package com.daihuaiyu.secondskill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具类
 *使用MD5做二次加密
 * @author daihuaiyu
 * @create: 2021-05-10 14:51
 **/
public class Md5Util {

    private static String salt="1a2b3c4d";

    /**对输入的字符串进行MD5加密*/
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassToFormPass(String inputPassForm){
        String md5InputPass = salt.charAt(0) + salt.charAt(2) + inputPassForm + salt.charAt(5) + salt.charAt(6);
        return md5(md5InputPass);
    }

    /**对输入的字符串进行二次MD5加密*/
    public static String formPassToDBPass(String formPass) {
        String str = salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(6);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDbPass("123456"));
        System.out.println(inputPassToFormPass("123456"));
    }

}

