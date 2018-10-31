package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.CorporationInfo;

/**
 * 企业基本信息mapper
 * @author lixq
 * @date 2018年9月19日
 */
public interface CorporationInfoMapper {
    
	//根据主键物理删除企业基本信息
    int deleteByPrimaryKey(@Param("pid") String pid);
    
    //根据主键查询获取有效状态的企业信息
    CorporationInfo getCorByPrimaryKey(@Param("pid") String pid);
    
    //根据主键,关联字典查询获取企业信息
    CorporationInfo getCorporationByKey(@Param("pid") String pid);
    
    //根据主键更新企业基本信息
    int updateCorInfoByKey(CorporationInfo corInfo);
    
    //保存企业基础信息
    void insertCorporation(CorporationInfo corInfo);
    
    //根据条件分页查询获取企业信息
    List<CorporationInfo> getCorporationListByPage(Map<String,Object> queryParams);
    
    //根据条件,查询获取命中数量
    int getTotalCount(Map<String,Object> queryParams);
    
    //批量伪删除企业信息(非物理删除)
    int delCorporationByPids(Map<String,Object> paramsMap);
    
    //根据组织机构代码证号查询获取对应的企业信息
  	CorporationInfo getCorByOrgCodeCert(@Param("orgCodeCert") String orgCodeCert);
  	
  	//根据状态获取所有该状态的所有企业信息 
  	List<CorporationInfo> getAllCorInfoList(@Param("status") Integer status);
  	
  	/**
  	 *  获取项目对应的股东公司
  	 * @param prjId
  	 * @return
  	 */
	List<Map<String, Object>> getProjectStockholderCorp(@Param("prjId")String prjId);

	/**
	 * 根据收款公司(项目股东)和项目公司加载出资主体(关联资管计划表)
	 * @param prjId
	 * @param corpId
	 * @param investType 
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectCorp(@Param("prjId")String prjId, @Param("corpId")String corpId, @Param("investType")String investType);

	/**
	 * 根据项目编号获取投资主体公司
	 * @param prjId
	 * @param investType 
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectCorpByPrjId(@Param("prjId")String prjId, @Param("investType")String investType);

	/**
	 * 根据投资主体pid查询投资人公司
	 * @param parentId
	 * @param investType
	 * @return
	 */
	List<Map<String, Object>> getInvestorCorpByInvestSubjectPid(@Param("parentId")String parentId, @Param("investType")String investType);
	
	/**
	 * 根据股东pid 加载投资主体公司
	 * @param stockholderPid
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectCorpByStockCorpId(@Param("stockholderPid") String stockholderPid);

	/**
	 * 查询项目对应股东信息
	 * @param prjId
	 * @return
	 */
	List<Map<String, Object>> getProjectStockholder(@Param("prjId")String prjId);
}