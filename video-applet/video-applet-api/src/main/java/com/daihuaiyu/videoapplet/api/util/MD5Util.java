package com.daihuaiyu.videoapplet.api.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加解密工具类
 *
 * @author daihuaiyu
 * @create: 2021-03-30 14:29
 **/
public class MD5Util {

    /**
     * MD5加密方法，返回加密后的字符串
     * @param message
     * @return
     */
    public static String encode(String message) throws NoSuchAlgorithmException {
         MessageDigest md5 = MessageDigest.getInstance("MD5");
        final String base64String = Base64.encodeBase64String(md5.digest(message.getBytes()));//对MD5加密值进行BASE64加密
        return base64String;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(MD5Util.encode("daihuaiyu"));
    }
}

