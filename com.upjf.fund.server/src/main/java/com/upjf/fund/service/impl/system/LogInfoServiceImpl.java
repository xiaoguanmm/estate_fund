package com.upjf.fund.service.impl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upjf.fund.dto.LogInfo;
import com.upjf.fund.service.impl.system.mapper.LogInfoMapper;
import com.upjf.fund.service.system.LogInfoService;

/**
 * 系统日志服务
 * 
 * @author wufujing
 * 
 */
@Service("logInfoService")
public class LogInfoServiceImpl implements LogInfoService{
	@Autowired
	private LogInfoMapper logInfoMapper;
	
    /**
     * 系统日志列表页面
     * 
     * @param paramsMap
     * @return
     */
    public Map<String, Object> getLogInfoListPage(Map<String, String> paramsMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int total = logInfoMapper.getLogInfoListCount(paramsMap);
			if(total > 0){
				List<LogInfo> logInfoList = logInfoMapper.getLogInfoList(paramsMap);
				resultMap .put("logInfoList", logInfoList);
			}
			resultMap .put("total", total);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap .put("total", 0);
		}
		
		return resultMap;
    }

    /**
     * 新增日志
     * 
     * @param log
     */
    public int addLogInfo(LogInfo logInfo){
		int result = 0;
		try {
			result = logInfoMapper.addLogInfo(logInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
    }
    
    
    /**
     * 系统日志详情
     * 
     * @param paramsMap
     * @return
     */
    public LogInfo getLogInfo(String logInfoId){
    	LogInfo logInfo = null;
		try {
			logInfo = logInfoMapper.getLogInfo(logInfoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logInfo;
    }
}
