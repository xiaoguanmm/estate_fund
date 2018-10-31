package com.upjf.fund.service.impl.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.DictInfo;

public interface DictInfoMapper {
    /**
     *
     * @mbg.generated 2018-09-20
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int insert(DictInfo record);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int insertSelective(DictInfo record);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    DictInfo selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int updateByPrimaryKeySelective(DictInfo record);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int updateByPrimaryKey(DictInfo record);

	List<Map<String, Object>> getDictInfosByConditions(@Param("dictInfo")DictInfo dictInfo, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	DictInfo getDictInfoBySingleCondition(@Param("code")String oriCode, @Param("name")String oriName);

	Integer countDictInfosByConditions(@Param("dictInfo")DictInfo dictInfo);

	/**
	 * 根据code获取字典数据
	 * @param code
	 * @return
	 */
	List<Map<String, String>> getdataByCode(@Param("code")String code);

	/**
	 * 获取企业信息
	 * @param type
	 * @return
	 */
	List<Map<String, String>> getEnterpriseDataByType(@Param("type")String type);
}