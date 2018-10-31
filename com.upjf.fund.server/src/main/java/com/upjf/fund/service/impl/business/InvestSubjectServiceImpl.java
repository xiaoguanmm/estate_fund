package com.upjf.fund.service.impl.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.InvestSubject;
import com.upjf.fund.dto.InvestSubjectAccessory;
import com.upjf.fund.service.business.InvestSubjectService;
import com.upjf.fund.service.impl.business.mapper.InvestSubjectAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.InvestSubjectMapper;
import com.upjf.fund.utils.UuidGenerator;


@Service("investSubjectService")
public class InvestSubjectServiceImpl implements InvestSubjectService {
	
	@Autowired
	private InvestSubjectMapper investSubjectMapper;
	
	@Autowired
	private InvestSubjectAccessoryMapper investSubjectAccessoryMapper;

	@Override
	@Transactional
	public int insertInvestSubject(InvestSubject investSubject) {
		if(investSubject.getExpectContributiveAmount()!=null) {
			investSubject.setExpectContributiveAmount(investSubject.getExpectContributiveAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investSubject.getExpectAllReceiverAccount()!=null) {
			investSubject.setExpectAllReceiverAccount(investSubject.getExpectAllReceiverAccount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investSubject.getExpectIncome()!=null) {
			investSubject.setExpectIncome(investSubject.getExpectIncome().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		return this.investSubjectMapper.insertInvestSubject(investSubject);
	}

	@Override
	public InvestSubject getInvestSubjectByPrimaryKey(String investSubjectPid) {
		return this.investSubjectMapper.getInvestSubjectByPrimaryKey(investSubjectPid);
	}

	@Override
	public InvestSubject getInvestSubjectByInvestPlanPidAndPid(String investPlanManagePid, String investSubjectName) {
		return this.investSubjectMapper.getInvestSubjectByInvestPlanPidAndPid(investPlanManagePid,investSubjectName);
	}

	@Override
	@Transactional
	public int updateInvestSubjectByPrimaryKey(InvestSubject investSubject) {
		if(investSubject.getExpectContributiveAmount()!=null) {
			investSubject.setExpectContributiveAmount(investSubject.getExpectContributiveAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investSubject.getExpectAllReceiverAccount()!=null) {
			investSubject.setExpectAllReceiverAccount(investSubject.getExpectAllReceiverAccount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investSubject.getExpectIncome()!=null) {
			investSubject.setExpectIncome(investSubject.getExpectIncome().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		return this.investSubjectMapper.updateInvestSubjectByPrimaryKey(investSubject);
	}

	@Override
	@Transactional
	public int batchInsertInvestSubjectAccessory(String accessoryType,List<EstateFundFile> files,String investSubjectPid, String loginUserId) {
		List<InvestSubjectAccessory> accessoryList = new ArrayList<InvestSubjectAccessory>();
		for(EstateFundFile file: files){
			InvestSubjectAccessory investSubjectAccessory = new InvestSubjectAccessory();
			investSubjectAccessory.setPid(UuidGenerator.getUuidGenerator());
			investSubjectAccessory.setSubjectId(investSubjectPid);
			investSubjectAccessory.setFileId(file.getPid());
			// 附件类型(1为投资主体附件，2为投资人附件)
			if(StringUtils.isNotBlank(accessoryType)) {
				investSubjectAccessory.setInvestAccessoryType(accessoryType);
			}
			investSubjectAccessory.setCreateDate(new Date());
			investSubjectAccessory.setCreateId(loginUserId);
			accessoryList.add(investSubjectAccessory);
		}
		return this.investSubjectAccessoryMapper.batchInsertInvestSubjectAccessory(accessoryList);
	}

	@Override
	public List<Map<String, Object>> getInvestSubjectAccessoryByInvestSubjectId(String investSubjectPid, Integer offset, Integer pageRows) {
		return this.investSubjectAccessoryMapper.getInvestSubjectAccessoryByInvestSubjectId(investSubjectPid,offset,pageRows);
	}

	@Override
	public Integer countInvestSubjectAccessoryByInvestSubjectId(String investSubjectPid) {
		return this.investSubjectAccessoryMapper.countInvestSubjectAccessoryByInvestSubjectId(investSubjectPid);
	}

	@Override
	public List<Map<String, Object>> queryInvestorBusinessManageList(Map<String, String> condtions, Integer offset, Integer pageRows) {
		return this.investSubjectMapper.queryInvestorBusinessManageList(condtions,offset,pageRows);
	}

	@Override
	public Integer countInvestorBusinessManageList(Map<String, String> condtions) {
		return this.investSubjectMapper.countInvestorBusinessManageList(condtions);
	}

	@Override
	public Map<String, Object> queryInvestSubjectAndPrjInfo(String investPlanManagePid, String investSubjectPid) {
		return this.investSubjectMapper.queryInvestSubjectAndPrjInfo(investPlanManagePid,investSubjectPid);
	}

	@Override
	public InvestSubject getInvestorByInvestPlanPidAndInvestPid(String investPlanManagePid, String investSubjectPid,String investorCorPid) {
		return this.investSubjectMapper.getInvestorByInvestPlanPidAndInvestPid(investPlanManagePid,investSubjectPid,investorCorPid);
	}

	@Override
	@Transactional
	public int deleteInvestSubjectByPids(List<String> pids, String curPid, String investType) {
		int result = 0;
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		Date updateDate = new Date();
		if(pids != null && pids.size() > 0){
			paramsMap.put("pids", pids);
			paramsMap.put("status", 0);
			paramsMap.put("investType", investType);
			paramsMap.put("updateDate", updateDate);
			
			if(StringUtils.isNotBlank(curPid)){
				paramsMap.put("updateId", curPid);
			}
			
			result = this.investSubjectMapper.deleteInvestSubjectByPids(paramsMap);			//伪删除投资主体表
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryInvestSubjectBusinessManageList(Map<String, String> conditions, Integer offset, Integer pageRows) {
		return this.investSubjectMapper.queryInvestSubjectBusinessManageList(conditions,offset,pageRows);
	}

	@Override
	public Integer countInvestSubjectBusinessManageList(Map<String, String> conditions) {
		return this.investSubjectMapper.countInvestSubjectBusinessManageList(conditions);
	}

	
	
	
	
	


}
