package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.CorporationBankInfo;

/**
 * 企业银行相关服务接口
 * @author lixq
 * @date 2018年9月19日
 */
public interface CorporationBankService {
	
	//根据条件分页查询获取企业银行相关信息
	Map<String, Object> getCorporationBankListByPage(CorporationBankInfo bankInfo,Page page);
	
	//根据主键删除企业银行信息(物理删除)
	int delCorporationBankByKey(String pid);
	
	//保存企业银行信息
	Map<String,Object> insertCorporationBank(CorporationBankInfo bankInfo);
	
	//根据企业银行主键,查询 获取企业银行信息
	CorporationBankInfo getCorporationBankByKey(String pid);
	
	//根据主键修改企业银行信息
	int updateCorporationBankByKey(CorporationBankInfo bankInfo);
	
	//根据企业主键批量变更企业银行信息状态值(伪删除)
	int updateCorBankInfoByCorPids(List<String> pids,String updateId);

	/**
	 * 根据企业表主键获取企业银行信息
	 * @param receiverId
	 * @return
	 */
	List<Map<String, Object>> getSimpleCorpBankInfoByCorpId(String corpid);

	/**
	 * 根据企业id及银行id查询银行账户
	 * @param corpId
	 * @param bankId
	 * @return
	 */
	List<String> getInvestSubjectBankAccount(String corpId, String bankId);
}
