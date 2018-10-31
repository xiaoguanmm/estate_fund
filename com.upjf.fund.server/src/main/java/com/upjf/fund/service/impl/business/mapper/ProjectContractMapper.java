package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.ProjectContract;

public interface ProjectContractMapper {

	int deleteByPrimaryKey(String pid);

    int insert(ProjectContract record);
    
    ProjectContract selectByPrimaryKey(String pid);

    int updateByPrimaryKey(ProjectContract record);
    
    
    
    
    /**
     * 保存项目合同附件与项目关系信息
     * @author  lixq 
     * @param record
     * @return  int  
     * @date 2018年10月10日
     */
    void saveProjectContract(ProjectContract record);

    
    
    /**
     * 根据主键变更项目合同信息
     * @author  lixq 
     * @param   pc
     * @return  int  
     * @date 2018年10月10日
     */
    int updateProjectContractByPid(ProjectContract pc);

    
    
    /**
     * 分页获取项目合同附件列表信息
     * @author  lixq 
     * @param   queryParamsMap
     * @return  List<ProjectContract>  
     * @date 2018年10月10日
     */
    List<ProjectContract> getContractListByPage(Map<String, Object> queryParamsMap);
    
    
    
    /**
     * 统计满足条件的总记录数
     * @author  lixq 
     * @param   queryParamsMap
     * @return  int  
     * @date 2018年10月10日
     */
    int getTotalCount(Map<String, Object> queryParamsMap);
    
    
    /**
     * 根据项目主键获取满足条件的所有的处于有效状态的项目合同信息
     * @author  lixq 
     * @param   paramsMap
     * @return  List<String>  
     * @date 2018年10月14日
     */
    List<ProjectContract> getContractByProjectPids(Map<String, Object> paramsMap);
    
    
    /**
     * 根据项目合同主键批量变更项目合同的状态信息(伪删除)
     * @author  lixq 
     * @param   paramsMap
     * @return  int  
     * @date 2018年10月14日
     */
    int delContractByPids(Map<String, Object> paramsMap);
    
    
    /**
     * 根据项目合同主键查询获取处于有效状态的且存在上传文件的所有的文件主键
     * @author  lixq 
     * @param   paramsMap
     * @return  List<String>  
     * @date 2018年10月14日
     */
    List<String> getContractFilePidByPids(Map<String, Object> paramsMap);
}