package com.study.chapter1.util;

import java.security.MessageDigest;
import java.util.*;

/**
 * MD5工具类
 *
 * @author daihuaiyu
 * @create: 2021-02-07 16:36
 **/
public class MD5Util {

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String encode(String origin) {
        return encode(origin, "UTF-8");
    }
    public static String encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println(getMaxTimesString("12358bbbb戴8aaabbbbba"));
    }
    public static String getMaxTimesString(String sourceString){
        Map<String,Integer> countMap = new HashMap<>();
        char[] charArray = sourceString.toCharArray();
        for(int i=0;i<charArray.length;i++){
            if(sourceString.indexOf(charArray[i])!=-1){
                Integer times = countMap.get(String.valueOf(charArray[i]));
                if(times !=null){
                    times = times+1;
                    countMap.put(String.valueOf(charArray[i]),times);
                }else{
                    countMap.put(String.valueOf(charArray[i]),1);
                }
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(countMap.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue()-o1.getValue());
        return "出现次数最多的字符为："+list.get(0).getKey()+",出现次数为："+list.get(0).getValue();
    }
}

