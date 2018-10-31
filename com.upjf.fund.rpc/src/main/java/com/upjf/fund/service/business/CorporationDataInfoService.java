package com.upjf.fund.service.business;

import java.util.List;

import com.upjf.fund.dto.CorporationDataInfo;

/**
 * 企业资料扫描件服务接口
 * @author lixq
 * @date 2018年9月19日
 */
public interface CorporationDataInfoService {
	
	//根据企业基本信息主键和文件类型主键变更处于有效状态的企业资料扫描件状态(伪删除)
	int delCorporationByCondition(String corPid,String corDataType,String updateId) throws Exception;
	
	//保存企业资料扫描件
	String saveCorporationDataInfo(CorporationDataInfo corDataInfo) throws Exception;
	
	
	//根据企业主键获取处于有效状态的企业资料扫描件信息
	List<CorporationDataInfo> getCorDataInfoByCorPid(String corPid) throws Exception;
	
	

}
