package com.upjf.fund.service.business;

import java.util.List;

import com.upjf.fund.dto.ProjectBudgetAccessory;

/**
 * 项目预算附件信息服务接口
 * @author lixq
 * @date 2018年9月29日
 */
public interface ProjectBudgetAccessoryService {
	
	//保存项目预算附件信息
	String saveProjectBudgetAccessory(ProjectBudgetAccessory pba) throws Exception;
	
	//根据主键变更项目预算附件信息
	int updateProjectBudgetAccessory(ProjectBudgetAccessory pba) throws Exception;
	
	//根据条件查询获取项目预算附件信息
	List<ProjectBudgetAccessory> getBudgetAccessoryByCondition(ProjectBudgetAccessory pba) throws Exception;
}
