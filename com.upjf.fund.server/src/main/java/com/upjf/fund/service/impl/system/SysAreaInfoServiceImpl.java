/** Project Name:com.upjf.risk.server File Name:UserInfoServiceImpl.java Package Name:com.upjf.risk.userinfo.service.impl Date:2018年3月26日上午11:24:21 Copyright (c)
 * 2018, chenzhou1025@126.com All Rights Reserved. */

package com.upjf.fund.service.impl.system;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.SysAreaInfo;
import com.upjf.fund.service.impl.system.mapper.SysAreaInfoMapper;
import com.upjf.fund.service.system.SysAreaInfoService;


/**
 * 系统地区服务接口实现类
 * @author lixq
 * @date 2018年9月27日
 */
@Service("sysAreaInfoService")
public class SysAreaInfoServiceImpl implements SysAreaInfoService{
	
	private static Logger log = LoggerFactory.getLogger(SysAreaInfoServiceImpl.class);
	
	
	@Autowired
	private SysAreaInfoMapper sysAreaInfoMapper;								//系统地区接口
	
	
	
	//根据主键删除系统地区信息表(物理删除)
	@Override
	@Transactional
	public int delSysAreaByKey(String areaCode) throws Exception {
		int result = -1;
		if(StringUtils.isNotBlank(areaCode)){
			result = sysAreaInfoMapper.delSysAreaByKey(areaCode);
		}
		
		return result;
	}
	
	
	//新增系统地区信息
	@Override
	@Transactional
	public String saveSysAreaInfo(SysAreaInfo area) throws Exception{
		String result = "";
		if(area != null){
			sysAreaInfoMapper.saveSysAreaInfo(area);
			result = area.getAreaCode();
		}
		return result;
	}
	
	
	//根据主键查询获取系统地区信息
	@Override
	public SysAreaInfo getSysAreaByKey(String areaCode) throws Exception{
		SysAreaInfo areaInfo = null;
		if(StringUtils.isNotBlank(areaCode)){
			areaInfo = sysAreaInfoMapper.getSysAreaByKey(areaCode);
		}
		return areaInfo;
	}
	
	
	//根据主键更新系统地区信息表
	@Override
	@Transactional
	public int updateSysAreaByKey(SysAreaInfo area) throws Exception{
		int result = -1;
		if(area != null){
			result = sysAreaInfoMapper.updateSysAreaByKey(area);
		}
		return result;
	}
	
	
	//根据级别获取系统地区信息
	@Override
	public List<SysAreaInfo> getSysAreaByLevel(String level) throws Exception{
		List<SysAreaInfo> list = null;
		if(StringUtils.isNotBlank(level)){
			list = sysAreaInfoMapper.getSysAreaByLevel(level);
		}
		return list;
	}


	//根据条件获取响应的系统地区信息
	@Override
	public List<SysAreaInfo> getSysAreaByCondition(SysAreaInfo areaInfo) throws Exception {
		List<SysAreaInfo> list = null;
		if(areaInfo != null){
			list = sysAreaInfoMapper.getSysAreaByCondition(areaInfo);
		}
		return list;
	}
	

	

}
