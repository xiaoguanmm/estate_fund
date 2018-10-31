package com.upjf.fund.service.system;

import java.util.Map;

import com.upjf.fund.dto.LogInfo;

/**
 * 系统日志服务
 * 
 * @author wufujing
 * 
 */
public interface LogInfoService {

    /**
     * 系统日志
     * 
     * @param paramsMap
     * @return
     */
    public Map<String, Object> getLogInfoListPage(Map<String, String> paramsMap);
    
    /**
     * 系统日志详情
     * 
     * @param paramsMap
     * @return
     */
    public LogInfo getLogInfo(String logInfoId);

    /**
     * 新增日志
     * 
     * @param LogInfo
     */
    public int addLogInfo(LogInfo logInfo);
}
