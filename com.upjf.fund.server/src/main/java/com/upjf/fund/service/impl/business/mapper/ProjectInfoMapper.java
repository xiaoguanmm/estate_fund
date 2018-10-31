package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.dto.ProjectVo;

public interface ProjectInfoMapper {
    
	
	//根据主键删除项目基本信息(物理删除)
    int deleteProInfoByKey(String pid);

    //新增项目基本信息
    void saveProjectInfo(ProjectInfo proInfo);

    //根据主键查询获取单条项目基本信息
    ProjectInfo getProjectInfoByKey(String pid);

    //根据主键更新对应的项目基本信息
    int updateProInfoByKey(ProjectInfo proInfo);
    
    //根据主键集合批量删除项目基本信息(伪删除)
    int delProInfoByPids(Map<String,Object> paramsMap);
    
    //分页获取项目管理信息
    List<ProjectVo> getProjectVoListByPage(Map<String,Object> queryParams);
    
    //获取满足条件的所有记录数
    int getTotalCount(Map<String,Object> queryParams);
    
    //根据状态,获取有效或者无效状态的项目信息
    List<ProjectInfo>  getProjectListByStatus(Integer status);
    
    //根据项目主键查询获取项目公司相关信息
    List<Map<String,Object>> getProjectCompanyByProjectPid(String projectPid);
    

    /**
	 * 根据项目公司id查询项目公司pid和项目公司名称
	 * @author zhangcai
	 * @param projectInfoPid
	 * @return
	 */
	List<Map<String, Object>> getBusinessPrjInfoPidByPrjId(@Param("projectInfoPid") String projectInfoPid);

	/**
	 * 获取项目最新状态
	 * @param projectId
	 * @return
	 */
	String getProjectStatusByProjectId(String projectId);
}