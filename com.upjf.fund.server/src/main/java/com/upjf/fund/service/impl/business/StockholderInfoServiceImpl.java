package com.upjf.fund.service.impl.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.Payment;
import com.upjf.fund.dto.StockholderAccessory;
import com.upjf.fund.dto.StockholderInfo;
import com.upjf.fund.service.business.StockholderInfoService;
import com.upjf.fund.service.impl.business.mapper.BusinessPrjInfoMapper;
import com.upjf.fund.service.impl.business.mapper.CorporationBankInfoMapper;
import com.upjf.fund.service.impl.business.mapper.PaymentAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.PaymentMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectInfoMapper;
import com.upjf.fund.service.impl.business.mapper.StockholderAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.StockholderInfoMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;


/**
 * 股东相关操作服务接口实现类
 * @author lixq
 * @date   2018年10月22日
 */
@Service("stockholderInfoService")
public class StockholderInfoServiceImpl implements StockholderInfoService {
	
	@Autowired
	private StockholderInfoMapper stockholderInfoMapper;
	
	@Autowired
	private StockholderAccessoryMapper stockholderAccessoryMapper;
	
	@Autowired
	private PaymentMapper paymentMapper ;												//付款信息接口
	
	@Autowired
	private PaymentAccessoryMapper paymentAccessoryMapper;								//付款附件信息接口
	
	@Autowired
	private ProjectInfoMapper projectInfoMapper;										//项目信息接口
	
	@Autowired
	private CorporationBankInfoMapper corporationBankInfoMapper;						//企业银行信息接口
    	
	@Autowired
	private BusinessPrjInfoMapper businessPrjInfoMapper;								//项目公司信息接口


	
	
	//分页获取项目股东付款请求列表信息
	@Override
	public Map<String, Object> getStockHolderPayByPage(Payment payMent,Page page) throws Exception {
		Map<String,String> queryParams = new HashMap<String, String>();					//封装查询条件
		Map<String,Object> resultMap = null;											//封装结果信息
		PageUtils.toTrimPageFields(page);												//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;									//起始查询索引
		
		if(payMent != null){
			queryParams.put("prjId", payMent.getPrjId());
			queryParams.put("corp_id", payMent.getContributiveId());
			queryParams.put("financeConfirmStatus", payMent.getFinanceConfirmStatus());
		}
		
		List<Map<String,Object>> stockHolderPayList = paymentMapper.queryStockPaymentInfoList(queryParams,startIndex,pageSize);
		Integer totalCount = paymentMapper.countStockPaymentInfoList(queryParams);
		page.setTotalCount(totalCount);													//赋值最新相关页码信息
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("stockHolderPayList", stockHolderPayList);
		resultMap.put("page", page);
		
		return resultMap;
	}
	
	
	//根据付款主键,分页获取该付款主键下的项目股东付款请求附件信息
	@Override
	public Map<String, Object> getStockHolderVoucherByPage(String payMentPid,Page page) throws Exception {
		Map<String,Object> resultMap = new HashMap<String, Object>();					//封装结果信息
		PageUtils.toTrimPageFields(page);												//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;									//起始查询索引
		
		if(StringUtils.isNotBlank(payMentPid)){
			List<Map<String, Object>> voucherList = paymentAccessoryMapper.getPaymentAccessoriesByPaymentId(payMentPid,startIndex,pageSize);
			int totalCount = paymentAccessoryMapper.countPaymentAccessoriesByPaymentId(payMentPid);
			page.setTotalCount(totalCount);												//赋值最新相关页码信息
			
			resultMap.put("voucherList", voucherList);
			resultMap.put("page", page);
		}
		return resultMap;
	}


