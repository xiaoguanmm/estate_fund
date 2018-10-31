package com.upjf.fund.service.impl.business;

import java.util.ArrayList;
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
import com.upjf.fund.dto.CorporationBankInfo;
import com.upjf.fund.service.business.CorporationBankService;
import com.upjf.fund.service.impl.business.mapper.CorporationBankInfoMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 企业银行相关服务接口实现类
 * @author lixq
 * @date 2018年9月19日
 */
@Service("corporationBankService")
public class CorporationBankServiceImpl implements CorporationBankService {
	
	private static Logger log = LoggerFactory.getLogger(CorporationBankServiceImpl.class);
	
	@Autowired
	private CorporationBankInfoMapper corporationBankInfoMapper;			//企业银行接口
	
	
	
	//根据条件分页查询获取企业银行相关信息
	@Override
	public Map<String, Object> getCorporationBankListByPage(CorporationBankInfo bankInfo, Page page) {
		Map<String,Object> queryParams = new HashMap<String, Object>();			//封装查询条件
		Map<String,Object> resualtMap = new HashMap<String, Object>();			//封装结果信息
		try {
			
			PageUtils.toTrimPageFields(page);										//页码信息去空格赋值
			
			int curPage = Integer.parseInt(page.getCurPage());
			int pageSize  = Integer.parseInt(page.getPageSize());
			Integer startIndex = (curPage - 1) * pageSize;							//起始查询索引
			
			
			if(bankInfo != null){													
				queryParams.put("corpId", bankInfo.getCorpId());
				queryParams.put("accountName", bankInfo.getAccountName());
				queryParams.put("bankId", bankInfo.getBankId());
				queryParams.put("account", bankInfo.getAccount());
				queryParams.put("remark", bankInfo.getRemark());
				queryParams.put("createId", bankInfo.getCreateId());
				queryParams.put("updateId", bankInfo.getUpdateId());
			}
			queryParams.put("startIndex", startIndex);
			queryParams.put("pageSize", pageSize);
			queryParams.put("status", 1);
			
			List<CorporationBankInfo> corBanklist = corporationBankInfoMapper.getCorporationBankListByPage(queryParams);
			int totalCount = corporationBankInfoMapper.getTotalCount(queryParams);
			page.setTotalCount(totalCount);											//赋值最新相关页码信息
			
			if(corBanklist == null || corBanklist.size() <= 0){
				corBanklist = new ArrayList<CorporationBankInfo>();
			}
			
			resualtMap.put("corBankList", corBanklist);
			resualtMap.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页查询获取企业银行信息失败!");
		}
		
		return resualtMap;
	}
	
	
	//根据主键删除企业银行信息
	@Override
	@Transactional
	public int delCorporationBankByKey(String pid) {
		int result = -1;
		try {
			result = corporationBankInfoMapper.deleteByPrimaryKey(pid);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除企业银行信息失败,系统出错!");
		}
		return result;
	}
	
	
	//保存企业银行信息
	@Override
	@Transactional
	public Map<String, Object> insertCorporationBank(CorporationBankInfo bankInfo) {
		Map<String,Object>  resualtMap = new HashMap<String, Object>();
		try {
			if(bankInfo != null){
				String uuid = UuidGenerator.getUuidGenerator();
				bankInfo.setPid(uuid);
				corporationBankInfoMapper.insertCorporationBank(bankInfo);
				
				resualtMap.put("success", "s");
				resualtMap.put("msg", "保存成功!");
				resualtMap.put("newPid", uuid);
				
			}else{
				resualtMap.put("success", "e");
				resualtMap.put("msg", "信息不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存企业银行信息失败,系统出错!");
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错,请联系管理员!");
			resualtMap.remove("newPid");
		}
		return resualtMap;
	}
	
	//根据企业银行主键,查询 获取企业银行信息
	@Override
	public CorporationBankInfo getCorporationBankByKey(String pid) {
		CorporationBankInfo corporationBankInfo = null;
		try {
			corporationBankInfo = corporationBankInfoMapper.getCorporationBankByKey(pid);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
		}
		return corporationBankInfo;
	}
	
	//根据主键修改企业银行信息
	@Override
	@Transactional
	public int updateCorporationBankByKey(CorporationBankInfo bankInfo) {
		int result = 0;
		try {
			if(bankInfo != null){
				result = corporationBankInfoMapper.updateCorporationBankByKey(bankInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据主键更新企业银行信息失败,系统出错!");
		}
		return result;
	}

	
	//根据企业主键批量变更企业银行信息状态值(伪删除)
	@Override
	@Transactional
	public int updateCorBankInfoByCorPids(List<String> pids,String updateId) {
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		int result = -1;
		try {
			if(pids != null && pids.size() > 0){
				paramsMap.put("pids",pids);
				paramsMap.put("updateDate",new Date());
				
				if(StringUtils.isNotBlank(updateId)){
					paramsMap.put("updateId",updateId);
				}
				result = corporationBankInfoMapper.updateCorBankInfoByCorPids(paramsMap);
			}else{
				log.info("根据企业主键批量变更企业银行信息状态值时,传入企业主键集合为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据企业主键批量变更企业银行信息状态值失败,系统出错!");
		}
		return result;
	}


	@Override
	public List<Map<String, Object>> getSimpleCorpBankInfoByCorpId(String corpid) {
		
		return this.corporationBankInfoMapper.getSimpleCorpBankInfoByCorpId(corpid);
	}


	@Override
	public List<String> getInvestSubjectBankAccount(String corpId, String bankId) {
		
		return this.corporationBankInfoMapper.getInvestSubjectBankAccount(corpId,bankId);
	}
	
}
