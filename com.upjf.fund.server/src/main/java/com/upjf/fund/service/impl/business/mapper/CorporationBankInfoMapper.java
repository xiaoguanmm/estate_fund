package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.CorporationBankInfo;

/**
 * 企业银行信息相关操作mapper
 * @author lixq
 * @date 2018年9月22日
 */
public interface CorporationBankInfoMapper {
	
    //根据主键删除企业银行信息
    int deleteByPrimaryKey(@Param("pid") String pid);

    //根据主键查询获取企业银行信息
    CorporationBankInfo getCorporationBankByKey(@Param("pid") String pid);
    
    //根据主键更新企业银行信息
    int updateCorporationBankByKey(CorporationBankInfo bankInfo);
    
    //保存企业银行信息
    void insertCorporationBank(CorporationBankInfo bankInfo);
    
    //根据条件分页查询获取企业信息
    List<CorporationBankInfo> getCorporationBankListByPage(Map<String,Object> queryParams);
    
    //根据条件,查询获取命中企业银行数量
    int getTotalCount(Map<String,Object> queryParams);
    
    //根据企业主键批量变更企业银行信息状态值(伪删除)
  	int updateCorBankInfoByCorPids(Map<String,Object> paramsMap);
  	
  	
  	//根据企业主键查询获取该企业银行信息
  	List<Map<String,Object>> getBankInfoByComPanyPid(String companyPid);
  	
  	//根据银行主键及企业主键,查询获取有效状态的企业银行账号信息
  	List<Map<String,Object>> getBankInfoByComPanyPidAndBankId(@Param("companyPid") String companyPid,@Param("bankId") String bankId);
  	
  	/**
  	 * 根据企业表主键获取企业银行信息
  	 * @param corpid
  	 * @return
  	 */
	List<Map<String, Object>> getSimpleCorpBankInfoByCorpId(@Param("corpId") String corpId);

	/**
	 * 根据corpId 及 bankId 查询银行账户
	 * @param corpId
	 * @param bankId
	 * @return
	 */
	List<String> getInvestSubjectBankAccount(@Param("corpId") String corpId, @Param("bankId") String bankId);

}