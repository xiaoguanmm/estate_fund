package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.ProjectProgressAccessory;

public interface ProjectProgressAccessoryMapper {
    
	/**
	 * 根据主键删除项目与文件关系信息
	 * @author  lixq 
	 * @param   pid
	 * @return  int  
	 * @date 2018年9月30日
	 */
    int delProgressAccessoryByKey(String pid);

    

    /**
     * 根据主键条件获取项目与文件关系信息
     * @author  lixq 
     * @param   pid
     * @return  ProjectProgressAccessory  
     * @date 2018年9月30日
     */
    ProjectProgressAccessory getProgressAccessoryByPid(@Param("pid") String pid);

    
   /**
    * 根据主键更新项目进度关系信息
    * @author  lixq 
    * @param   record
    * @return  int  
    * @date 2018年9月30日
    */
    int updateProgressAccessoryByKey(ProjectProgressAccessory record);

    
    /**
     * 保存项目与文件关系信息
     * @author  lixq 
     * @param   record
     * @return  int  
     * @date 2018年9月30日
     */
    void saveProgressAccessory(ProjectProgressAccessory ppa);
    
    
    /**
     * 根据项目进度主键查询获取是否存在处于当前进度状态且无文件关系主键的记录
     * @author  lixq 
     * @param   progressId
     * @return  ProjectProgressAccessory  
     * @date 2018年10月9日
     */
    ProjectProgressAccessory getProgressAccessoryByProgressId(String progressId);
    
    
    /**
     * 根据条件查询获取响应的项目进度附件信息记录
     * @author  lixq 
     * @param   queryParams
     * @return  List<ProjectProgressAccessory>  
     * @date 2018年10月10日
     */
    List<ProjectProgressAccessory> getAllProgressAccessoryByCondition(Map<String,Object> queryParams);
    
    
    /**
     * 根据项目进度批量查询获取有效状态下的项目进度附件关系信息
     * @author  lixq 
     * @param   paramsMap
     * @return  List<ProjectProgressAccessory>  
     * @date 2018年10月14日
     */
    List<ProjectProgressAccessory> getProgressAccessoryByProgressPids(Map<String, Object> paramsMap);
    
    
    /**
     * 根据项目附件关系信息主键批量变更其状态为无效状态
     * @author  lixq 
     * @param   paramsMap
     * @return  int  
     * @date 2018年10月14日
     */
    int delProgressAccessoryByPids(Map<String, Object> paramsMap);
}