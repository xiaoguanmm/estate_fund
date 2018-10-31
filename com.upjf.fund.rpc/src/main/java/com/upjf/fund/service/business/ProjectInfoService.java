package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.dto.ProjectVo;

/**
 * 项目基本信息服务接口
 * @author lixq
 * @date 2018年9月26日
 */
public interface ProjectInfoService {
	
	//分页获取项目管理信息
	Map<String, Object> getProjectVoListByPage(ProjectVo projectVo,Page page) throws Exception;
	
	//批量删除项目信息
	int delProjectByPids(List<String> pids,String updateId) throws Exception;
	
	
	//保存项目基本信息
	Map<String, Object> saveProjectInfo(ProjectInfo proInfo) throws Exception;
	

	//根据项目主键,查询 获取项目基本信息
	ProjectInfo getProjectInfoByKey(String pid) throws Exception;
	
	//根据主键修改企业基本信息
	int updateProjectInfoByKey(ProjectInfo proInfo) throws Exception;
	
	//根据状态,获取有效或者无效状态的项目信息
	List<ProjectInfo>  getProjectListByStatus(Integer status) throws Exception;

	/**
	 * 根据项目公司id查询项目公司pid和项目公司名称
	 * @author zhangcai
	 * @param projectInfoPid
	 * @return
	 */
	List<Map<String, Object>> getBusinessPrjInfoPidByPrjId(String projectInfoPid);

	/**
	 * 获取项目最新状态
	 * @param projectId
	 * @return
	 */
	String getProjectStatusByProjectId(String projectId);

}
