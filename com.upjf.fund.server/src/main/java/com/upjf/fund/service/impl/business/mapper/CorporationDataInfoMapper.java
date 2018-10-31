package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.CorporationDataInfo;

public interface CorporationDataInfoMapper {
    /**
     *
     * @mbggenerated 2018-10-15
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbggenerated 2018-10-15
     */
    int insert(CorporationDataInfo record);

    
    /**
     * 根据企业基本信息主键和文件类型主键变更处于有效状态的企业资料扫描件状态(伪删除)
     * @author  lixq 
     * @param   paramsMap
     * @return  int  
     * @date 2018年10月15日
     */
    int delCorDataInfoByCondition(Map<String, Object> paramsMap);
    
    
    /**
     * 保存企业资料扫描件
     * @author  lixq 
     * @param   corDataInfo  void  
     * @date 2018年10月15日
     */
    void saveCorporationDataInfo(CorporationDataInfo corDataInfo);
    
    
    /**
     * 根据企业主键和文件类型查询获取处于有效状态的企业资料扫描件信息
     * @author  lixq 
     * @param   paramsMap
     * @return  CorporationDataInfo  
     * @date 2018年10月15日
     */
    CorporationDataInfo getDataInfoByCondition(Map<String, Object> paramsMap);
    
    
    
    /**
     * 根据企业主键获取处于有效状态的企业资料扫描件信息
     * @author  lixq 
     * @param   corPid
     * @return  List<CorporationDataInfo>  
     * @date 2018年10月15日
     */
    List<CorporationDataInfo> getCorDataInfoByCorPid(@Param("corPid") String corPid);
    
    
    /**
     *
     * @mbggenerated 2018-10-15
     */
    CorporationDataInfo selectByPrimaryKey(String pid);

    /**
     *
     * @mbggenerated 2018-10-15
     */
    int updateByPrimaryKeySelective(CorporationDataInfo record);

    /**
     *
     * @mbggenerated 2018-10-15
     */
    int updateByPrimaryKey(CorporationDataInfo record);
}