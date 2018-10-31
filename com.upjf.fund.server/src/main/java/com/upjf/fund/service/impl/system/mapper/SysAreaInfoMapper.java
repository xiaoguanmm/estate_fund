package com.upjf.fund.service.impl.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.SysAreaInfo;

/**
 * 系统地区信息服务接口
 * @author lixq
 * @date 2018年9月27日
 */
public interface SysAreaInfoMapper {
    
	//根据主键删除系统地区信息表(物理删除)
    int delSysAreaByKey(String areaCode);


    //新增系统地区信息
    void saveSysAreaInfo(SysAreaInfo area);

   
    //根据主键查询获取系统地区信息
    SysAreaInfo getSysAreaByKey(String areaCode);

    
    //根据主键更新系统地区信息表
    int updateSysAreaByKey(SysAreaInfo record);

    //根据级别获取系统地区信息
    List<SysAreaInfo> getSysAreaByLevel(@Param("level") String level);
    
    //根据条件获取响应的系统地区信息
    List<SysAreaInfo> getSysAreaByCondition(SysAreaInfo areaInfo);
}