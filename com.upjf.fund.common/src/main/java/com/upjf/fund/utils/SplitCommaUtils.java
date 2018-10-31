package com.upjf.fund.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 该工具类用于切割逗号拼接的字符串,将它们根据逗号分隔后,获取到由这些独立字符串组成的集合
 * 可用于界面在批量删除时,主键逗号拼接传入后台时的运用场景等
 * @author lixq
 * @date 2018年9月26日
 */
public class SplitCommaUtils {
	
	
	
	/**
	 * 切割传入的企业主键多个corporationIds,以逗号分隔
	 * @param corporationIds
	 * @return
	 */
	public static List<String> splitCorPids(String corporationIds){
		List<String> pids = new ArrayList<String>();
		if(StringUtils.isNotBlank(corporationIds)){
			if(!corporationIds.contains(",")){
				pids.add(corporationIds);
			}else{
				String[] pidStrs = corporationIds.split(",");
				if(pidStrs != null && pidStrs.length >0){
					for(int i=0;i<pidStrs.length;i++){
						String pid = pidStrs[i];
						if( (pid.contains(",")) && (!",".equals(pid.trim())) ){
							pid = pid.replace(",", "").trim();
						}
						
						if(StringUtils.isNotBlank(pid) && !",".equals(pid)){
							pids.add(pid);
						}
					}
				}
			}
		}
		return pids;
	}
}
