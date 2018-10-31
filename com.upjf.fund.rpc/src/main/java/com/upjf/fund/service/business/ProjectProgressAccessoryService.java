package com.upjf.fund.service.business;

import java.util.List;

import com.upjf.fund.dto.ProjectProgressAccessory;

/**
 * 项目进度与附件关系服务接口
 * @author lixq
 * @date 2018年9月30日
 */
public interface ProjectProgressAccessoryService {
	
	//批量删除项目进度信息
	int delProgressAccessoryByPids(List<String> pids,String updateId) throws Exception;
	
	//保存项目进度信息
	String saveProgressAccessory(ProjectProgressAccessory ppa) throws Exception;
	

	//根据项目主键,查询 获取项目基本信息
	ProjectProgressAccessory getProgressAccessoryByKey(String pid) throws Exception;
	
	//根据主键修改企业基本信息
	int updateProgressAccessoryByKey(ProjectProgressAccessory ppa) throws Exception;
	
}
