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
import com.upjf.fund.service.business.InvestSubjectFinanceService;
import com.upjf.fund.service.impl.business.mapper.CorporationInfoMapper;
import com.upjf.fund.service.impl.business.mapper.InvestSubjectAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.InvestSubjectFinanceMapper;
import com.upjf.fund.service.impl.business.mapper.PaymentAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.ReceivedAccessoryMapper;
import com.upjf.fund.utils.PageUtils;


/**
 * 投资主体财务管理服务接口实现类
 * @author lixq
 * @date   2018年10月17日
 */
@Service("investSubjectFinanceService")
public class InvestSubjectFinanceServiceImpl implements InvestSubjectFinanceService {
	
	
	@Autowired
	private InvestSubjectFinanceMapper investSubjectFinanceMapper;					//投资主体财务管理接口
	
	@Autowired
	private CorporationInfoMapper corporationInfoMapper ;							//企业信息接口
	
	@Autowired
	private InvestSubjectAccessoryMapper investSubjectAccessoryMapper;				//投资主体(投资人)附件接口
	
	@Autowired
	private PaymentAccessoryMapper paymentAccessoryMapper;							//付款附件接口
	
	@Autowired
	private ReceivedAccessoryMapper receivedAccessoryMapper;						//回款附件接口
	
	
	
	
	//分页获取投资主体财务信息
	@Override
	public Map<String, Object> getInvestSubjectFinanceListByPage(InvestSubjectFinanceVo investSubjectFinanceVo, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();				//封装查询条件
		Map<String,Object> resualtMap = null;										//封装结果信息
		
		PageUtils.toTrimPageFields(page);											//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;								//起始查询索引
		
		
		if(investSubjectFinanceVo != null){
			queryParams.put("contributiveType", investSubjectFinanceVo.getContributiveType());
			queryParams.put("investSubjectId", investSubjectFinanceVo.getInvestSubjectId());
			queryParams.put("projectName", investSubjectFinanceVo.getProjectName());
		}
	
		queryParams.put("startIndex", startIndex);
		queryParams.put("pageSize", pageSize);
		
		List<InvestSubjectFinanceVo> subjectFinanceList = investSubjectFinanceMapper.getInvestSubjectFinanceListByPage(queryParams);
		
		int totalCount = investSubjectFinanceMapper.getTotalCount(queryParams);
		page.setTotalCount(totalCount);												//赋值最新相关页码信息
		
		resualtMap = new HashMap<String, Object>();
		resualtMap.put("subjectFinanceList", subjectFinanceList);
		resualtMap.put("page", page);
		
		return resualtMap;
	}


