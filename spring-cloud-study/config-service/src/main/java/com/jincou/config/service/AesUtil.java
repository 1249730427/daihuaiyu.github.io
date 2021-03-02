package com.jincou.config.service;

import org.apache.commons.lang.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AesUtil {
    /**
     * 密钥，需要base64解码
     */
    private static final String KEY = "rjSkZDn/QvdAStABR0Uk0g==";

    /**
     * 加解密方式
     */
    private static final String MODE = "AES/CBC/PKCS5Padding";

    /**
     * 初始变量IV
     */
    private static final String IV = "Qzu4SC/RW32+YXVnvB5Yzg==";

    /**
     * 解密视频路径
     * @param data 加密路径
     * @return 解密路径
     */
    public static String decrypt(String data){
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        String content = "";
        byte[] enCodeFormat = Base64.getDecoder().decode(KEY);
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        // 创建密码器
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(MODE);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(Base64.getDecoder().decode(IV)));
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(data));
            content = new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static String encrypt(String data){
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        String content = "";
        byte[] enCodeFormat = Base64.getDecoder().decode(KEY);
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        // 创建密码器
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(MODE);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(Base64.getDecoder().decode(IV)));
            byte[] result = cipher.doFinal(data.getBytes());
            content = new String(Base64.getEncoder().encode(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static void main(String[] args) {
        String data = "1VALbISC+RXSaKAHmk9/3bRhCtH72dYFj0QEz1zDHKAn8VYm+jsEN80K+CTVIftKCzAvkQ7OWiv+QmmbEPt7wNBcSKLURejOKKLhckxskLX1XpOIJ35l4Ty6L2VffrDr";
        String decryptData = decrypt(data);
        System.out.println(decryptData);

//        String data = "/564/1b/7KywtBN4Kp4jUvZcpuCT/1b7KywtBN4Kp4jUvZcpuCT.mp4";
//        String encryptData = encrypt(data);
//        System.out.println(encryptData);
//
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        numbers.parallelStream()
//            .forEach(System.out::println);
//        long time = System.currentTimeMillis();
//        String nowTimeStamp = String.valueOf(time / 1000);
//        System.out.println(nowTimeStamp);
    }
}
