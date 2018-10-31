package com.upjf.fund.web.controller.business.finance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.service.business.BusinessPrjInfoService;
import com.upjf.fund.service.business.CorporationBankService;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.InvestSubjectService;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.business.StockholderInfoService;
import com.upjf.fund.web.controller.base.BaseController;

/**
 * 项目关联企业查询Controller
 * @company upjf.com
 * @author guantong
 *
 */
@RestController
@RequestMapping("/prjContactCorp")
public class ProjectContactCorpInfoController extends BaseController{
	
	@Autowired
	protected CorporationService corporationService;
	
	@Autowired
	protected CorporationBankService corporationBankService;
	
	@Autowired
	protected BusinessPrjInfoService businessPrjInfoService;
	
	@Autowired
	protected ProjectInfoService projectInfoService;
	
	@Autowired
	protected StockholderInfoService stockholderInfoService;
	
	/**
	 * 获取项目股东信息
	 * @param prjId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getProjectStockholder")
	public ModelMap getProjectStockholder(String prjId,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.corporationService.getProjectStockholder(prjId);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询股东信息成功");
			return mm;
		}
		mm.put("errMsg", "查询股东信息失败");
		return mm;
	}
	/**
	 * 根据项目公司异步加载收款公司(投资主体公司上一级，项目股东关联公司)
	 * @param prjId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getProjectStockholderCorp")
	public ModelMap getProjectStockholderCorp(String prjId,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.corporationService.getProjectStockholderCorp(prjId);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询收款公司信息成功");
			return mm;
		}
		mm.put("errMsg", "查询收款公司信息失败");
		return mm;
	}
	
	/**
	 * 根据收款公司(项目股东)和项目公司异步加载出资主体(关联资管计划表)
	 * @param pid
	 * @param receiverId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getInvestSubjectCorp")
	public ModelMap getInvestSubjectCorp(String prjId,String corpId,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.corporationService.getInvestSubjectCorp(prjId,corpId,EstateFundConstants.INVEST_TYPE_INVEST_SUBJECT);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询出资主体公司信息成功");
			return mm;
		}
		mm.put("errMsg", "查询出资主体公司信息失败");
		
		return mm;
	}
	
	
	/**
	 * 根据项目编号获取投资主体公司
	 * @param prjId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getInvestSubjectCorpByPrjId")
	public ModelMap getInvestSubjectCorpByPrjId(String prjId,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.corporationService.getInvestSubjectCorpByPrjId(prjId, EstateFundConstants.INVEST_TYPE_INVEST_SUBJECT);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询收款公司信息成功");
			return mm;
		}
		mm.put("errMsg", "查询收款公司信息失败");
		return mm;
	}
	
	/**
	 * 根据投资主体pid查询投资人公司
	 * @param investSubjectPid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getInvestorCorpByInvestSubjectPid")
	public ModelMap getInvestorCorpByInvestSubjectPid(String parentId,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.corporationService.getInvestorCorpByInvestSubjectPid(parentId,EstateFundConstants.INVEST_TYPE_INVESTOR);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询出资主体公司信息成功");
			return mm;
		}
		mm.put("errMsg", "查询出资主体公司信息失败");
		return mm;
	}
	
	
	/**
	 * 获取企业银行信息
	 * @param receiverId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getInvestSubjectBankInfo")
	public ModelMap getInvestSubjectBankInfo(String corpId,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.corporationBankService.getSimpleCorpBankInfoByCorpId(corpId);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询银行信息成功");
			return mm;
		}
		mm.put("errMsg", "查询银行信息失败");
		return mm;
	}
	
	/**
	 * 获取银行账户
	 * @param corpId
	 * @param bankId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getInvestSubjectBankAccount")
	public ModelMap getInvestSubjectBankAccount(String corpId,String bankId,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<String> result = this.corporationBankService.getInvestSubjectBankAccount(corpId,bankId);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询银行账户信息成功");
			return mm;
		}
		mm.put("errMsg", "查询银行账户信息失败");
		return mm;
	}
	
	/**
	 * 根据项目名称异步加载项目公司
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.12
	 */
	@RequestMapping("/getBusinessPrjInfoPidByPrjId")
	public ModelMap getBusinessPrjInfoPidByPrjId(HttpServletRequest request,HttpServletResponse response){
		String projectInfoPid = request.getParameter("projectInfoPid");
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.projectInfoService.getBusinessPrjInfoPidByPrjId(projectInfoPid);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询收款公司信息成功");
			return mm;
		}
		mm.put("errMsg", "查询收款公司信息失败");
		return mm;
	}
	
	
	/**
	 * 根据项目公司异步加载股东信息
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.12
	 */
	@RequestMapping("/getStockholderPidByBusinessPrjPid")
	public ModelMap getStockholderPidByBusinessPrjPid(HttpServletRequest request,HttpServletResponse response){
		String businessPrjInfoPid = request.getParameter("businessPrjInfoPid");
		String projectInfoPid = request.getParameter("projectInfoPid");
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.stockholderInfoService.getStockholderPidByBusinessPrjPid(projectInfoPid,businessPrjInfoPid);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询收款公司信息成功");
			return mm;
		}
		mm.put("errMsg", "查询收款公司信息失败");
		return mm;
	}
	
	/**
	 * 根据项目名称加载股东
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.23
	 */
	@RequestMapping("/getStockInfoByPrjId")
	public ModelMap getStockInfoByPrjId(HttpServletRequest request,HttpServletResponse response){
		String projectInfoPid = request.getParameter("projectInfoPid");
		ModelMap mm = new ModelMap();
		List<Map<String,Object>> result = this.stockholderInfoService.getStockInfoByPrjId(projectInfoPid);
		if(result!=null && result.size()>0){
			mm.put("result", result);
			mm.put("successMsg", "查询股东信息成功");
			return mm;
		}
		mm.put("errMsg", "查询股东信息失败");
		return mm;
	}
	
	/**
	 * 根据项目名称异步加载项目信息
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.12
	 */
	@RequestMapping("/getProjectInfoByPrjId")
	public ModelMap getProjectInfoByPrjId(HttpServletRequest request,HttpServletResponse response){
		String projectInfoPid = request.getParameter("projectInfoPid");
		ModelMap mm = new ModelMap();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		ProjectInfo projectInfo;
		try {
			projectInfo = this.projectInfoService.getProjectInfoByKey(projectInfoPid);
			if(projectInfo != null) {
				String buildingName = projectInfo.getBuildingName();
				resultMap.put("buildingName", buildingName);
				resultMap.put("projectInfoPid", projectInfoPid);
				mm.put("result", resultMap);
				mm.put("successMsg", "查询项目信息成功");
			} else {
				mm.put("errMsg", "查询项目信息失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mm;
	}
	
	/**
	 * 根据股东pid 加载投资主体公司
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.24
	 */
	@RequestMapping("/getInvestSubjectCorpByStockCorpId")
	public ModelMap getInvestSubjectCorpByStockCorpId(HttpServletRequest request,HttpServletResponse response){
		String stockholderPid = request.getParameter("stockholderPid");
		ModelMap mm = new ModelMap();
		try {
			List<Map<String,Object>> result = this.corporationService.getInvestSubjectCorpByStockCorpId(stockholderPid);
			if(result!=null && result.size()>0){
				mm.put("result", result);
				mm.put("successMsg", "查询投资主体公司成功");
				return mm;
			} else {
				mm.put("errMsg", "查询投资主体公司失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mm;
	}

}
