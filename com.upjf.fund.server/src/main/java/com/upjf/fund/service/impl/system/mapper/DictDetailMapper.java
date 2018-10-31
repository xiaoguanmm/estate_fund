package com.upjf.fund.service.impl.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.DictInfo;

public interface DictDetailMapper {
    /**
     *
     * @mbg.generated 2018-09-20
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int insert(DictDetail record);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int insertSelective(DictDetail record);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    DictDetail selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int updateByPrimaryKeySelective(DictDetail record);

    /**
     *
     * @mbg.generated 2018-09-20
     */
    int updateByPrimaryKey(DictDetail record);

    /**
     * 批量插入字典详细表信息
     * @param dictDetails
     * @return
     */
	int batchInserDictDetail(@Param("dictDetails")DictDetail[] dictDetails);

	/**
	 * 根据主表id值获取字典详细信息
	 * @param pid
	 * @return
	 */
	List<DictDetail> getDictDetailsByPid(@Param("pid")String pid);

	/**
	 * 批量更新字典详细表
	 * @param dictDetails
	 * @return
	 */
	int batchUpdateDetails(@Param("dictDetails")DictDetail[] dictDetails);

	int deleteByDictId(@Param("dictInfoId")String pid);
	
	
	/**
	 * 根据字典编码,查询获取对应的字典详情信息
	 * @param code
	 * @author lixq
	 * @return
	 */
	public List<DictDetail> getDictDetailsByCode(String code);
}