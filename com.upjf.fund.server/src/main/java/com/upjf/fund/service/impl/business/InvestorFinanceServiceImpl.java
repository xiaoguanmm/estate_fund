package com.upjf.fund.service.impl.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.InvestSubjectFinanceVo;
import com.upjf.fund.service.business.InvestorFinanceService;
import com.upjf.fund.service.impl.business.mapper.CorporationInfoMapper;
import com.upjf.fund.service.impl.business.mapper.InvestSubjectAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.InvestorFinanceMapper;
import com.upjf.fund.service.impl.business.mapper.PaymentAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.ReceivedAccessoryMapper;
import com.upjf.fund.utils.PageUtils;


/**
 * 投资人财务管理服务接口实现类
 * @author lixq
 * @date   2018年10月17日
 */
@Service("investorFinanceService")
public class InvestorFinanceServiceImpl implements InvestorFinanceService {
	
	
	@Autowired
	private CorporationInfoMapper corporationInfoMapper ;							//企业信息接口
	
	@Autowired
	private InvestSubjectAccessoryMapper investSubjectAccessoryMapper;				//投资主体(投资人)附件接口
	
	@Autowired
	private PaymentAccessoryMapper paymentAccessoryMapper;							//付款附件接口
	
	@Autowired
	private ReceivedAccessoryMapper receivedAccessoryMapper;						//回款附件接口

	@Autowired
	private InvestorFinanceMapper investorFinanceMapper;							//投资人财务信息接口
	
	
	
	//分页获取投资人财务信息
	@Override
	public Map<String, Object> getInvestorFinanceListByPage(InvestSubjectFinanceVo investSubjectFinanceVo, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();				//封装查询条件
		Map<String,Object> resualtMap = null;										//封装结果信息
		
		PageUtils.toTrimPageFields(page);											//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;								//起始查询索引
		
		
		if(investSubjectFinanceVo != null){
			queryParams.put("investorPid", investSubjectFinanceVo.getInvestorPid());
			queryParams.put("investSubjectId", investSubjectFinanceVo.getInvestSubjectId());
			queryParams.put("projectName", investSubjectFinanceVo.getProjectName());
		}
	
		queryParams.put("startIndex", startIndex);
		queryParams.put("pageSize", pageSize);
		
		List<Map<String,Object>> investorFinanceList = investorFinanceMapper.getInvestorFinanceListByPage(queryParams);
		
		int totalCount = investorFinanceMapper.getTotalCount(queryParams);
		page.setTotalCount(totalCount);												//赋值最新相关页码信息
		
		resualtMap = new HashMap<String, Object>();
		resualtMap.put("investorFinanceList", investorFinanceList);
		resualtMap.put("page", page);
		
		return resualtMap;
	}
	
	
	
	//根据投资人主键获取投资人相关的详细信息
	@Override
	public Map<String, Object> getInvestorFinanceDetail(String investorPid) throws Exception {
		CorporationInfo corporationInfo = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(investorPid)){
			//根据投资人主键,获取投资人基础信息,投资主体附件均做异步加载
			Map<String, Object> detailMap = investorFinanceMapper.getInvestorFinanceDetail(investorPid);
			
			//根据获取到的关联企业主键,获取企业基本信息,企业资料及银行信息,付款信息,回款信息均做异步加载
			if(detailMap != null && detailMap.size() > 0){
				String corporationPid = (String)detailMap.get("investor_corPid"); 
				corporationInfo = corporationInfoMapper.getCorporationByKey(corporationPid);
			}
			
			resultMap.put("investor", detailMap);
			resultMap.put("corporationInfo", corporationInfo);
		}
		return resultMap;
	}
	
	
	
	
	
	
	//分页加载投资人付款信息
	@Override
	public Map<String, Object> getInvestorFinanceDetailListByPage(String investorPid, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();				//封装查询条件
		Map<String,Object> resualtMap = new HashMap<String, Object>();				//封装结果信息
		
		PageUtils.toTrimPageFields(page);											//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;								//起始查询索引
		
		
		if(StringUtils.isNotBlank(investorPid)){
			queryParams.put("investorPid", investorPid);
			queryParams.put("startIndex", startIndex);
			queryParams.put("pageSize", pageSize);
			List<Map<String,Object>> investorFinanceDetailList = investorFinanceMapper.getInvestorFinanceDetailListByPage(queryParams);
			Map<String, Object> prePayAndActualPayAmount = investorFinanceMapper.getAmountOfPrePayAndActualPay(queryParams);
			
			int totalCount = investorFinanceMapper.getInvestorFinanceDetailTotalCount(queryParams);
			page.setTotalCount(totalCount);												//赋值最新相关页码信息
			
			resualtMap.put("investorFinanceDetailList", investorFinanceDetailList);
			resualtMap.put("PrePayAndActualPayAmount", prePayAndActualPayAmount);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}
	
	
	//分页加载投资人回款信息
	@Override
	public Map<String, Object> getInvestorReceiveDetailListByPage(String investorPid, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();				//封装查询条件
		Map<String,Object> resualtMap = new HashMap<String, Object>();				//封装结果信息
		
		PageUtils.toTrimPageFields(page);											//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;								//起始查询索引
		
		
		if(StringUtils.isNotBlank(investorPid)){
			queryParams.put("investorPid", investorPid);
			queryParams.put("startIndex", startIndex);
			queryParams.put("pageSize", pageSize);
			List<Map<String,Object>> investorReceiveDetailList = investorFinanceMapper.getInvestorReceiveDetailListByPage(queryParams);
			Map<String, Object> receiverAmountAndProfit = investorFinanceMapper.getReceiverAmountAndProfit(queryParams);
			
			int totalCount = investorFinanceMapper.getInvestorFinanceDetailTotalCount(queryParams);
			page.setTotalCount(totalCount);												//赋值最新相关页码信息
			
			resualtMap.put("investorReceiveDetailList", investorReceiveDetailList);
			resualtMap.put("receiverAmountAndProfit", receiverAmountAndProfit);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}



}
