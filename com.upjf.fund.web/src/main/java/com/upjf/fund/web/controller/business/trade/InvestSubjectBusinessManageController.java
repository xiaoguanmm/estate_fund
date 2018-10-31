package com.upjf.fund.web.controller.business.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upjf.fund.constants.TagConstants;
import com.upjf.fund.dto.BusinessPrjInfo;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.InvestSubjectFinanceVo;
import com.upjf.fund.dto.ProjectBudget;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.dto.ProjectProgress;
import com.upjf.fund.dto.SysAreaInfo;
import com.upjf.fund.service.business.BusinessPrjInfoService;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.InvestSubjectFinanceService;
import com.upjf.fund.service.business.InvestSubjectService;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.service.system.SysAreaInfoService;
import com.upjf.fund.web.controller.base.BaseController;

/**
 * 投资主体业务管理 Controller
 * @author zhangcai
 * @time：2018年10月26日下午2:28:47
 * @version：
 * @ClassAnnotation：投资人业务管理 
 *
 */
@Controller
@RequestMapping("/investSubjectBusinessManage")
public class InvestSubjectBusinessManageController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(InvestSubjectBusinessManageController.class);
	
	@Autowired
	protected InvestSubjectService investSubjectService;
	
	@Autowired
	protected CorporationService corporationService;
	
	@Autowired
	protected InvestSubjectFinanceService investSubjectFinanceService;
	
	@Autowired
	protected DictInfoService dictInfoService;
	
	@Autowired
	protected BusinessPrjInfoService businessPrjInfoService;
	
	@Autowired
	protected ProjectInfoService projectInfoService;
	
	@Autowired
	protected SysAreaInfoService sysAreaInfoService;
	
	/**
	 * 跳转至投资主体信息管理页面
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.26
	 */
	@RequestMapping("/toInvestSubjectIndex")
	public String toInvestSubjectIndex(Model model, HttpServletRequest request,HttpServletResponse response){
		return "trade/invest_subject_business_manage/invest_subject_business";
	}
	
	
	/**
	 * 投资主体业务管理index页 列表
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.26
	 */
	@RequestMapping(value="/queryInvestSubjectBusinessManageList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryInvestSubjectBusinessManageList(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> conditions = new HashMap<String,String>();
		conditions.put("projectName", request.getParameter("projectName"));
		conditions.put("investSubjectCorpId", request.getParameter("investSubjectCorpId"));
		conditions.put("contributiveType", request.getParameter("contributiveType"));
		
		List<Map<String, Object>> rows = this.investSubjectService.queryInvestSubjectBusinessManageList(conditions,getOffset(), getPageRows());
		Integer records = this.investSubjectService.countInvestSubjectBusinessManageList(conditions);
		return putJsonData(rows, records);
	}
	
	/**
	 * 进入投资主体业务管理---查看详情页面
	 * @author  zhangcai 
	 * @param   investSubjectFinanceVo
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年10月17日
	 */
	@RequestMapping(value="/toInvestSubjectBusinessManangeDetail")
	private String toInvestSubjectBusinessManangeDetail(InvestSubjectFinanceVo subjectFinanceVo,Model model,HttpServletRequest req){
		List<BusinessPrjInfo> busProInfoList = null;										//获取项目公司信息
		List<CorporationInfo> corInfoList = null;											//开发商信息
		List<SysAreaInfo> provinceList = null;												//省级信息
		List<DictDetail> proCateGoryList = null;											//项目种类
		List<DictDetail> landGetWayList = null;												//土地获取方式
		List<DictDetail> busCompoList = null;												//业态组成下拉框
		List<DictDetail> progressList = null;												//项目进度状态
		List<DictDetail> certBuildAreaList = null;											//证载建面
		ProjectInfo projectInfo = null;
		String type = req.getParameter("type");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(subjectFinanceVo !=null){
				/** 加载  财务管理模块 --- 投资主体财务管理 （copy investSubjectFinanceController的  toSubjectFinanceDetail 方法）*/
				String projectPid = subjectFinanceVo.getProjectPid();
				String subjectPid = subjectFinanceVo.getSubjectPid();
				if(StringUtils.isNotBlank(subjectPid) && StringUtils.isNotBlank(projectPid)){
					map = this.investSubjectFinanceService.getSubjectFinanceDetail(subjectPid, projectPid);
				}
				
				List<DictDetail> corDataInfoList = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_DATA_TYPE);
				List<DictDetail> bankList = dictInfoService.getDictDetailsByCode(TagConstants.BANK_NAME);
				model.addAttribute("investSubject", map.get("investSubject"));
				model.addAttribute("corporationInfo", map.get("corporationInfo"));
				model.addAttribute("corDataInfoList", corDataInfoList);
				model.addAttribute("bankList", bankList);
				
				
				
				/** 加载 项目基本信息、项目合同（copy ProjectManageController的 toProject 方法）*/
				//获取项目公司信息,开发商信息,省级信息,项目种类,土地获取方式,业态组成下拉框,项目进度状态,证载建面
				BusinessPrjInfo busProInfo = new BusinessPrjInfo();
				busProInfo.setStatus("1");
				busProInfoList = businessPrjInfoService.getBusProjInfoByCondition(busProInfo);
				
				corInfoList = corporationService.getAllCorInfoList(1);
				provinceList = sysAreaInfoService.getSysAreaByLevel(TagConstants.PROVINCE_CODE);
				proCateGoryList = dictInfoService.getDictDetailsByCode(TagConstants.PROJECT_CATEGORY);
				landGetWayList = dictInfoService.getDictDetailsByCode(TagConstants.LAND_GET_WAY);
				busCompoList = dictInfoService.getDictDetailsByCode(TagConstants.BUSINESS_COMPOSITION);
				progressList = dictInfoService.getDictDetailsByCode(TagConstants.PROJECT_PROGRESS);
				certBuildAreaList = dictInfoService.getDictDetailsByCode(TagConstants.CERT_BUILD_AREA);
				
				if(StringUtils.isNotBlank(projectPid)){
					//加载项目基本信息
					projectInfo = projectInfoService.getProjectInfoByKey(projectPid);												
				}
				model.addAttribute("type", type);
				model.addAttribute("projectInfo", projectInfo);
				model.addAttribute("corInfoList", corInfoList);
				model.addAttribute("busCompoList", busCompoList);
				model.addAttribute("provinceList", provinceList);
				model.addAttribute("busProInfoList", busProInfoList);
				model.addAttribute("landGetWayList", landGetWayList);
				model.addAttribute("proCateGoryList", proCateGoryList);
				model.addAttribute("progressList", progressList);
				model.addAttribute("certBuildAreaList", certBuildAreaList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "trade/invest_subject_business_manage/invest_subject_business_see";
	}
	
	
}
