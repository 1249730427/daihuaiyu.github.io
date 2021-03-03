package com.service.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

public class SignUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(SignUtils.class);

    private static final String secretKeyOfWxh = "e10adc3949ba59abbe56e057f20f883f";
    private static final String appidOfWxh = "xxx";

    public static void main(String[] args)
    {
        //参数签名算法测试例子
        HashMap<String, String> signMap = new HashMap<String, String>();
        signMap.put("devid","BC5549D899ED");
        signMap.put("userId","1");
        signMap.put("type","worker");
        signMap.put("name","中文测试");
        System.out.println("得到签名sign1:"+getSign(signMap,secretKeyOfWxh));
    }


    /**
     * 唯修汇外部接口签名验证
     * @param request
     * @return
     */
    public static Boolean checkSign(HttpServletRequest request) throws Exception {
        Boolean flag= false;
        String appid = request.getParameter("appid");//appid
        if(!appid.equals(appidOfWxh)){
            throw  RestCommService.buildBadRequest("appid错误");
        }
        String sign = request.getParameter("sign");//签名
        String timestamp = request.getParameter("timestamp");//时间戳
        //check时间戳的值是否在当前时间戳前后一小时以内
        String currTimestamp = String.valueOf(new Date().getTime() / 1000); // 当前时间的时间戳
        int currTimestampNum = Integer.parseInt(currTimestamp);
        int verifyTimestampNum = Integer.parseInt(timestamp); // 时间戳的数值
        // 在一小时范围之外，访问已过期
        if (Math.abs(verifyTimestampNum - currTimestampNum) > 600) {
            throw  RestCommService.buildBadRequest("sign已经过期");
        }
        //检查sigin是否过期
        Enumeration<?> pNames =  request.getParameterNames();
        Map<String, String> params = new HashMap<String, String>();
        while (pNames.hasMoreElements()) {
            String pName = (String) pNames.nextElement();
            if("sign".equals(pName)) continue;
            String pValue = (String)request.getParameter(pName);
            params.put(pName, pValue);
        }
        if(sign.equals(getSign(params, secretKeyOfWxh))){
            flag = true;
        }
        return flag;
    }


    public static String utf8Encoding(String value, String sourceCharsetName) {
        try {
            return new String(value.getBytes(sourceCharsetName), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }



    private static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }


    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
            //sign.append(hex.toLowerCase());
        }
        return sign.toString();
    }


    /**
     * 得到签名
     * @param params 参数集合不含secretkey
     * @param secret 验证接口的secretkey
     * @return
     */
    public static String getSign(Map<String, String> params, String secret)
    {
        String sign="";
        StringBuilder sb = new StringBuilder();
        //step1：先对请求参数排序
        Set<String> keyset=params.keySet();
        TreeSet<String> sortSet=new TreeSet<String>();
        sortSet.addAll(keyset);
        Iterator<String> it=sortSet.iterator();
        //step2：把参数的key value链接起来 secretkey放在最后面，得到要加密的字符串
        while(it.hasNext())
        {
            String key=it.next();
            String value=params.get(key);
            sb.append(key).append(value);
        }
        sb.append(secret);
        byte[] md5Digest;
        try {
            //得到Md5加密得到sign
            md5Digest = getMD5Digest(sb.toString());
            sign = byte2hex(md5Digest);
        } catch (IOException e) {
            LOGGER.error("生成签名错误",e);
        }
        return sign;
    }

}
