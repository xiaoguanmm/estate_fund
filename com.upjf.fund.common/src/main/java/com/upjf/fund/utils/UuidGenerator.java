package com.upjf.fund.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * uuid生成器
 * @author wufujing
 *
 */
public class UuidGenerator {
    /**
     * 生成唯一标示uuid
     * 
     * @return
     */
    public static String getUuidGenerator() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
    
    
    /**
     * 生成以时间追加随机数的字符串
     * @author  lixq 
     * @param bit			时间追加的位数,默认六位
     * @return  String      最终输出格式:20180928821070
     * @date 2018年9月28日
     */
    public static String getDateFormatUuid(int bit){
    	String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        returnStr = f.format(date).replaceAll("-", "");
    	
        if (bit <= 1) {
			bit = 6;

		} else {

			bit -= 1;
		}
		int num = (int) ((Math.random() * 9 + 1) * Math.pow(10, bit));
		
		returnStr = returnStr + num;
    	
    	return returnStr;
    }
    
    
    public static void main(String[] args) {
//        String uuid=UuidGenerator.getUuidGenerator();
        String uuid=UuidGenerator.getDateFormatUuid(6);
        System.out.println(uuid);
    }

}
