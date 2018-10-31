package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.BusinessComposition;

/**
 * 项目业态组成服务接口
 * @author lixq
 * @date 2018年9月26日
 */
public interface BusinessCompositionService {
	
	//根据条件分页查询获取项目业态组成信息
	Map<String, Object> getBusCompoListByPage(BusinessComposition busCompo,Page page) throws Exception;
	
	//批量删除项目业态组成信息
	int delBusCompoByPids(List<String> pids,String updateId);
	
	
	//保存项目业态组成信息
	String saveBusCompoInfo(BusinessComposition busCompo) throws Exception;
	

	//根据项目主键,查询 获取项目业态组成信息
	BusinessComposition getBusCompoByKey(String pid) throws Exception;
	
	//根据主键修改企业基本信息
	int updateBusCompoByKey(BusinessComposition busCompo) throws Exception;

}
