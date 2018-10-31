package com.upjf.fund.utils;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

public class MD5Utils {
	private static final Logger	logger	= Logger.getLogger(MD5Utils.class);
	
	 // 全局数组
     private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
             "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
     private final static String DEFAULT_CHARSET = "UTF-8";
	/**
	 * MD5加密
	 * @param source
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encode(byte[] source){
		try {
			char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
					'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			// MD5 的计算结果是一个 128 位的长整数，
			byte tmp[] = md.digest(); 
			// 用字节表示就是 16 个字节
			// 每个字节用 16 进制表示的话，使用两个字符，
			char str[] = new char[16 * 2]; 
			// 所以表示成 16 进制需要 32 个字符
			// 表示转换结果中对应的字符位置
			int k = 0; 
			for (int i = 0; i < 16; i++) { 
				// 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				// 取第 i 个字节
				byte byte0 = tmp[i]; 
				// 取字节中高 4 位的数字转换,
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				// >>> 为逻辑右移，将符号位一起右移
				// 取字节中低 4 位的数字转换
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String s = new String(str);
			md = null;
			// 换后的结果转换为字符串
			return s!=null?s:null; 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    
	/**
	 * md5加密
	 * @param content
	 * @return
	 */
	public static String encrypt(String content){
		String encryptTxt = null;
		
		try {
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(content.getBytes());
			encryptTxt = new BASE64Encoder().encode(md5.digest());
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return encryptTxt;
	}
		
	public static String tltMd5(String text){
		String md5 = null;
		
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
			md5 = bytesHexString(md.digest(text.getBytes()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return md5;
	}
	
	public static String bytesHexString(byte[] b){
		
		String ret = "";
		for(int i=0;i<b.length;i++){
			String hex = Integer.toHexString(b[i] & 0xFF);
			if(hex.length() == 1){
				hex = "0" + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
	
	/**
	  * @Description: 加入盐值进行md5加密
	  * @param content 待加密内容
	  * @param salt 盐值，加强加密复杂度
	  * @return
	  * @throws NoSuchAlgorithmException
	  * @throws UnsupportedEncodingException
	  * @author: dulin
	  * @date: 2017年5月31日 上午10:06:09
	 */
	public static String encryptWithSalt(String content, String salt){  
      //声明加密后的口令数组变量  
      byte[] pwd = null;  
      //声明盐数组变量  
      byte[] salts=null;
	//声明消息摘要对象  
	  MessageDigest md=null;
	try {
		salts = salt.getBytes(DEFAULT_CHARSET);  
		  //创建消息摘要  
		  md = MessageDigest.getInstance("MD5");  
		  //将盐数据传入消息摘要对象  
		  md.update(salts);
		  //将口令的数据传给消息摘要对象  
		  md.update(content.getBytes(DEFAULT_CHARSET));
	} catch (UnsupportedEncodingException e) {
		logger.info("不支持"+DEFAULT_CHARSET+"编码格式");
		e.printStackTrace();
	}catch (NoSuchAlgorithmException e) {
		logger.info("不支持MD5算法");
		e.printStackTrace();
	}
      //获得消息摘要的字节数组  
      byte[] digest = md.digest();  

      //因为要在口令的字节数组中存放盐，所以加上盐的字节长度  
      pwd = new byte[digest.length + salts.length];  
      //将盐的字节拷贝到生成的加密口令字节数组的前12个字节，以便在验证口令时取出盐  
      System.arraycopy(salts, 0, pwd, 0, salts.length);  
      //将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节  
      System.arraycopy(digest, 0, pwd, salts.length, digest.length);  
      //将字节数组格式加密后的口令转化为16进制字符串格式的口令  
      return bytesHexString(pwd);
  }
    
	public static void main(String[] args) {
		System.out.println(MD5Utils.encode("86006966".getBytes()));
		
        System.out.println(MD5Utils.GetMD5Code("000000"));
        System.out.println(MD5Utils.encryptWithSalt("111111", "estate.fund"));
        System.out.println("6573746174652E66756E64FE4B56807AF18B5422ACB10F087B6312".length());
	}
}
