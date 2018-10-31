package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.PrjCompanyContract;

public interface PrjCompanyContractMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(PrjCompanyContract record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(PrjCompanyContract record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    PrjCompanyContract selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(PrjCompanyContract record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(PrjCompanyContract record);

    /**
	 * 批量插入项目公司合同附件表
	 * @author zhangcai
	 * @param files
	 * @param businessPrjInfoId
	 */
	int batchInsertPrjCompanyContract(@Param("accessoryList") List<PrjCompanyContract> accessoryList);

	/**
	 * 查询项目公司合同附件
	 * @author zhangcai
	 * @param businessPrjInfoId
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getPrjCompContractByBusPrjInfoId(@Param("businessPrjInfoId") String businessPrjInfoId, @Param("offset") Integer offset, @Param("pageRows") Integer pageRows);

	/**
	 * 查询项目公司合同附件条数
	 * @author zhangcai
	 * @param businessPrjInfoId
	 * @return
	 */
	Integer countPrjCompContractByBusPrjInfoId(@Param("businessPrjInfoId") String businessPrjInfoId);
	
}