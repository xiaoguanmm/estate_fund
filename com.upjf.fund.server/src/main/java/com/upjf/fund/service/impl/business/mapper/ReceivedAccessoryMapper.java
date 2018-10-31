package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.ReceivedAccessory;

public interface ReceivedAccessoryMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(ReceivedAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(ReceivedAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    ReceivedAccessory selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(ReceivedAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(ReceivedAccessory record);

    /**
     * 批量插入回款附件信息
     * @param accessoryList
     * @return
     */
	int batchInsertPaybackAccessory(@Param("accessoryList")List<ReceivedAccessory> accessoryList);

	/**
	 * 根据paybackId获取回款信息附件信息
	 * @param paybackId
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getPaybackAccessoriesByPaybackId(@Param("paybackId")String paybackId, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据paybackId统计回款信息附件信息
	 * @param paybackId
	 * @return
	 */
	Integer countPaybackAccessoriesByPaybackId(@Param("paybackId")String paybackId);
}