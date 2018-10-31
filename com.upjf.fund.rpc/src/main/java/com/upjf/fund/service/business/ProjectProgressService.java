package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.ProjectProgress;

/**
 * 项目进度信息服务接口
 * @author lixq
 * @date 2018年9月29日
 */
public interface ProjectProgressService {
	
	//批量删除项目进度信息
	int delProProgressByPids(List<String> pids,String updateId) throws Exception;
	
	//保存项目进度信息
	String saveProProgress(ProjectProgress proProgress) throws Exception;
	

	//根据项目主键,查询 获取项目基本信息
	ProjectProgress getProProgressByKey(String pid) throws Exception;
	
	//根据主键修改企业基本信息
	int updateProProgressByKey(ProjectProgress proProgress) throws Exception;
	
	//校验变动的项目进度信息是否与最新一条记录一致
    boolean cheackProProgress(ProjectProgress proProgress) throws Exception;
    
    //分页查询获取项目进度附件列表
    Map<String, Object> getProgressFileByPage(String projectId,Page page) throws Exception;
    
    //删除项目进度状态
    int delProgress(String pid) throws Exception;
    
    //根据项目主键加载所属的最新的项目进度记录
    ProjectProgress getLatestProgressByProjectPid(String projectPid) throws Exception;
}
