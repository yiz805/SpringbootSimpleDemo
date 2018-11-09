package com.ding.hddk.authen.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 编码工具类
 *  @author stopper
 * @date 2017-12-07
 */
public class CodeUtil {
    private CodeUtil(){}

    /**
     * md5编码
     * @param str
     */
    public static String md5Encode(String str){
        Md5Hash md5Hash = new Md5Hash(str);
        return md5Hash.toHex();
    }

    public static String md5Encode(char[] chars){
        Md5Hash md5Hash = new Md5Hash(String.valueOf(chars));
        return md5Hash.toHex();
    }

}
