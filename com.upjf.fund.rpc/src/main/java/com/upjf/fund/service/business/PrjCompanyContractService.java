package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.EstateFundFile;

/**
 * 项目公司合同附件服务接口
 * @author zhangcai
 * @date 2018年9月29日
 */
public interface PrjCompanyContractService {
	
	/**
	 * 批量插入项目公司合同附件表
	 * @author zhangcai
	 * @param files
	 * @param businessPrjInfoId
	 * @param contractName 
	 * @param loginId 
	 * @return int
	 */
	int batchInsertPrjCompanyContract(List<EstateFundFile> files,String businessPrjInfoId, String contractName, String loginId);

	/**
	 * 查询项目公司合同附件
	 * @author zhangcai
	 * @param businessPrjInfoId
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getPrjCompContractByBusPrjInfoId(String businessPrjInfoId, Integer offset, Integer pageRows);

	/**
	 * 查询项目公司合同附件条数
	 * @author zhangcai
	 * @param businessPrjInfoId
	 * @return
	 */
	Integer countPrjCompContractByBusPrjInfoId(String businessPrjInfoId);
}
