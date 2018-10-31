package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.BusinessPrjInfo;

public interface BusinessPrjInfoMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(BusinessPrjInfo record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(BusinessPrjInfo record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    BusinessPrjInfo selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(BusinessPrjInfo record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(BusinessPrjInfo record);
    
    /**
     * 根据条件查询项目公司列表
     * @author zhangcai
     * @param condtions
     * @param offset
     * @param pageRows
     * @return
     */
	List<Map<String, Object>> getProjCompanyByConditon(@Param("condtions")Map<String, String> condtions, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
     * 根据条件查询项目公司列表总条数
     * @author zhangcai
     * @param condtions
     * @param offset
     * @param pageRows
     * @return
     */
	Integer countProjCompanyByCondition(@Param("condtions")Map<String, String> condtions);

    /**
	 * 根据企业Pid查询企业项目关系表
	 * @author zhangcai
	 * @param corporationInfoId
	 * @date 2018年9月21日
	 * @return
	 */
	List<BusinessPrjInfo> getBusinessPrjInfoByCorPid(@Param("corporationInfoId") String corporationInfoId);
	
	
	/**
	 * 根据条件获取所有处于该条件下的项目公司信息
	 * @author  lixq 
	 * @param   businessPrjInfo
	 * @return  List<BusinessPrjInfo>  
	 * @date    2018年9月27日
	 */
	List<BusinessPrjInfo> getBusProjInfoByCondition(BusinessPrjInfo businessPrjInfo);

	/**
	 * update企业项目关系表
	 * @author zhangcai
	 * @param businessPrj
	 * @date 2018年9月21日
	 * @return
	 */
	int updateBusinessPrjInfo(BusinessPrjInfo businessPrj);

	/**
	 * 删除企业项目关系表
	 * @author zhangcai
	 * @param paramsMap
	 * @date 2018年10月8日
	 * @return
	 */
	int delBusinessPrjInfoByPids(Map<String, Object> paramsMap);
}