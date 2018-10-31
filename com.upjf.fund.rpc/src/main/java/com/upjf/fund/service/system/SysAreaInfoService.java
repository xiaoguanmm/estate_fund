package com.upjf.fund.service.system;

import java.util.List;

import com.upjf.fund.dto.SysAreaInfo;


/**
 * 系统地区信息服务接口
 * @author lixq
 * @date 2018年9月27日
 */
public interface SysAreaInfoService{
	
	//根据主键删除系统地区信息表(物理删除)
    int delSysAreaByKey(String areaCode) throws Exception;


    //新增系统地区信息
    String saveSysAreaInfo(SysAreaInfo area) throws Exception;
   
    //根据主键查询获取系统地区信息
    SysAreaInfo getSysAreaByKey(String areaCode) throws Exception;

    
    //根据主键更新系统地区信息表
    int updateSysAreaByKey(SysAreaInfo record) throws Exception;

    //根据级别获取系统地区信息
    List<SysAreaInfo> getSysAreaByLevel(String level) throws Exception;
    
    //根据条件获取响应的系统地区信息
    List<SysAreaInfo> getSysAreaByCondition(SysAreaInfo areaInfo) throws Exception;
}
