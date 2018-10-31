package com.upjf.fund.utils.encrpyt;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 字符串加密工具类<br/>
 * 
 * 一般用于密码加密
 * 
 * @author wufujing
 * 
 */
public class StrEncrypt {
    private static final int fillchar = '=';
    private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    /**
     * 用于登陆密码
     * 
     * @param pwd
     *            密码
     * @param salt
     *            盐值
     * @return
     */
    public static String sha1(String pwd, String salt) {
        if (StringUtils.isBlank(pwd) || StringUtils.isBlank(salt)) {
            return null;
        }
        String encodePwd = encode1(pwd.getBytes());
        String encodeSalt = encode2(salt.getBytes());
        String encryptPwd = DigestUtils.sha256Hex(encodePwd + encodeSalt);
        return encryptPwd;
    }

    /**
     * 用于用户支付密码
     * 
     * @param pwd
     *            密码
     * @param salt
     *            盐值
     * @return
     */
    public static String sha2(String pwd, String salt) {
        if (StringUtils.isBlank(pwd) || StringUtils.isBlank(salt)) {
            return null;
        }
        String encodePwd = encode2(pwd.getBytes());
        String encodeSalt = encode1(salt.getBytes());
        String encryptPwd = DigestUtils.sha256Hex(encodePwd + cvt + encodeSalt);
        return encryptPwd;
    }

    private static String encode1(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        int i;
        for (i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }

    private static String encode2(byte[] data) {
        int c;
        int len = data.length;
        StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
        for (int i = 0; i < len; ++i) {
            c = (data[i] >> 2) & 0x3f;
            ret.append(cvt.charAt(c));
            c = (data[i] << 4) & 0x3f;
            if (++i < len) {
                c |= (data[i] >> 4) & 0x0f;
            }

            ret.append(cvt.charAt(c));
            if (i < len) {
                c = (data[i] << 2) & 0x3f;
                if (++i < len) {
                    c |= (data[i] >> 6) & 0x03;
                }
                ret.append(cvt.charAt(c));
            } else {
                ++i;
                ret.append((char) fillchar);
            }
            if (i < len) {
                c = data[i] & 0x3f;
                ret.append(cvt.charAt(c));
            } else {
                ret.append((char) fillchar);
            }
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String pwd = "123456";
        String salt = "123";
        String pwd1 = sha1(pwd, salt);
        String pwd2 = sha2(pwd, salt);
        System.out.println(pwd1 + "           " + pwd1.length());
        System.out.println(pwd2 + "           " + pwd2.length());
    }
}
