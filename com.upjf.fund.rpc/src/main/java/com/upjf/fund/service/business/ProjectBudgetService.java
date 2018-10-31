package com.upjf.fund.service.business;

import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.ProjectBudget;

/**
 * 项目预算信息服务接口
 * @author lixq
 * @date 2018年9月29日
 */
public interface ProjectBudgetService {
	
	//保存项目预算信息
	String saveProjectBudget(ProjectBudget pb) throws Exception;
	
	//校验预算名称是否改变:true表示预算名称已改变,false表示项目预算名称尚未改变
	boolean cheackBudget (ProjectBudget pb) throws Exception;
	
	//分页获取项目预算记录
	Map<String, Object> getBudgetDataByPage(String projectId,Page page) throws Exception;
	
	//删除项目预算记录
	int delbudget(String pid) throws Exception;
	
	//根据项目主键获取所属的最新的项目预算信息
	ProjectBudget getLatestBudgetByProjectPid(String projectPid) throws Exception;
	
}
