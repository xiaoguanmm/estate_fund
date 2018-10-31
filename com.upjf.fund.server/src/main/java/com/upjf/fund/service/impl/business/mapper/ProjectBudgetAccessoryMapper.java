package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.ProjectBudgetAccessory;

public interface ProjectBudgetAccessoryMapper {
    /**
     *
     * @mbggenerated 2018-10-11
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbggenerated 2018-10-11
     */
    int insert(ProjectBudgetAccessory record);

    
   /**
    * 保存项目预算附件关系记录
    * @author  lixq 
    * @param   pba
    * @return  int  
    * @date 2018年10月11日
    */
    void saveProjectBudgetAccessory(ProjectBudgetAccessory pba);

    
    /**
     *
     * @mbggenerated 2018-10-11
     */
    ProjectBudgetAccessory selectByPrimaryKey(String pid);

    
    /**
     * 根据主键更新项目预算附件信息
     * @author  lixq 
     * @param   ppa
     * @return  int  
     * @date 2018年10月11日
     */
    int updateBudgetAccessoryByPid(ProjectBudgetAccessory pba);

    /**
     *
     * @mbggenerated 2018-10-11
     */
    int updateByPrimaryKey(ProjectBudgetAccessory record);
    
    
    /**
     * 获取条件项目预算主键下的有效状态且尚未上传文件的项目预算附件信息
     * @author  lixq 
     * @param   prjBudgetId
     * @return  List<ProjectBudgetAccessory>  
     * @date 2018年10月11日
     */
    ProjectBudgetAccessory getBudgetAccessoryByBudgetPid(String prjBudgetId);
    
    
    /**
     * 根据项目预算主键查询获取处于有某个状态下的预算信息
     * @author  lixq 
     * @param   prjBudgetId
     * @return  List<ProjectBudgetAccessory>  
     * @date 2018年10月11日
     */
    List<ProjectBudgetAccessory> getAccessoryByBudgetPid(@Param("prjBudgetId") String prjBudgetId,@Param("status") int status);
    
    
    /**
     * 根据项目预算主键批量查询处于有效状态下的所有的项目预算附件信息
     * @author  lixq 
     * @param   paramsMap
     * @return  List<ProjectBudgetAccessory>  
     * @date 2018年10月14日
     */
    List<ProjectBudgetAccessory> getBudgetAccessoryByBudgetPids(Map<String, Object> paramsMap);
    
    
    /**
     * 根据项目预算附件信息主键批量变更其状态为无效状态
     * @author  lixq 
     * @param   paramsMap
     * @return  int  
     * @date 2018年10月14日
     */
    int delBudgetAccessoryByPids(Map<String, Object> paramsMap);
    
}