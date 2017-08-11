package cn.szxys.security;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2017/8/8.
 */

public class MD5Util {
    private static final String encryModel="MD5";
    /**
     * 32位md5.
     * 32位小写md5加密
     * @param str
     * @return
     */
    public  static String md5(String str) {
        return encrypt(encryModel, str);
    }
    public static String encrypt(String algorithm, String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());
            StringBuffer sb = new StringBuffer();
            byte[] bytes = md.digest();
            for (int i = 0; i < bytes.length; i++) {
                int b = bytes[i] & 0xFF;
                if (b < 0x10) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