	//根据项目主键查询获取项目公司相关信息
	@Override
	public List<Map<String,Object>> getProjectCompanyByProjectPid(String projectPid) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(projectPid)){
			list = projectInfoMapper.getProjectCompanyByProjectPid(projectPid);
		}
		return list;
	}

	
	//根据项目公司主键及项目主键,查询获取有效状态下的股东信息
	@Override
	public List<Map<String, Object>> getStockHolderByProjectComPanyPid(String projectCompanyPid,String projectId) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(projectCompanyPid)){
			list = stockholderInfoMapper.getStockHolderByProjectComPanyPid(projectCompanyPid,projectId);
		}
		return list;
	}
	
	
	//根据企业主键查询获取该企业银行信息
	@Override
	public List<Map<String, Object>> getBankInfoByComPanyPid(String companyPid) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(companyPid)){
			list = corporationBankInfoMapper.getBankInfoByComPanyPid(companyPid);
		}
		return list;
	}
	
	
	//根据银行主键及企业主键,查询获取有效状态的企业银行账号信息
	@Override
	public List<Map<String, Object>> getBankInfoByComPanyPidAndBankId(
			String companyPid, String bankId) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(bankId) && StringUtils.isNotBlank(companyPid)){
			list = corporationBankInfoMapper.getBankInfoByComPanyPidAndBankId(companyPid, bankId);
		}
		return list;
	}

	
	//根据付款主键,查询获取股东相关信息
	@Override
	public Map<String,Object> getStockHolderPayMentByPayMentPid(String payMentPid) throws Exception {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(payMentPid)){
			resultMap = paymentMapper.getStockHolderPayByPid(payMentPid);
		}
		return resultMap;
	}
	
	
	//批量删除股东付款请求
	@Override
	@Transactional
	public int delStockHolderPayByPids(List<String> payMentPids) throws Exception {
		int result = -1;
		if(payMentPids != null && payMentPids.size() > 0){
			result = paymentMapper.delStockHolderPayByPids(payMentPids);
		}
		return result;
	}


	@Override
	public List<Map<String, Object>> queryProjCompanyStockholderList(String businessPrjInfoId, String projectInfoPid, Integer offset, Integer pageRows) {
		return this.stockholderInfoMapper.queryProjCompanyStockholderList(businessPrjInfoId,projectInfoPid,offset,pageRows);
	}

	@Override
	public Integer countProjCompanyStockholderList(String businessPrjInfoId, String projectInfoPid) {
		return this.stockholderInfoMapper.countProjCompanyStockholderList(businessPrjInfoId,projectInfoPid);
	}

	@Override
	public StockholderInfo getStockholderByCorpId(String businessPrjInfoId, String corPid, String prjId) {
		return this.stockholderInfoMapper.getStockholderByCorpId(businessPrjInfoId,corPid,prjId);
	}

	@Override
	@Transactional
	public int insertSelective(StockholderInfo stockholderInfo) {
		return this.stockholderInfoMapper.insertSelective(stockholderInfo);
	}

	@Override
	@Transactional
	public int updateByPrimaryKeySelective(StockholderInfo stockholderInfo) {
		return this.stockholderInfoMapper.updateByPrimaryKeySelective(stockholderInfo);
	}

	@Override
	public int delStockholderInfoByPids(List<String> pids, String curPid) {
		int result = 0;
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		Date updateDate = new Date();
		if(pids != null && pids.size() > 0){
			paramsMap.put("pids", pids);
			paramsMap.put("status", 0);
			paramsMap.put("updateDate", updateDate);
			
			if(StringUtils.isNotBlank(curPid)){
				paramsMap.put("updateId", curPid);
			}
			
			result = this.stockholderInfoMapper.delStockholderInfoByPids(paramsMap);			//伪删除股东信息表
		}
		return result;
	}

	@Override
	@Transactional
	public int batchInsertStockholderAccessory(List<EstateFundFile> files,String stockholderPid, String loginUserId) {
		List<StockholderAccessory> accessoryList = new ArrayList<StockholderAccessory>();
		for(EstateFundFile file: files){
			StockholderAccessory stockholderAccessory = new StockholderAccessory();
			stockholderAccessory.setPid(UuidGenerator.getUuidGenerator());
			stockholderAccessory.setStockholderId(stockholderPid);
			stockholderAccessory.setFileId(file.getPid());
			stockholderAccessory.setCreateDate(new Date());
			stockholderAccessory.setCreateId(loginUserId);
			accessoryList.add(stockholderAccessory);
		}
		return this.stockholderAccessoryMapper.batchInsertStockholderAccessory(accessoryList);
	}

	@Override
	public List<Map<String, Object>> getStockholderAccessoryByStockholderId(String stockholderId, Integer offset, Integer pageRows) {
		return this.stockholderAccessoryMapper.getStockholderAccessoryByStockholderId(stockholderId, offset, pageRows);
	}

	@Override
	public Integer countStockholderAccessoryByStockholderId(String stockholderId) {
		return this.stockholderAccessoryMapper.countStockholderAccessoryByStockholderId(stockholderId);
	}

	@Override
	public List<Map<String, Object>> getStockholderPidByBusinessPrjPid(String projectInfoPid, String businessPrjInfoPid) {
		return this.stockholderInfoMapper.getStockholderPidByBusinessPrjPid(projectInfoPid, businessPrjInfoPid);
	}


	@Override
	public List<String> queryStockholderInfo(String businessPrjPid, String projectInfoPid) {
		return this.stockholderInfoMapper.queryStockholderInfo(businessPrjPid,projectInfoPid);
	}


	@Override
	@Transactional
	public int updateStockholderInfoByPids(List<String> pids,String projectInfoPid, String userInfoPid) {
		int result = 0;
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		Date updateDate = new Date();
		if(pids != null && pids.size() > 0){
			paramsMap.put("pids", pids);
			paramsMap.put("prjId", projectInfoPid);
			paramsMap.put("updateDate", updateDate);
			
			if(StringUtils.isNotBlank(userInfoPid)){
				paramsMap.put("updateId", userInfoPid);
			}
			
			result = this.stockholderInfoMapper.updateStockholderInfoByPids(paramsMap);			
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getStockInfoByPrjId(String projectInfoPid) {
		return this.stockholderInfoMapper.getStockInfoByPrjId(projectInfoPid);
	}
	
	
	
	
	
	


}
