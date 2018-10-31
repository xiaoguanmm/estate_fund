package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.ProjectBudget;
import com.upjf.fund.dto.ProjectBudgetVo;

public interface ProjectBudgetMapper {
    /**
     *
     * @mbggenerated 2018-10-11
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbggenerated 2018-10-11
     */
    int insert(ProjectBudget record);

    
    /**
     * 保存项目预算信息
     * @author  lixq 
     * @param   pb
     * @return  int  
     * @date 2018年10月11日
     */
    void saveProjectBudget(ProjectBudget pb);

    /**
     * 根据主键获取项目预算信息
     * @author  lixq 
     * @param   pid
     * @return  ProjectBudget  
     * @date 2018年10月11日
     */
    ProjectBudget getProjectBudgetByKey(String pid);

    
    /**
     * 根据主键更新项目预算信息
     * @author  lixq 
     * @param   pb
     * @return  int  
     * @date 2018年10月11日
     */
    int updateProjectBudgetByPid(ProjectBudget pb);

    /**
     *
     * @mbggenerated 2018-10-11
     */
    int updateByPrimaryKey(ProjectBudget record);
    
    
    /**
     * 分页获取项目预算记录
     * @author  lixq 
     * @param   queryParamsMap
     * @return  List<ProjectBudgetVo>  
     * @date 2018年10月11日
     */
    List<ProjectBudgetVo> getBudgetDataByPage(Map<String, Object> queryParamsMap);
    
    
    /**
     * 获取命中总数
     * @author  lixq 
     * @param   queryParamsMap
     * @return  int  
     * @date 2018年10月11日
     */
    int getTotalCount(Map<String, Object> queryParamsMap);
    
    
    /**
     * 根据项目主键获取所属的最新的项目预算信息
     * @author  lixq 
     * @param   projectPid
     * @return  ProjectBudget  
     * @date 2018年10月12日
     */
  	ProjectBudget getLatestBudgetByProjectPid(String projectPid);
  	
  	
  	/**
  	 * 根据项目主键,批量获取有效状态的项目预算信息
  	 * @author  lixq 
  	 * @param   paramsMap
  	 * @return  List<ProjectBudget>  
  	 * @date 2018年10月14日
  	 */
  	List<ProjectBudget> getBudgetByProjectPids(Map<String, Object> paramsMap);
  	
  	
  	/**
  	 * 根据项目预算主键批量变更项目预算状态为无效状态(伪删除)
  	 * @author  lixq 
  	 * @param   paramsMap
  	 * @return  int  
  	 * @date 2018年10月14日
  	 */
  	int delBudgetByPids(Map<String, Object> paramsMap);
}