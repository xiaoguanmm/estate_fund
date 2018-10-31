package com.upjf.fund.web.controller.business.finance.received;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.dto.Received;
import com.upjf.fund.web.aspect.annotation.Log;
import com.upjf.fund.web.utils.file.ExcelUtils;

/**
 * 投资主体利润分配
 * @company upjf.com
 * @author guantong
 *
 */
@Controller
public class IvestSubjectReceivedManageController extends ReceivedManageController{
	
	@RequestMapping("/toInvestSubjectReceivedList")
	public ModelAndView toInvestSubjectReceivedList(){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> amountMap = this.receivedManageService.statisticsAmount(EstateFundConstants.PAYBACK_TYPE_INVEST_SUBJECT);
		mv.addObject("amount", amountMap);
		mv.setViewName(INVEST_SUBJECT_RECEIVED_LIST);
		return mv;
	}
	
	@RequestMapping(value="/queryInvestSubjectPaybackList",produces = "application/json;charset=utf-8")
	public @ResponseBody String  queryInvestSubjectPaybackList(Received received,HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = this.receivedManageService.getInvestSubjectPaybacksByConditions(received,getOffset(),getPageRows());
		Integer records = this.receivedManageService.countInvestSubjectPaybacksByConditions(received);
		return putJsonData(rows, records);
	}
	
	/**
	 * 跳转到每个投资主体回款历史页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toInvestSubjectHistoryPaybackList")
	public ModelAndView toInvestSubjectHistoryPaybackList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		String contributive_name = request.getParameter("contributive_name");
		String receiver_name = request.getParameter("receiver_name");
		String project_name = request.getParameter("project_name");
		String sourceModule = request.getParameter("sourceModule");
		if(StringUtils.isNotEmpty(contributive_name)){
			try {
				contributive_name = URLDecoder.decode(contributive_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(receiver_name)){
			try {
				receiver_name = URLDecoder.decode(receiver_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(project_name)){
			try {
				project_name = URLDecoder.decode(project_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String project_id = request.getParameter("project_id");
		String receiver_id = request.getParameter("receiver_id");
		String contributive_id = request.getParameter("contributive_id");
		Received received = new Received();
		received.setPrjId(project_id);
		received.setReceiverId(receiver_id);
		received.setContributiveId(contributive_id);
		received.setReceivedType(EstateFundConstants.PAYBACK_TYPE_INVEST_SUBJECT);
		Map<String,Object> amountMap = this.receivedManageService.statisticsAmountByParmas(received);
		mv.addObject("amount", amountMap);
		String project_status = this.projectInfoService.getProjectStatusByProjectId(project_id);
		mv.addObject("project_status",project_status);
		String received_status = this.receivedManageService.getReceivedStatus(received);
		mv.addObject("received_status",received_status);
		mv.addObject("project_id", project_id);
		mv.addObject("receiver_id", receiver_id);
		mv.addObject("contributive_id", request.getParameter("contributive_id"));
		mv.addObject("receiver_name", receiver_name);
		mv.addObject("contributive_name", contributive_name);
		mv.addObject("project_name", project_name);
		mv.addObject("sourceModule", sourceModule);
		mv.setViewName(INVEST_SUBJECT_RECEIVED_HISTORY_LIST);
		return mv;
		
	}
	
	/**
	 * 查看投资主体回款历史记录
	 * @param received
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryInvestSubjectHistoryPaybackList",produces = "application/json;charset=utf-8")
	public @ResponseBody String  queryInvestSubjectHistoryPaybackList(Received received,HttpServletRequest request,HttpServletResponse response){
		
		return queryHistoryPaybacksList(received, request, response);
	}
	
	
	/**
	 * 新增投资主体回款记录
	 * @param received
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="财务管理-投资主体利润分配",content="新增/修改 投资主体回款记录")
	@RequestMapping("/addInvestSubjectPaybackRecord")
	public @ResponseBody ModelMap addInvestSubjectPaybackRecord(Received received,HttpServletRequest request,HttpServletResponse response){
		
		return addPaybackRecord(received, request, response);
	}
	
	/**
	 * 查看投资主体回款附件列表
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryInvestSubjectPaybackAccessoryList", produces = "application/json;charset=utf-8")
	protected @ResponseBody String queryInvestSubjectPaybackAccessoryList(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return queryPaybackAccessoryList(pid, request, response);
	}
	
	/**
	 * 删除投资主体回款记录
	 * @param received
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="财务管理-投资主体利润分配",content="删除投资主体回款记录")
	@RequestMapping("/delInvestSubjectPaybackRecord")
	public @ResponseBody ModelMap delInvestSubjectPaybackRecord(Received received,HttpServletRequest request,HttpServletResponse response){
		
		return delPaybackRecord(received, request, response);
	}
	
	/**
	 * 上传投资主体回款收款凭证
	 * @return
	 */
	@Log(module="财务管理-投资主体利润分配",content="上传投资主体回款收款凭证")
	@RequestMapping("/uploadInvestSubjectPaybackAccessory")
	public @ResponseBody ModelMap uploadInvestSubjectPaybackAccessory(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return uploadPaybackAccessory(pid,EstateFundModelConstants.INVEST_SUBJECT_PAYBACK_ACCESSORY,EstateFundConstants.PAYMENT_ACCESSORY_PAY, request, response);
	}
	
	/**
	 * 导出投资主体回款记录列表
	 * @param request
	 * @param response
	 */
	@Log(module="财务管理-投资主体回款",content="导出投资主体回款记录列表")
	@RequestMapping("/exportInvestSubjectPaybackData")
	public void exportInvestSubjectPaybackData(Received received,HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = this.receivedManageService.getInvestSubjectPaybacksByConditions(received,null,null);
		String[] title = {"项目名称","付款公司","收款公司","投入本金(万元)","应回利润(万元)","已回款本金(万元)","已回款利润(万元)","回款状态"};
		String[] filedNames = {"project_name","contributive_name","receiver_name","pay_amount_total","received_profit","receiver_amount_total","profit_total","received_status"};
		String fileName = "投资主体回款.xls";
		try {
			ExcelUtils.exportDataToExcel(rows, title, filedNames, fileName, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出投资主体回款历史记录列表
	 * @param request
	 * @param response
	 */
	@Log(module="财务管理-投资主体回款",content="导出投资主体回款历史记录列表")
	@RequestMapping("/exportInvestSubjectHistoryPaybackData")
	public void exportInvestSubjectHistoryPaybackData(Received received,HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = this.receivedManageService.getHistoryPaybacksByConditions(received,null,null);
		String[] title = {"回款时间","回款账号","回款本金(万元)","回款利润(万元)","项目名称","付款公司","回款备注","回款录入人","回款录入时间"};
		String[] filedNames = {"receiver_date","receiver_account","receiver_amount","profit","project_name","prj_corp_name","reveiver_remark","create_user","create_date"};
		String fileName = "投资主体回款历史.xls";
		try {
			ExcelUtils.exportDataToExcel(rows, title, filedNames, fileName, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
