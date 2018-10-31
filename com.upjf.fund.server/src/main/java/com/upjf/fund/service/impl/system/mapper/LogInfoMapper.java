package com.upjf.fund.service.impl.system.mapper;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.LogInfo;

public interface LogInfoMapper {

    public List<LogInfo> getLogInfoList(Map<String, String> paramsMap);
    
    public int getLogInfoListCount(Map<String, String> paramsMap);
    
    public LogInfo getLogInfo(String logInfoId);
    
    public int addLogInfo(LogInfo logInfo);
}