package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.CorporationInfo;

/**
 * 企业信息相关服务接口
 * @author lixq
 * @date 2018年9月19日
 */
public interface CorporationService {
	
	//根据条件分页查询获取企业信息
	Map<String, Object> getCorporationListByPage(CorporationInfo corInfo,Page page) throws Exception;
	
	
	//批量删除企业信息
	int delCorporationByPids(List<String> pids,String updateId) throws Exception;
	
	//保存企业基础信息
	Map<String,Object> insertCorporation(CorporationInfo corInfo) throws Exception;
	
	//根据组织机构代码证号查询获取对应的企业信息
	CorporationInfo getCorByOrgCodeCert(String orgCodeCert) throws Exception;

	//根据企业主键,查询 获取企业信息
	CorporationInfo getCorByPrimaryKey(String pid) throws Exception;
	
	//根据主键修改企业基本信息
	int updateCorInfoByKey(CorporationInfo corInfo) throws Exception;
	
	//根据状态获取所有该状态的所有企业信息 
  	List<CorporationInfo> getAllCorInfoList(Integer status) throws Exception;

  	/**
  	 * 获取项目对应的股东公司
  	 * @param prjId
  	 * @return
  	 */
	List<Map<String, Object>> getProjectStockholderCorp(String prjId);

	/**
	 * 根据收款公司(项目股东)和项目公司加载出资主体(关联资管计划表)
	 * @param prjId
	 * @param corpId
	 * @param investType 
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectCorp(String prjId, String corpId, String investType);


	/**
	 * 根据项目编号获取投资主体公司
	 * @param prjId
	 * @param investType 
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectCorpByPrjId(String prjId, String investType);


	/**
	 * 根据投资主体pid查询投资人公司
	 * @param parentId
	 * @param investType
	 * @return
	 */
	List<Map<String, Object>> getInvestorCorpByInvestSubjectPid(String parentId, String investType);

	/**
	 * 根据股东pid 加载投资主体公司
	 * @param stockholderPid
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectCorpByStockCorpId(String stockholderPid);


	/**
	 * 获取项目对应的股东
	 * @param prjId
	 * @return
	 */
	List<Map<String, Object>> getProjectStockholder(String prjId);

}
