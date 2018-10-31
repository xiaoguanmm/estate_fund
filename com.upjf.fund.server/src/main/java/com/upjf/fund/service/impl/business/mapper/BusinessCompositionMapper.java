package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.BusinessComposition;

/**
 * 项目业态组成信息接口
 * @author lixq
 * @date 2018年9月28日
 */
public interface BusinessCompositionMapper {
    
	//根据主键物理删除项目业态组成信息
    int deleteByPrimaryKey(String pid);

    //新增保存项目业态组成信息
    void saveBusCompoInfo(BusinessComposition busCompo);

    //根据主键获取项目业态组成信息
    BusinessComposition getBusCompoByKey(String pid);

    //根据主键更新保存项目业态组成信息
    int updateBusCompoByKey(BusinessComposition busCompo);
    
    //分页获取项目业态组成信息
    List<BusinessComposition> getBusCompoListByPage(Map<String, Object> paramsMap);
    
    //获取所有满足条件的企业业态信息数量 
    int getTotalCount(Map<String, Object> paramsMap);
    
    //根据项目主键获取满足条件的所有的处于有效状态的项目业态主键 
    List<String> getBusCompoPidByProjectPids(Map<String, Object> paramsMap);
    
    //根据项目业态主键批量变更项目业态的状态信息(伪删除)
    int delBusCompoByPids(Map<String, Object> paramsMap);

}