	//根据投资主体主键及项目主键获取投资主体相关的详细信息
	@Override
	public Map<String, Object> getSubjectFinanceDetail(String subjectPid,String projectPid) throws Exception {
		InvestSubjectFinanceVo investSubject = null;
		CorporationInfo corporationInfo = null;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(subjectPid) && StringUtils.isNotBlank(projectPid)){
			//根据投资主体主键,获取投资主体基础信息,投资主体附件均做异步加载
			paramsMap.put("projectPid", projectPid);
			paramsMap.put("subjectPid", subjectPid);
			investSubject = investSubjectFinanceMapper.getInvestSubjectByPidAndProjectPid(paramsMap);
			
			//根据获取到的关联企业主键,获取企业基本信息,企业资料及银行信息,付款信息,回款信息均做异步加载
			if(investSubject != null){
				String corporationPid = investSubject.getInvestSubjectId();
				corporationInfo = corporationInfoMapper.getCorporationByKey(corporationPid);
			}
			
			resultMap.put("investSubject", investSubject);
			resultMap.put("corporationInfo", corporationInfo);
		}
		return resultMap;
	}

	
	
	
	//分页获取投资主体(投资人)的附件列表
	@Override
	public Map<String, Object> getSubjectFileListByPage(String subjectPid, Page page) throws Exception {
		Map<String,Object> resualtMap = null;										//封装结果信息
		
		PageUtils.toTrimPageFields(page);											//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;								//起始查询索引
		
		if(StringUtils.isNotBlank(subjectPid)){
			List<Map<String,Object>> subjectFileList = investSubjectAccessoryMapper.getInvestSubjectAccessoryByInvestSubjectId(subjectPid, startIndex, pageSize);
			Integer totalCount = investSubjectAccessoryMapper.countInvestSubjectAccessoryByInvestSubjectId(subjectPid);
			page.setTotalCount(totalCount);											//赋值最新相关页码信息
			
			resualtMap = new HashMap<String, Object>();
			resualtMap.put("subjectFileList", subjectFileList);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}

	
	
	//分页加载投资主体付款信息
	@Override
	public Map<String, Object> getSubjectFinanceDetailListByPage(String subjectPid, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();				//封装查询条件
		Map<String,Object> resualtMap = new HashMap<String, Object>();				//封装结果信息
		
		PageUtils.toTrimPageFields(page);											//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;								//起始查询索引
		
		
		if(StringUtils.isNotBlank(subjectPid)){
			queryParams.put("subjectPid", subjectPid);
			queryParams.put("startIndex", startIndex);
			queryParams.put("pageSize", pageSize);
			List<Map<String,Object>> subFinanceDetailList = investSubjectFinanceMapper.getSubjectFinanceDetailListByPage(queryParams);
			Map<String, Object> prePayAndActualPayAmount = investSubjectFinanceMapper.getAmountOfPrePayAndActualPay(subjectPid);
			
			int totalCount = investSubjectFinanceMapper.getSubjectFinanceDetailTotalCount(queryParams);
			page.setTotalCount(totalCount);												//赋值最新相关页码信息
			
			resualtMap.put("subFinanceDetailList", subFinanceDetailList);
			resualtMap.put("PrePayAndActualPayAmount", prePayAndActualPayAmount);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}

	
	
	//根据付款主键,获取当前该条件下对应的付款明细信息
	@Override
	public Map<String, Object> getSubjectPaymentDetailByPid(String subjectPayPid) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(subjectPayPid)){
			resultMap = investSubjectFinanceMapper.getSubjectPaymentDetailByPid(subjectPayPid);
		}
		return resultMap;
	}

	
	
	//分页加载投资主体单条付款对应的附件信息列表
	@Override
	public Map<String, Object> getSubjectPaymentDetailFileList(String subjectPayPid, Page page) throws Exception {
		Map<String,Object> resualtMap = new HashMap<String, Object>();					//封装结果信息
		PageUtils.toTrimPageFields(page);												//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;									//起始查询索引
		
		if(StringUtils.isNotBlank(subjectPayPid)){
			List<Map<String,Object>> subjectPaymentDetailFileList = paymentAccessoryMapper.getPaymentAccessoriesByPaymentId(subjectPayPid,startIndex,pageSize);
			
			int totalCount = paymentAccessoryMapper.countPaymentAccessoriesByPaymentId(subjectPayPid);
			page.setTotalCount(totalCount);												//赋值最新相关页码信息
			
			resualtMap.put("subjectPaymentDetailFileList", subjectPaymentDetailFileList);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}

	
	
	//分页加载投资主体回款信息
	@Override
	public Map<String, Object> getSubjectReceiveDetailListByPage(String subjectPid, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();				//封装查询条件
		Map<String,Object> resualtMap = new HashMap<String, Object>();				//封装结果信息
		
		PageUtils.toTrimPageFields(page);											//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;								//起始查询索引
		
		
		if(StringUtils.isNotBlank(subjectPid)){
			queryParams.put("subjectPid", subjectPid);
			queryParams.put("startIndex", startIndex);
			queryParams.put("pageSize", pageSize);
			List<Map<String,Object>> subjectReceiveDetailList = investSubjectFinanceMapper.getSubjectReceiveDetailListByPage(queryParams);
			Map<String, Object> receiverAmountAndProfit = investSubjectFinanceMapper.getReceiverAmountAndProfit(subjectPid);
			
			int totalCount = investSubjectFinanceMapper.getSubjectReceiveDetailListTotalCount(queryParams);
			page.setTotalCount(totalCount);												//赋值最新相关页码信息
			
			resualtMap.put("subjectReceiveDetailList", subjectReceiveDetailList);
			resualtMap.put("receiverAmountAndProfit", receiverAmountAndProfit);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}


	
	//根据投资主体回款信息主键,获取该条主键下的回款明细
	@Override
	public Map<String, Object> getSubjectReturnDetailByPid(String subjectReturnPid) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(subjectReturnPid)){
			resultMap = investSubjectFinanceMapper.getSubjectReturnDetailByPid(subjectReturnPid);
		}
		return resultMap;
	}

	
	
	//分页加载投资主体单条付款对应的附件信息列表
	@Override
	public Map<String, Object> getSubjectReturnDetailFileList(String subjectReturnPid, Page page) throws Exception {
		Map<String,Object> resualtMap = new HashMap<String, Object>();					//封装结果信息
		PageUtils.toTrimPageFields(page);												//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;									//起始查询索引
		
		if(StringUtils.isNotBlank(subjectReturnPid)){
			List<Map<String,Object>> subjectReturnDetailFileList = receivedAccessoryMapper.getPaybackAccessoriesByPaybackId(subjectReturnPid,startIndex,pageSize);
			
			int totalCount = receivedAccessoryMapper.countPaybackAccessoriesByPaybackId(subjectReturnPid);
			page.setTotalCount(totalCount);												//赋值最新相关页码信息
			
			resualtMap.put("subjectReturnDetailFileList", subjectReturnDetailFileList);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
		
	}
	
	
	
	
	
	


}
