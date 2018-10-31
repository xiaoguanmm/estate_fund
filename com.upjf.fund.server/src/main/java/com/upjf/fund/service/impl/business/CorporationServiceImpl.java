package com.upjf.fund.service.impl.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.base.Page;
import com.upjf.fund.constants.TagConstants;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.impl.business.mapper.CorporationBankInfoMapper;
import com.upjf.fund.service.impl.business.mapper.CorporationInfoMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 企业信息服务层接口实现类
 * @author lixq
 * @date 2018年9月19日
 */
@Service("corporationService")
public class CorporationServiceImpl implements CorporationService {
	
	private static Logger log = LoggerFactory.getLogger(CorporationServiceImpl.class);
	
	@Autowired
	private CorporationInfoMapper corporationInfoMapper;						//企业信息接口
	
	
	@Autowired
	private CorporationBankInfoMapper corporationBankInfoMapper;			    //企业银行接口
	
	
	
	
	//根据条件分页查询获取企业信息
	@Override
	public Map<String, Object> getCorporationListByPage(CorporationInfo corInfo,Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();			//封装查询条件
		Map<String,Object> resualtMap = new HashMap<String, Object>();			//封装结果信息
		
		toTrimCorporationFields(corInfo);										//企业检索条件去空格赋值
		PageUtils.toTrimPageFields(page);										//页码信息去空格赋值
		
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;							//起始查询索引
		
		if(corInfo != null ){
			queryParams.put("name", corInfo.getName());
			queryParams.put("simpleName", corInfo.getSimpleName());
			queryParams.put("orgCodeCert", corInfo.getOrgCodeCert());
			queryParams.put("businessLicenceCode", corInfo.getBusinessLicenceCode());
			queryParams.put("customerManager", corInfo.getCustomerManager());
		}
		queryParams.put("startIndex", startIndex);
		queryParams.put("pageSize", pageSize);
		queryParams.put("status", 1);
		
		List<CorporationInfo> corporationList = corporationInfoMapper.getCorporationListByPage(queryParams);
		int totalCount = corporationInfoMapper.getTotalCount(queryParams);
		page.setTotalCount(totalCount);											//赋值最新相关页码信息
		
		
		resualtMap.put("corList", corporationList);
		resualtMap.put("page", page);
		
		return resualtMap;
	}
	
	//根据主键修改企业基本信息
	@Override
	@Transactional
	public int updateCorInfoByKey(CorporationInfo corInfo) throws Exception {
		int result = 0;
		if(corInfo != null){
			CorporationInfo corporationInfo = corporationInfoMapper.getCorByPrimaryKey(corInfo.getPid());
			if(corporationInfo.getOrgCodeCert().equals(corInfo.getOrgCodeCert())){
				result = corporationInfoMapper.updateCorInfoByKey(corInfo);
			}else{
				CorporationInfo corExistInfo = getCorByOrgCodeCert(corInfo.getOrgCodeCert());
				if(corExistInfo == null){
					result = corporationInfoMapper.updateCorInfoByKey(corInfo);
				}else{
					result = TagConstants.EXIST_CODE;
				}
			}
		}
		return result;
	}


	//批量删除企业信息
	@Override
	@Transactional
	public int delCorporationByPids(List<String> pids,String updateId) throws Exception {
		int resualt = 0;
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		Date updateDate = new Date();
		if(pids != null && pids.size() > 0){
			paramsMap.put("pids", pids);
			paramsMap.put("status", 0);
			paramsMap.put("updateDate", updateDate);
			
			if(StringUtils.isNotBlank(updateId)){
				paramsMap.put("updateId", updateId);
			}
			
			resualt = corporationInfoMapper.delCorporationByPids(paramsMap);			//伪删除企业基本新
			if(resualt > 0){
				corporationBankInfoMapper.updateCorBankInfoByCorPids(paramsMap);			//伪删除企业银行账户信息
			}
		}
		return resualt;
	}
	
	
	
