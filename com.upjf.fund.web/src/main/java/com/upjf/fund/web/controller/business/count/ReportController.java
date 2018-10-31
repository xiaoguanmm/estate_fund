package com.upjf.fund.web.controller.business.count;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.upjf.fund.base.Page;
import com.upjf.fund.service.business.InvestSubjectFinanceService;
import com.upjf.fund.service.business.ProjectStatisticsService;
import com.upjf.fund.service.business.ReportService;
import com.upjf.fund.web.controller.base.BaseController;

/**
    * @ClassName: ReportController
    * @Description: 报表统计
    * @author wufujing
    * @date 2018年10月09日
    *
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController{
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private ProjectStatisticsService projectStatisticsService;
	@Autowired
	private InvestSubjectFinanceService investSubjectFinanceService;
	
	
	/** 项目投入产出统计列表页*/
	@RequestMapping(value="/projectInOutListPage")
	public String projectInOutListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		List<Map<String,Object>> projectCompanyList = reportService.getProjectCompanyList();
		model.put("projectList", projectList);
		model.put("projectCompanyList", projectCompanyList);
		
		return "/count/project_inoutput";
	}
	
	/** 项目投入产出统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectInOutList")
	public void projectInOutList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectInOutList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String businessPrjInfoId = request.getParameter("businessPrjInfoId");
			String projectProgress = request.getParameter("projectProgress");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("businessPrjInfoId", businessPrjInfoId);
			params.put("projectProgress", projectProgress);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = reportService.getProjectInOutList(params);
			
			if(resultMap != null){
				projectInOutList = (List<Map<String,Object>>) resultMap.get("projectInOutList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectInOutList, total,pageSize);
	}
	
	/** 项目投入产出-统计图形数据页*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectInOutFormPage")
	public String projectInOutFormPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
	    int pageSize = 10;//最大取前10条数据显示 
	    int offset = 0;
		List<Map<String,Object>> projectInOutList = null;
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = reportService.getProjectInOutList(params);
			
			if(resultMap != null){
				projectInOutList = (List<Map<String,Object>>) resultMap.get("projectInOutList");
				if(projectInOutList != null && projectInOutList.size() > 0){
					
					//显示的数据类型:预计总投资，实际总投资，预计总回报，实际总回报
					List<String> amountTypeList = new ArrayList<String>();
					amountTypeList.add("预计总投资");
					amountTypeList.add("实际总投资");
					amountTypeList.add("预计总回报");
					amountTypeList.add("实际总回报");
					
					//项目类型
					List<String> projectNameList = new ArrayList<String>();
					//预计总投资
					List<BigDecimal> expectInvestAmountList = new ArrayList<BigDecimal>();
					//实际总投资
					List<BigDecimal> expectAllReceiverAccountList = new ArrayList<BigDecimal>();
					//预计总回报
					List<BigDecimal> realityInvestAmountList = new ArrayList<BigDecimal>();
					//实际总回报
					List<BigDecimal> realityReceivedAmountList = new ArrayList<BigDecimal>();
					
					for (int i = 0; i < projectInOutList.size(); i++) {
						Map<String,Object> projectItem = projectInOutList.get(i);
						if(projectItem != null){
							String projectName = (String) projectItem.get("project_name");
							if(StringUtils.isNotBlank(projectName)){
								if(projectName.length() > 6){
									projectNameList.add(projectName.substring(0, 6)+"...");
								}else{
									projectNameList.add(projectName);
								}
							}else{
								projectNameList.add("无");
							}
							BigDecimal expectInvestAmount = (BigDecimal) projectItem.get("expect_invest_amount");
							if(expectInvestAmount == null){
								expectInvestAmount = BigDecimal.ZERO;
							} 
							expectInvestAmountList.add(expectInvestAmount);
							
							BigDecimal expectAllReceiverAccount = (BigDecimal) projectItem.get("expect_all_receiver_account");
							if(expectAllReceiverAccount == null){
								expectAllReceiverAccount = BigDecimal.ZERO;
							} 
							expectAllReceiverAccountList.add(expectAllReceiverAccount);
							
							BigDecimal realityInvestAmount = (BigDecimal) projectItem.get("reality_invest_amount");
							if(realityInvestAmount == null){
								realityInvestAmount = BigDecimal.ZERO;
							} 
							realityInvestAmountList.add(realityInvestAmount);
							
							BigDecimal realityReceivedAmount = (BigDecimal) projectItem.get("reality_received_amount");
							if(realityReceivedAmount == null){
								realityReceivedAmount = BigDecimal.ZERO;
							} 
							realityReceivedAmountList.add(realityReceivedAmount);

						}
					}
					
					model.addAttribute("amountTypeList", JSONArray.toJSONString(amountTypeList));
					model.addAttribute("projectNameList",  JSONArray.toJSONString(projectNameList));
					model.addAttribute("expectInvestAmountList",  JSONArray.toJSONString(expectInvestAmountList));
					model.addAttribute("expectAllReceiverAccountList",  JSONArray.toJSONString(expectAllReceiverAccountList));
					model.addAttribute("realityInvestAmountList",  JSONArray.toJSONString(realityInvestAmountList));
					model.addAttribute("realityReceivedAmountList",  JSONArray.toJSONString(realityReceivedAmountList));
				}
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate.substring(0, 10);
			}
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate.substring(0, 10);
			}
			model.addAttribute("startDate",  startDate);
			model.addAttribute("endDate",  endDate);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "/count/project_inoutput_form";
	}
	
	/** 项目投入产出-股东公司统计列表数据页*/
	@RequestMapping(value="/projectInOutCompanyListPage")
	public String projectInOutCompanyListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		String businessPrjInfoId = request.getParameter("businessPrjInfoId");
		String stockholderId = request.getParameter("stockholderId");
		String intoType = request.getParameter("intoType");//进入类型：1：从项目投入产出进入，2：从项目股东投入产出进入
		model.put("projectId", projectId);
		model.put("businessPrjInfoId", businessPrjInfoId);
		model.put("stockholderId", stockholderId);
		model.put("intoType", intoType);
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		List<Map<String,Object>> projectCompanyList = reportService.getProjectCompanyList();
		model.put("projectList", projectList);
		model.put("projectCompanyList", projectCompanyList);
		
		return "/count/project_inoutput_company";
	}
	
	/** 项项目投入产出-股东公司统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectInOutCompanyList")
	public void projectInOutCompanyList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectInOutCompanyList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String businessPrjInfoId = request.getParameter("businessPrjInfoId");
			String projectProgress = request.getParameter("projectProgress");
			String stockholderId = request.getParameter("stockholderId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("businessPrjInfoId", businessPrjInfoId);
			params.put("projectProgress", projectProgress);
			params.put("stockholderId", stockholderId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = reportService.getProjectInOutCompanyList(params);
			
			if(resultMap != null){
				projectInOutCompanyList = (List<Map<String,Object>>) resultMap.get("projectInOutCompanyList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectInOutCompanyList, total,pageSize);
	}
	
	/** 项目投入产出-股东公司投资统计列表数据页*/
	@RequestMapping(value="/projectInOutInvestListPage")
	public String projectInOutInvestListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		String businessPrjInfoId = request.getParameter("businessPrjInfoId");
		String corpId = request.getParameter("corpId");
		String intoType = request.getParameter("intoType");//进入类型：1：从项目投入产出进入，2：从项目股东投入产出进入
		
		model.put("projectId", projectId);
		model.put("businessPrjInfoId", businessPrjInfoId);
		model.put("corpId", corpId);
		model.put("intoType", intoType);
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		List<Map<String,Object>> projectCompanyList = reportService.getProjectCompanyList();
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		
		model.put("projectList", projectList);
		model.put("projectCompanyList", projectCompanyList);
		model.put("stockholderList", stockholderList);
		return "/count/project_inoutput_invest";
	}
	
	/** 项项目投入产出-股东公司投资统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectInOutInvestList")
	public void projectInOutInvestList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    String totalAmount= "";
	    Map<String,Object> paramMap = new HashMap<String, Object>();
	    
	    List<Map<String,Object>> projectInOutInvestList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String businessPrjInfoId = request.getParameter("businessPrjInfoId");
			String contributiveId = request.getParameter("contributiveId");
			String financeConfirmStatus = request.getParameter("financeConfirmStatus");
			String payStartDate = request.getParameter("payStartDate");
			String payEndDate = request.getParameter("payEndDate");
			String receiverStartDate = request.getParameter("receiverStartDate");
			String receiverEndDate = request.getParameter("receiverEndDate");
			
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(payStartDate)){
				payStartDate = payStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(payEndDate)){
				payEndDate = payEndDate +" 23:59:59";
			}
			if(StringUtils.isNotBlank(receiverStartDate)){
				receiverStartDate = receiverStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(receiverEndDate)){
				receiverEndDate = receiverEndDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("businessPrjInfoId", businessPrjInfoId);
			params.put("contributiveId", contributiveId);
			params.put("financeConfirmStatus", financeConfirmStatus);
			params.put("payStartDate", payStartDate);
			params.put("payEndDate", payEndDate);
			params.put("receiverStartDate", receiverStartDate);
			params.put("receiverEndDate", receiverEndDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
//			if(StringUtils.isNotBlank(projectId) && StringUtils.isNotBlank(businessPrjInfoId)){
				Map<String,Object> resultMap  = reportService.getProjectInOutInvestList(params);
				
				if(resultMap != null){
					projectInOutInvestList = (List<Map<String,Object>>) resultMap.get("projectInOutInvestList");
					total = (int) resultMap.get("total");
					totalAmount = resultMap.get("totalAmount")+"";
					paramMap.put("totalAmount", totalAmount);
				}
//			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectInOutInvestList, total,pageSize,paramMap);
	}
	
	/** 项目投入产出-股东公司统计列表数据页*/
	@RequestMapping(value="/projectInOutRepayListPage")
	public String projectInOutRepayListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		String businessPrjInfoId = request.getParameter("businessPrjInfoId");
		String corpId = request.getParameter("corpId");
		String intoType = request.getParameter("intoType");//进入类型：1：从项目投入产出进入，2：从项目股东投入产出进入
		
		model.put("projectId", projectId);
		model.put("businessPrjInfoId", businessPrjInfoId);
		model.put("corpId", corpId);
		model.put("intoType", intoType);
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		List<Map<String,Object>> projectCompanyList = reportService.getProjectCompanyList();
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		
		model.put("projectList", projectList);
		model.put("projectCompanyList", projectCompanyList);
		model.put("stockholderList", stockholderList);
		return "/count/project_inoutput_repay";
	}
	
	/** 项项目投入产出-股东公司统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectInOutRepayList")
	public void projectInOutRepayList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    String totalAmount = "";
	    Map<String,Object> paramMap = new HashMap<String, Object>();
	    
	    List<Map<String,Object>> projectInOutRepayList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String businessPrjInfoId = request.getParameter("businessPrjInfoId");
			String receiverId = request.getParameter("receiverId");
			String receiverAccount = request.getParameter("receiverAccount");
			String receivedStatus = request.getParameter("receivedStatus");
			String receiverStartDate = request.getParameter("receiverStartDate");
			String receiverEndDate = request.getParameter("receiverEndDate");
			if(!StringUtils.isBlank(receiverAccount)){
				receiverAccount = URLDecoder.decode(receiverAccount, "UTF-8");
			}
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(receiverStartDate)){
				receiverStartDate = receiverStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(receiverEndDate)){
				receiverEndDate = receiverEndDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("businessPrjInfoId", businessPrjInfoId);
			params.put("receiverId", receiverId);
			params.put("receiverAccount", receiverAccount);
			params.put("receivedStatus", receivedStatus);
			params.put("receiverStartDate", receiverStartDate);
			params.put("receiverEndDate", receiverEndDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
//			if(StringUtils.isNotBlank(projectId) && StringUtils.isNotBlank(businessPrjInfoId)){
				Map<String,Object> resultMap  = reportService.getProjectInOutRepayList(params);
				
				if(resultMap != null){
					projectInOutRepayList = (List<Map<String,Object>>) resultMap.get("projectInOutRepayList");
					total = (int) resultMap.get("total");
					totalAmount = resultMap.get("totalAmount")+"";
					paramMap.put("totalAmount", totalAmount);
				}
//			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectInOutRepayList, total,pageSize,paramMap);
	}
	
	/** 回款明细附件列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getReturnDetailFileList")
	public void getReturnDetailFileList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 1;
	    
	    List<Map<String,Object>> subjectReturnDetailFileList = null;
		try {
			String subjectReturnPid = request.getParameter("subjectReturnPid");
			if(StringUtils.isNotBlank(subjectReturnPid)){
				Page page = new Page();
				page.setCurPage(offset+"");
				page.setPageSize(pageSize+"");
				
				Map<String,Object> resultMap  = investSubjectFinanceService.getSubjectReturnDetailFileList(subjectReturnPid,page);
				
				if(resultMap != null){
					subjectReturnDetailFileList = (List<Map<String,Object>>) resultMap.get("subjectReturnDetailFileList");
					page = (Page) resultMap.get("page");
					if(page != null){
						total =  resultMap.get("totalCount") == null ?  0 : Integer.parseInt(resultMap.get("totalCount")+"");
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, subjectReturnDetailFileList, total,pageSize);
	}
	
	/** 股东项目投入产出统计列表页*/
	@RequestMapping(value="/stockholderInOutListPage")
	public String stockholderInOutListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		model.put("stockholderList", stockholderList);
		
		return "/count/stockholder_inoutput";
	}
	
	/** 股东项目投入产出统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/stockholderInOutList")
	public void stockholderInOutList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> stockholderInOutList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String stockholderId = request.getParameter("stockholderId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("stockholderId", stockholderId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = reportService.getStockholderInOutList(params);
			
			if(resultMap != null){
				stockholderInOutList = (List<Map<String,Object>>) resultMap.get("stockholderInOutList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, stockholderInOutList, total,pageSize);
	}
	
	/** 项目股东投资投入产出-统计图形数据页*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/stockholderInOutFormPage")
	public String stockholderInOutFormPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
	    int pageSize = 10;//最大取前10条数据显示 
	    int offset = 0;
		List<Map<String,Object>> stockholderInOutList = null;
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = reportService.getStockholderInOutList(params);
			
			if(resultMap != null){
				stockholderInOutList = (List<Map<String,Object>>) resultMap.get("stockholderInOutList");
				if(stockholderInOutList != null && stockholderInOutList.size() > 0){
					
					//显示的数据类型:预计总投资，实际总投资，预计总回报，实际总回报
					List<String> amountTypeList = new ArrayList<String>();
					amountTypeList.add("预计总投资");
					amountTypeList.add("实际总投资");
					amountTypeList.add("预计总回报");
					amountTypeList.add("实际总回报");
					
					//股东名称
					List<String> stockholderNameList = new ArrayList<String>();
					//预计总投资
					List<BigDecimal> expectInvestAmountList = new ArrayList<BigDecimal>();
					//实际总投资
					List<BigDecimal> expectAllReceiverAccountList = new ArrayList<BigDecimal>();
					//预计总回报
					List<BigDecimal> realityInvestAmountList = new ArrayList<BigDecimal>();
					//实际总回报
					List<BigDecimal> realityReceivedAmountList = new ArrayList<BigDecimal>();
					
					for (int i = 0; i < stockholderInOutList.size(); i++) {
						Map<String,Object> projectItem = stockholderInOutList.get(i);
						if(projectItem != null){
							String stockholderName = (String) projectItem.get("stockholder_name");
							if(StringUtils.isNotBlank(stockholderName)){
								if(stockholderName.length() > 6){
									stockholderNameList.add(stockholderName.substring(0, 6)+"...");
								}else{
									stockholderNameList.add(stockholderName);
								}
							}else{
								stockholderNameList.add("无");
							}
							BigDecimal expectInvestAmount = (BigDecimal) projectItem.get("expect_invest_amount");
							if(expectInvestAmount == null){
								expectInvestAmount = BigDecimal.ZERO;
							} 
							expectInvestAmountList.add(expectInvestAmount);
							
							BigDecimal expectAllReceiverAccount = (BigDecimal) projectItem.get("expect_all_receiver_account");
							if(expectAllReceiverAccount == null){
								expectAllReceiverAccount = BigDecimal.ZERO;
							} 
							expectAllReceiverAccountList.add(expectAllReceiverAccount);
							
							BigDecimal realityInvestAmount = (BigDecimal) projectItem.get("reality_invest_amount");
							if(realityInvestAmount == null){
								realityInvestAmount = BigDecimal.ZERO;
							} 
							realityInvestAmountList.add(realityInvestAmount);
							
							BigDecimal realityReceivedAmount = (BigDecimal) projectItem.get("reality_received_amount");
							if(realityReceivedAmount == null){
								realityReceivedAmount = BigDecimal.ZERO;
							} 
							realityReceivedAmountList.add(realityReceivedAmount);

						}
					}
					
					model.addAttribute("amountTypeList", JSONArray.toJSONString(amountTypeList));
					model.addAttribute("stockholderNameList",  JSONArray.toJSONString(stockholderNameList));
					model.addAttribute("expectInvestAmountList",  JSONArray.toJSONString(expectInvestAmountList));
					model.addAttribute("expectAllReceiverAccountList",  JSONArray.toJSONString(expectAllReceiverAccountList));
					model.addAttribute("realityInvestAmountList",  JSONArray.toJSONString(realityInvestAmountList));
					model.addAttribute("realityReceivedAmountList",  JSONArray.toJSONString(realityReceivedAmountList));
				}
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate.substring(0, 10);
			}
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate.substring(0, 10);
			}
			model.addAttribute("startDate",  startDate);
			model.addAttribute("endDate",  endDate);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "/count/stockholder_inoutput_form";
	}
	
	/** 项目股东投入产出-项目投入产出统计列表数据页*/
	@RequestMapping(value="/stockholderInOutCompanyListPage")
	public String stockholderInOutCompanyListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String corpId = request.getParameter("corpId");
		model.put("corpId", corpId);
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		List<Map<String,Object>> projectCompanyList = reportService.getProjectCompanyList();
		model.put("projectList", projectList);
		model.put("projectCompanyList", projectCompanyList);
		
		return "/count/stockholder_inoutput_company";
	}
	
	/** 项目股东投入产出-项目投入产出统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/stockholderInOutCompanyList")
	public void stockholderInOutCompanyList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> stockholderInOutCompanyList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String businessPrjInfoId = request.getParameter("businessPrjInfoId");
			String corpId = request.getParameter("corpId");
			String projectProgress = request.getParameter("projectProgress");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("businessPrjInfoId", businessPrjInfoId);
			params.put("corpId", corpId);
			params.put("projectProgress", projectProgress);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
//			if(StringUtils.isNotBlank(projectId) && StringUtils.isNotBlank(businessPrjInfoId)){
				Map<String,Object> resultMap  = reportService.getProjectInOutCompanyList(params);
				
				if(resultMap != null){
					stockholderInOutCompanyList = (List<Map<String,Object>>) resultMap.get("projectInOutCompanyList");
					total = (int) resultMap.get("total");
				}
//			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, stockholderInOutCompanyList, total,pageSize);
	}
	
	/** 投资主体、投资人投入产出统计列表数据页*/
	@RequestMapping(value="/investInOutListPage")
	public String investInOutListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String investType = request.getParameter("investType");
		model.put("investType", investType);
		
		List<Map<String,Object>> investCompanyList = reportService.getInvestCompanyList(investType);
		model.put("investCompanyList", investCompanyList);
		
		return "/count/invest_inoutput";
	}
	
	/** 投资主体、投资人投入产出-项目投入产出统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/investInOutList")
	public void investInOutList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> investInOutList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String investType = request.getParameter("investType");
			String investSubjectId = request.getParameter("investSubjectId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}
			if(StringUtils.isBlank(investType)){
				investType = "1";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("investType", investType);
			params.put("investSubjectId", investSubjectId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			if("1".equals(investType)){//投资主体
				params.put("paymentType", "2");
				params.put("receivedType", "2");
			}else if("2".equals(investType)){//投资人
				params.put("paymentType", "3");
				params.put("receivedType", "3");
			}
			
			Map<String,Object> resultMap  = reportService.getInvestInOutList(params);
			
			if(resultMap != null){
				investInOutList = (List<Map<String,Object>>) resultMap.get("investInOutList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, investInOutList, total,pageSize);
	}
	
	/** 投资主体、投资人投入产出-统计图形数据页*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/investInOutFormPage")
	public String investInOutFormPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
	    int pageSize = 10;//最大取前10条数据显示 
	    int offset = 0;
		List<Map<String,Object>> investInOutList = null;
		try {
			String investType = request.getParameter("investType");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}
			if(StringUtils.isBlank(investType)){
				investType = "1";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("investType", investType);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			if("1".equals(investType)){//投资主体
				params.put("paymentType", "2");
				params.put("receivedType", "2");
			}else if("2".equals(investType)){//投资人
				params.put("paymentType", "3");
				params.put("receivedType", "3");
			}
			
			Map<String,Object> resultMap  = reportService.getInvestInOutList(params);
			
			if(resultMap != null){
				investInOutList = (List<Map<String,Object>>) resultMap.get("investInOutList");
				if(investInOutList != null && investInOutList.size() > 0){
					
					//显示的数据类型:预计总投资，实际总投资，预计总回报，实际总回报
					List<String> amountTypeList = new ArrayList<String>();
					amountTypeList.add("预计总投资");
					amountTypeList.add("实际总投资");
					amountTypeList.add("预计总回报");
					amountTypeList.add("实际总回报");
					
					//股东名称
					List<String> investNameList = new ArrayList<String>();
					//预计总投资
					List<BigDecimal> expectInvestAmountList = new ArrayList<BigDecimal>();
					//实际总投资
					List<BigDecimal> expectAllReceiverAccountList = new ArrayList<BigDecimal>();
					//预计总回报
					List<BigDecimal> realityInvestAmountList = new ArrayList<BigDecimal>();
					//实际总回报
					List<BigDecimal> realityReceivedAmountList = new ArrayList<BigDecimal>();
					
					for (int i = 0; i < investInOutList.size(); i++) {
						Map<String,Object> projectItem = investInOutList.get(i);
						if(projectItem != null){
							String investName = (String) projectItem.get("invest_name");
							if(StringUtils.isNotBlank(investName)){
								if(investName.length() > 6){
									investNameList.add(investName.substring(0, 6)+"...");
								}else{
									investNameList.add(investName);
								}
							}else{
								investNameList.add("无");
							}
							BigDecimal expectInvestAmount = (BigDecimal) projectItem.get("expect_invest_amount");
							if(expectInvestAmount == null){
								expectInvestAmount = BigDecimal.ZERO;
							} 
							expectInvestAmountList.add(expectInvestAmount);
							
							BigDecimal expectAllReceiverAccount = (BigDecimal) projectItem.get("expect_all_receiver_account");
							if(expectAllReceiverAccount == null){
								expectAllReceiverAccount = BigDecimal.ZERO;
							} 
							expectAllReceiverAccountList.add(expectAllReceiverAccount);
							
							BigDecimal realityInvestAmount = (BigDecimal) projectItem.get("reality_invest_amount");
							if(realityInvestAmount == null){
								realityInvestAmount = BigDecimal.ZERO;
							} 
							realityInvestAmountList.add(realityInvestAmount);
							
							BigDecimal realityReceivedAmount = (BigDecimal) projectItem.get("reality_received_amount");
							if(realityReceivedAmount == null){
								realityReceivedAmount = BigDecimal.ZERO;
							} 
							realityReceivedAmountList.add(realityReceivedAmount);

						}
					}
					
					model.addAttribute("amountTypeList", JSONArray.toJSONString(amountTypeList));
					model.addAttribute("investNameList",  JSONArray.toJSONString(investNameList));
					model.addAttribute("expectInvestAmountList",  JSONArray.toJSONString(expectInvestAmountList));
					model.addAttribute("expectAllReceiverAccountList",  JSONArray.toJSONString(expectAllReceiverAccountList));
					model.addAttribute("realityInvestAmountList",  JSONArray.toJSONString(realityInvestAmountList));
					model.addAttribute("realityReceivedAmountList",  JSONArray.toJSONString(realityReceivedAmountList));
				}
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate.substring(0, 10);
			}
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate.substring(0, 10);
			}
			model.addAttribute("startDate",  startDate);
			model.addAttribute("endDate",  endDate);
			model.addAttribute("investType",  investType);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "/count/invest_inoutput_form";
	}
	
	/** 投资主体投入产出-项目投入产出统计列表数据页*/
	@RequestMapping(value="/investSubInOutCompanyListPage")
	public String investSubInOutCompanyListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		String investSubId = request.getParameter("investSubId");
		model.put("projectId", projectId);
		model.put("investSubId", investSubId);

		List<Map<String,Object>> projectList = reportService.getProjectList();
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		model.put("stockholderList", stockholderList);
		model.put("projectList", projectList);
		
		return "/count/investsub_inoutput_company";
	}
	
	/** 投资主体投入产出-项目投入产出统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/investSubInOutCompanyList")
	public void investSubInOutCompanyList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> investSubInOutCompanyList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String projectProgress = request.getParameter("projectProgress");
			String investSubId = request.getParameter("investSubId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("projectProgress", projectProgress);
			params.put("investSubId", investSubId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = reportService.getInvestSubInOutCompanyList(params);
			
			if(resultMap != null){
				investSubInOutCompanyList = (List<Map<String,Object>>) resultMap.get("investSubInOutCompanyList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, investSubInOutCompanyList, total,pageSize);
	}
	
	/** 投资人投入产出-项目投入产出统计列表数据页*/
	@RequestMapping(value="/investorInOutCompanyListPage")
	public String investorInOutCompanyListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		String investorId = request.getParameter("investorId");
		model.put("projectId", projectId);
		model.put("investorId", investorId);
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		List<Map<String,Object>> investCompanyList = reportService.getInvestCompanyList("1");
		model.put("investCompanyList", investCompanyList);
		model.put("projectList", projectList);
		
		return "/count/investor_inoutput_company";
	}
	
	/** 投资人投入产出-项目投入产出统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/investorInOutCompanyList")
	public void investorInOutCompanyList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> investorInOutCompanyList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String investorId = request.getParameter("investorId");
			String investSubId = request.getParameter("investSubId");
			String projectProgress = request.getParameter("projectProgress");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("investSubId", investSubId);
			params.put("projectProgress", projectProgress);
			params.put("investorId", investorId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = reportService.getInvestorInOutCompanyList(params);
			
			if(resultMap != null){
				investorInOutCompanyList = (List<Map<String,Object>>) resultMap.get("investorInOutCompanyList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, investorInOutCompanyList, total,pageSize);
	}
	
	/**投资主体、投资人投资统计列表数据页*/
	@RequestMapping(value="/investInOutInvestListPage")
	public String investInOutInvestListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		String contributiveId = request.getParameter("contributiveId");
		String investType = request.getParameter("investType");//进入类型：1：投资主体，2：投资人
		
		model.put("projectId", projectId);
		model.put("contributiveId", contributiveId);
		model.put("investType", investType);
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		if("1".equals(investType)){
			List<Map<String,Object>> investSubCompanyList = reportService.getInvestCompanyList(investType);//获取投资主体公司
			List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();//获取股东公司
			model.put("investSubCompanyList", investSubCompanyList);
			model.put("stockholderList", stockholderList);
		}else if("2".equals(investType)){
			List<Map<String,Object>> investSubCompanyList = reportService.getInvestCompanyList("1");//获取投资主体公司
			List<Map<String,Object>> investorCompanyList = reportService.getInvestCompanyList("2");//获取投资人公司
			model.put("investSubCompanyList", investSubCompanyList);
			model.put("investorCompanyList", investorCompanyList);
		}
		
		model.put("projectList", projectList);
		return "/count/invest_inoutput_invest";
	}
	
	/** 投资主体、投资人投资统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/investInOutInvestList")
	public void investInOutInvestList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    String totalAmount= "";
	    Map<String,Object> paramMap = new HashMap<String, Object>();
	    
	    List<Map<String,Object>> investInOutInvestList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String investType = request.getParameter("investType");//进入类型：1：投资主体，2：投资人
			String projectId = request.getParameter("projectId");
			String contributiveId = request.getParameter("contributiveId");
			String receiverId = request.getParameter("receiverId");
			String financeConfirmStatus = request.getParameter("financeConfirmStatus");
			String payStartDate = request.getParameter("payStartDate");
			String payEndDate = request.getParameter("payEndDate");
			String receiverStartDate = request.getParameter("receiverStartDate");
			String receiverEndDate = request.getParameter("receiverEndDate");
			
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(payStartDate)){
				payStartDate = payStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(payEndDate)){
				payEndDate = payEndDate +" 23:59:59";
			}
			if(StringUtils.isNotBlank(receiverStartDate)){
				receiverStartDate = receiverStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(receiverEndDate)){
				receiverEndDate = receiverEndDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("contributiveId", contributiveId);
			params.put("receiverId", receiverId);
			params.put("financeConfirmStatus", financeConfirmStatus);
			params.put("payStartDate", payStartDate);
			params.put("payEndDate", payEndDate);
			params.put("receiverStartDate", receiverStartDate);
			params.put("receiverEndDate", receiverEndDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			if("1".equals(investType)){//投资主体
				params.put("paymentType", "2");
			}else if("2".equals(investType)){//投资人
				params.put("paymentType", "3");
			}
			
			Map<String,Object> resultMap  = reportService.getInvestInOutInvestList(params);
			
			if(resultMap != null){
				investInOutInvestList = (List<Map<String,Object>>) resultMap.get("investInOutInvestList");
				total = (int) resultMap.get("total");
				totalAmount = resultMap.get("totalAmount")+"";
				paramMap.put("totalAmount", totalAmount);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, investInOutInvestList, total,pageSize,paramMap);
	}
	
	/** 投资主体、投资人回报统计列表数据页*/
	@RequestMapping(value="/investInOutRepayListPage")
	public String investInOutRepayListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		String receiverId = request.getParameter("receiverId");
		String investType = request.getParameter("investType");//进入类型：1：投资主体，2：投资人
		
		model.put("projectId", projectId);
		model.put("receiverId", receiverId);
		model.put("investType", investType);
		
		List<Map<String,Object>> projectList = reportService.getProjectList();
		if("1".equals(investType)){
			List<Map<String,Object>> investSubCompanyList = reportService.getInvestCompanyList(investType);//获取投资主体公司
			List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();//获取股东公司
			model.put("investSubCompanyList", investSubCompanyList);
			model.put("stockholderList", stockholderList);
		}else if("2".equals(investType)){
			List<Map<String,Object>> investSubCompanyList = reportService.getInvestCompanyList("1");//获取投资主体公司
			List<Map<String,Object>> investorCompanyList = reportService.getInvestCompanyList("2");//获取投资人公司
			model.put("investSubCompanyList", investSubCompanyList);
			model.put("investorCompanyList", investorCompanyList);
		}
		
		model.put("projectList", projectList);
		return "/count/invest_inoutput_repay";
	}
	
	/** 投资主体、投资人回报统计列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/investInOutRepayList")
	public void investInOutRepayList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    String totalAmount = "";
	    Map<String,Object> paramMap = new HashMap<String, Object>();
	    
	    List<Map<String,Object>> investInOutRepayList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String investType = request.getParameter("investType");//进入类型：1：投资主体，2：投资人
			String projectId = request.getParameter("projectId");
			String contributiveId = request.getParameter("contributiveId");
			String receiverId = request.getParameter("receiverId");
			String receiverAccount = request.getParameter("receiverAccount");
			String receivedStatus = request.getParameter("receivedStatus");
			String receiverStartDate = request.getParameter("receiverStartDate");
			String receiverEndDate = request.getParameter("receiverEndDate");
			if(!StringUtils.isBlank(receiverAccount)){
				receiverAccount = URLDecoder.decode(receiverAccount, "UTF-8");
			}
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(receiverStartDate)){
				receiverStartDate = receiverStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(receiverEndDate)){
				receiverEndDate = receiverEndDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("contributiveId", contributiveId);
			params.put("receiverId", receiverId);
			params.put("receiverAccount", receiverAccount);
			params.put("receivedStatus", receivedStatus);
			params.put("receiverStartDate", receiverStartDate);
			params.put("receiverEndDate", receiverEndDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			if("1".equals(investType)){//投资主体
				params.put("receivedType", "2");
			}else if("2".equals(investType)){//投资人
				params.put("receivedType", "3");
			}
			
			Map<String,Object> resultMap  = reportService.getInvestInOutRepayList(params);
			
			if(resultMap != null){
				investInOutRepayList = (List<Map<String,Object>>) resultMap.get("investInOutRepayList");
				total = (int) resultMap.get("total");
				totalAmount = resultMap.get("totalAmount")+"";
				paramMap.put("totalAmount", totalAmount);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, investInOutRepayList, total,pageSize,paramMap);
	}
}
