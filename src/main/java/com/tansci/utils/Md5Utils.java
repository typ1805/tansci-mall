package com.tansci.utils;

import java.io.UnsupportedEncodingException;

/**
 * @path：com.tansci.utils.Md5Utils.java
 * @className：Md5Utils.java
 * @description：MD5加密
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
public class Md5Utils {

    private static final int HEX_VALUE_COUNT = 16;

    public static String getMD5(byte[] bytes) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] str = new char[16 * 2];
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] tmp = md.digest();
            int k = 0;
            for (int i = 0; i < HEX_VALUE_COUNT; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(str);
    }

    public static String getMD5(String value, String encode) {
        String result = "";
        try {
            result = getMD5(value.getBytes(encode));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