	//保存企业基础信息
	@Override
	@Transactional
	public Map<String,Object> insertCorporation(CorporationInfo corInfo) throws Exception{
		Map<String,Object> resualtMap = null;
		
		if(corInfo != null){
			
			//转换单位
			BigDecimal unit = new BigDecimal("10000");
			BigDecimal registerCapital = corInfo.getRegisterCapital();
			if(registerCapital != null){
				corInfo.setRegisterCapital(registerCapital.multiply(unit));
			}
			
			
			String orgCodeCert = corInfo.getOrgCodeCert();
			CorporationInfo corporationInfo = corporationInfoMapper.getCorByPrimaryKey(corInfo.getPid());
			if(corporationInfo != null){//修改
				if(corporationInfo.getOrgCodeCert().equals(corInfo.getOrgCodeCert())){
					int result = corporationInfoMapper.updateCorInfoByKey(corInfo);
					if(result >0){
						resualtMap = new HashMap<String, Object>();
						resualtMap.put("success", "s");
						resualtMap.put("msg", "保存成功!");
						resualtMap.put("newPid", corInfo.getPid());
					}else{
						resualtMap = new HashMap<String, Object>();
						resualtMap.put("success", "e");
						resualtMap.put("msg", "保存失败!");
					}
				}else{
					CorporationInfo corExistInfo = getCorByOrgCodeCert(orgCodeCert);
					if(corExistInfo == null){
						int result = corporationInfoMapper.updateCorInfoByKey(corInfo);
						if(result >0){
							resualtMap = new HashMap<String, Object>();
							resualtMap.put("success", "s");
							resualtMap.put("msg", "保存成功!");
							resualtMap.put("newPid", corInfo.getPid());
						}else{
							resualtMap = new HashMap<String, Object>();
							resualtMap.put("success", "e");
							resualtMap.put("msg", "保存失败!");
						}
					}else{
						resualtMap = new HashMap<String, Object>();
						resualtMap.put("success", "e");
						resualtMap.put("msg", "企业已存在!");
					}
				}
			}else{//新增
				CorporationInfo corExistInfo = getCorByOrgCodeCert(orgCodeCert);
				if(corExistInfo == null){
					String uuid = UuidGenerator.getUuidGenerator();			//生成随机主键
					corInfo.setPid(uuid);
					corporationInfoMapper.insertCorporation(corInfo);
					
					resualtMap = new HashMap<String, Object>();
					resualtMap.put("success", "s");
					resualtMap.put("msg", "保存成功!");
					resualtMap.put("newPid", uuid);
				}else{
					resualtMap = new HashMap<String, Object>();
					resualtMap.put("success", "e");
					resualtMap.put("msg", "企业已存在!");
				}
			}
		}else{
			log.info("企业基础信息实体为空!");
			resualtMap = new HashMap<String, Object>();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "不能提交空信息!");
		}
		
		return resualtMap;
	}
	
	
	
	//根据组织机构代码证号查询获取对应的企业信息
	@Override
	public CorporationInfo getCorByOrgCodeCert(String orgCodeCert) throws Exception{
		CorporationInfo corInfo = null;
		if(StringUtils.isNotBlank(orgCodeCert)){
			corInfo = corporationInfoMapper.getCorByOrgCodeCert(orgCodeCert);
		}
		return corInfo;
	}

	
	//根据企业主键,查询 获取企业信息
	@Override
	public CorporationInfo getCorByPrimaryKey(String pid) throws Exception{
		CorporationInfo corInfo = null;
		if(StringUtils.isNotBlank(pid)){
			corInfo = corporationInfoMapper.getCorByPrimaryKey(pid);
		}
		return corInfo;
	}

	
	
	//根据状态获取所有该状态的所有企业信息 
	@Override
	public List<CorporationInfo> getAllCorInfoList(Integer status) throws Exception {
		List<CorporationInfo> list = null;
		if(status != null){
			list = corporationInfoMapper.getAllCorInfoList(status);
		}
		return list;
	}

	/**
	 * 企业信息检索条件去空格赋值
	 * @param corInfo
	 * @return
	 */
	private void toTrimCorporationFields(CorporationInfo corInfo){
		if(corInfo != null){
			String corporationName = corInfo.getName();							//企业名称
			String orgCodeCert = corInfo.getOrgCodeCert();						//组织机构代码
			String businessLicenceCode = corInfo.getBusinessLicenceCode();  	//营业执照号码
			String customerManager = corInfo.getCustomerManager();              //客户经理
			
			if(StringUtils.isNotBlank(corporationName)){
				corporationName = corporationName.trim();
				corInfo.setName(corporationName);
			}
			if(StringUtils.isNotBlank(orgCodeCert)){
				orgCodeCert = orgCodeCert.trim();
				corInfo.setOrgCodeCert(orgCodeCert);
			}
			if(StringUtils.isNotBlank(businessLicenceCode)){
				businessLicenceCode = businessLicenceCode.trim();
				corInfo.setBusinessLicenceCode(businessLicenceCode);
			}
			if(StringUtils.isNotBlank(customerManager)){
				customerManager = customerManager.trim();
				corInfo.setCustomerManager(customerManager);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getProjectStockholderCorp(String prjId) {
		
		return this.corporationInfoMapper.getProjectStockholderCorp(prjId);
	}

	@Override
	public List<Map<String, Object>> getInvestSubjectCorp(String prjId, String corpId,String investType) {
		
		return this.corporationInfoMapper.getInvestSubjectCorp(prjId,corpId,investType);
	}

	@Override
	public List<Map<String, Object>> getInvestSubjectCorpByPrjId(String prjId, String investType) {
		
		return this.corporationInfoMapper.getInvestSubjectCorpByPrjId(prjId, investType);
	}

	@Override
	public List<Map<String, Object>> getInvestorCorpByInvestSubjectPid(String parentId, String investType) {
		
		return this.corporationInfoMapper.getInvestorCorpByInvestSubjectPid(parentId, investType);
	}

	@Override
	public List<Map<String, Object>> getInvestSubjectCorpByStockCorpId(String stockholderPid) {
		return this.corporationInfoMapper.getInvestSubjectCorpByStockCorpId(stockholderPid);
	}

	@Override
	public List<Map<String, Object>> getProjectStockholder(String prjId) {
		
		return this.corporationInfoMapper.getProjectStockholder(prjId);
	}
	
	
	
}
