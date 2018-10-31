package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.ProjectProgress;
import com.upjf.fund.dto.ProjectProgressFileVo;

public interface ProjectProgressMapper {
  
	/**
	 * 根据主键物理删除项目进度信息
	 * @author  lixq 
	 * @param   pid
	 * @return  int  
	 * @date 2018年9月29日
	 */
    int deleteByPrimaryKey(String pid);

    
    /**
     * 保存项目进度信息
     * @author  lixq 
     * @param   record
     * @return  int  
     * @date 2018年9月29日
     */
    int saveProProgress(ProjectProgress record);

    
    /**
     * 根据主键获取项目进度信息
     * @author  lixq 
     * @param   pid
     * @return  ProjectProgress  
     * @date 2018年9月29日
     */
    ProjectProgress selectProProgressByKey(String pid);

    
    /**
     * 根据主键变更项目进度信息
     * @author  lixq 
     * @param   proProgress
     * @return  int  
     * @date 2018年9月29日
     */
    int updateProProgressByKey(ProjectProgress proProgress);
    
    
    /**
     * 校验变动的项目进度信息是否与最新一条记录一致
     * @author  lixq 
     * @param   proProgress
     * @return  int  
     * @date 2018年10月9日
     */
    int cheackProProgress(ProjectProgress proProgress);
    
    
    /**
     * 分页查询获取项目进度附件列表
     * @author  lixq 
     * @return  List<ProjectProgressFileVo>  
     * @date 2018年10月9日
     */
    List<ProjectProgressFileVo> getProgressFileByPage(Map<String, Object> paramsMap);
    
    
    /**
     * 统计命中的数量
     * @author  lixq 
     * @param   paramsMap
     * @return  int  
     * @date 2018年10月9日
     */
    int getTotalCount(Map<String, Object> paramsMap);
    
    
    /**
     * 根据项目主键加载所属的最新的项目进度记录
     * @author  lixq 
     * @param   pid
     * @return  ProjectProgress  
     * @date 2018年10月12日
     */
    ProjectProgress getLatestProgressByProjectPid(String projectPid);
    
    
    /**
     * 根据项目主键查询获取处于有效状态的项目进度信息
     * @author  lixq 
     * @param   paramsMap
     * @return  List<ProjectProgress>  
     * @date 2018年10月14日
     */
    List<ProjectProgress> getProgressByProjectPids(Map<String, Object> paramsMap);
    
    
    /**
     * 根据项目进度主键批量变更项目进度状态为无效状态(伪删除)
     * @author  lixq 
     * @param   paramsMap
     * @return  int  
     * @date 2018年10月14日
     */
    int delProgressByPids(Map<String, Object> paramsMap);
    
    
}