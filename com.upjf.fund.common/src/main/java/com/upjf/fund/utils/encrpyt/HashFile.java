package com.upjf.fund.utils.encrpyt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * 计算文件Hash值
 * 
 * @author wufujing
 * 
 */
public class HashFile {
    public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getHash(File file, String hashType) throws Exception {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        MessageDigest hash = MessageDigest.getInstance(hashType);
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            hash.update(buffer, 0, numRead);
        }
        fis.close();
        return toHexString(hash.digest()).toLowerCase();
    }

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String fileName = "D:\\tomcat.gz";
        String hashType = "MD5";
        try {
            System.out.println(hashType + " == " + getHash(new File(fileName), hashType));
            hashType = "SHA1";
            System.out.println(hashType + " == " + getHash(new File(fileName), hashType));
            hashType = "SHA-256";
            System.out.println(hashType + " == " + getHash(new File(fileName), hashType));
            hashType = "SHA-384";
            System.out.println(hashType + " == " + getHash(new File(fileName), hashType));
            hashType = "SHA-512";
            System.out.println(hashType + " == " + getHash(new File(fileName), hashType));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
