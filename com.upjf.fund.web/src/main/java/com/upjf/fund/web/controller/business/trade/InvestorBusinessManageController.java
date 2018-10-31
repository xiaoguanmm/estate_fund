package com.upjf.fund.web.controller.business.trade;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.constants.TagConstants;
import com.upjf.fund.dto.BusinessPrjInfo;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.InvestSubject;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.dto.SysAreaInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.business.BusinessPrjInfoService;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.InvestSubjectService;
import com.upjf.fund.service.business.InvestorFinanceService;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.service.system.SysAreaInfoService;
import com.upjf.fund.utils.SplitCommaUtils;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.controller.base.BaseController;

/**
 * 投资人业务管理 Controller
 * @author zhangcai
 * @time：2018年10月19日下午2:28:47
 * @version：
 * @ClassAnnotation：投资人业务管理 
 *
 */
@Controller
@RequestMapping("/investorBusinessManage")
public class InvestorBusinessManageController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(InvestorBusinessManageController.class);
	
	@Autowired
	protected InvestSubjectService investSubjectService;
	
	@Autowired
	protected CorporationService corporationService;
	
	@Autowired
	protected InvestorFinanceService investorFinanceService;
	
	@Autowired
	protected DictInfoService dictInfoService;
	
	@Autowired
	protected BusinessPrjInfoService businessPrjInfoService;
	
	@Autowired
	protected ProjectInfoService projectInfoService;
	
	@Autowired
	protected SysAreaInfoService sysAreaInfoService;
	
	/**
	 * 跳转至投资人信息管理页面
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.19
	 */
	@RequestMapping("/toInvestorsInfoManagePage")
	public String toInvestorsInfoManagePage(Model model, HttpServletRequest request,HttpServletResponse response){
		String investSubjectPid = request.getParameter("investSubjectPid");
		String investPlanManagePid = request.getParameter("investPlanManagePid");
		// accessType=1 代表从资管计划页面进入
		String accessType = request.getParameter("accessType");
		
		// 通过资管计划Pid（investPlanManagePid）、投资主体Pid（investSubjectPid）查询新增投资人时所需字段
		if(StringUtils.isNotBlank(investPlanManagePid) && StringUtils.isNotBlank(investSubjectPid)) {
			Map<String, Object> map = this.investSubjectService.queryInvestSubjectAndPrjInfo(investPlanManagePid,investSubjectPid);
			if(map!=null && map.size()>0) {
				String projectInfoPid = (String) map.get("projectInfoPid");
				String buildName = (String) map.get("building_name");
				String stockholderCorpPId = (String) map.get("stockholderCorpPId");
				String investSubjectCorpId = (String) map.get("investSubjectCorpId");
				String businessPrjInfoPid = (String) map.get("business_prj_info_id");
				String stockholderPid = (String) map.get("stockholder_id");
				model.addAttribute("projectInfoPid", projectInfoPid);
				model.addAttribute("buildName", buildName);
				model.addAttribute("stockholderCorpId", stockholderCorpPId);
				model.addAttribute("investSubjectCorpId", investSubjectCorpId);
				model.addAttribute("businessPrjInfoPid", businessPrjInfoPid);
				model.addAttribute("stockholderPid", stockholderPid);
			}
		}
		model.addAttribute("investSubjectPid", investSubjectPid);
		model.addAttribute("investPlanManagePid", investPlanManagePid);
		model.addAttribute("accessType", accessType);
		return "trade/investor_business_manage/investor_business";
	}
	
	
	/**
	 * 投资人业务管理index页 列表
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.19
	 */
	@RequestMapping(value="/queryInvestorBusinessManageList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryInvestorBusinessManageList(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		String investPlanManagePid = request.getParameter("investPlanManagePid");
		String investSubjectPid = request.getParameter("investSubjectPid");
		
		Map<String,String> condtions = new HashMap<String,String>();
		condtions.put("projectInfoPid", request.getParameter("projectInfoPid"));
		condtions.put("investSubjectCorpId", request.getParameter("investSubjectCorpId"));
		condtions.put("investorCorpId", request.getParameter("investorCorpId"));
		
		condtions.put("investPlanManagePid", investPlanManagePid);
		condtions.put("investSubjectPid", investSubjectPid);
		List<Map<String, Object>> rows = this.investSubjectService.queryInvestorBusinessManageList(condtions,getOffset(), getPageRows());
		Integer records = this.investSubjectService.countInvestorBusinessManageList(condtions);
		modelMap.put("investPlanManagePid",investPlanManagePid);
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增/修改投资人
	 * @author zhangcai
	 * @date 2018.10.22
	 * @param userInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addInvestor")
	public @ResponseBody ModelMap addInvestor(InvestSubject investor, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		UserInfo userInfo = getUserInfo();
		if(investor==null){
			mm.put("errMsg", "投资人信息为空!");
			return mm;
		}
		
		String businessPrjInfoPid = request.getParameter("businessPrjInfoPid");
		investor.setPrjCorpId(businessPrjInfoPid);
		// 股东名称
		String stockholderCorpId = request.getParameter("stockholderCorpId");
		// 投资主体名称
		String investSubjectCorPid = request.getParameter("investSubjectCorPid");
		investor.setInvestSubjectId(investSubjectCorPid);
		// 投资人名称
		String investorCorPid = request.getParameter("investorName");
		investor.setInvestSubjectId(investorCorPid);
		// 投资主体Pid
		String investSubjectPid = request.getParameter("investSubjectPid");
		investor.setParentId(investSubjectPid);
		// 资产管理计划Pid
		String investPlanManagePid = request.getParameter("investPlanManagePid");
		investor.setInvestManangeId(investPlanManagePid);
		// 主键
		String investorPid = request.getParameter("investorPid");
		
		if(StringUtils.isEmpty(investor.getPrjId())){
			mm.put("errMsg", "项目名称不能为空!");
			return mm;
		}
		if(StringUtils.isEmpty(stockholderCorpId)){
			mm.put("errMsg", "股东名称不能为空!");
			return mm;
		}
		if(StringUtils.isEmpty(investSubjectCorPid)){
			mm.put("errMsg", "投资主体名称不能为空!");
			return mm;
		}
		if(StringUtils.isEmpty(investorCorPid)){
			mm.put("errMsg", "投资人名称不能为空!");
			return mm;
		}
		try {
			int result = 0;
			// 修改
			if(StringUtils.isNotBlank(investorPid)) {
				investor.setPid(investorPid);
				investor.setUpdateDate(new Date());
				investor.setUpdateId(userInfo.getPid());
				result = this.investSubjectService.updateInvestSubjectByPrimaryKey(investor);
				if(result > 0){
					mm.put("investorPid", investorPid);
					mm.put("successMsg", "修改投资人成功!");
				}else{
					mm.put("errMsg", "修改投资人失败!");
				}
			} 
			// 新增
			else {
				/* 根据资管计划Pid、投资主体Pid、投资人名称 PID 查询投资主体表，校验是否重复
				 * 同一个资管计划、投资主体下投资人Pid不能重复
				 * */
				if(StringUtils.isNotBlank(investPlanManagePid) && StringUtils.isNotBlank(investSubjectPid) && StringUtils.isNotBlank(investorCorPid)) {
					InvestSubject existInvestor = this.investSubjectService.getInvestorByInvestPlanPidAndInvestPid(investPlanManagePid,investSubjectPid,investorCorPid);
					if(existInvestor != null) {
					CorporationInfo corporationInfo = this.corporationService.getCorByPrimaryKey(existInvestor.getInvestSubjectId());
						mm.put("errMsg", "当前资管计划下，投资主体对应的投资人: "+corporationInfo.getName()+" 已存在,不能再次新增!");
						return mm;
					}
				}
				
				//插入投资主体表
				String pid = UuidGenerator.getUuidGenerator();
				investor.setPid(pid);
				investor.setInvestType("2");
				investor.setCreateId(userInfo.getPid());
				investor.setCreateDate(new Date());
				result = this.investSubjectService.insertInvestSubject(investor);
				if(result > 0){
					mm.put("investorPid", pid);
					mm.put("successMsg", "新增投资人成功！");
				}else{
					mm.put("errMsg", "新增投资人失败！");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增/修改投资人失败!");
			mm.put("errMsg", "系统出错!");
		}
		return mm;
	}
	
	/**
	 * 删除投资人（可批量删除）
	 * @author zhangcai
	 * @param investorPids
	 * @return
	 * @date 2018.10.25
	 */
	@RequestMapping(value="/deleteInvestors",method=RequestMethod.POST)
	private @ResponseBody ModelMap deleteInvestors(HttpServletRequest request, HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String investorPids = request.getParameter("investorPids");
		UserInfo userInfo = getUserInfo();
		String curPid = userInfo.getPid();
		try {
			if(StringUtils.isBlank(investorPids)){
				mm.put("errMsg", "删除失败,请选择数据!");
			}else{
				List<String> pids = SplitCommaUtils.splitCorPids(investorPids);
				int result = this.investSubjectService.deleteInvestSubjectByPids(pids,curPid,EstateFundConstants.INVEST_TYPE_INVESTOR);
				
				if(result <= 0){
					mm.put("errMsg", "删除失败!");
				}else{
					mm.put("successMsg", "删除成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除投资人信息失败!");
			mm.put("errMsg", "系统出错!");
		}
		
		return mm;
	}
	
	/**
	 * 进入投资人业务管理---查看详情页面
	 * @author  zhangcai 
	 * @param   investorPid
	 * @param   projectPid
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年10月17日
	 */
	@RequestMapping(value="/toInvestorBusinessManangeDetail")
	private String toInvestorBusinessManangeDetail(String investorPid,String projectPid,Model model,HttpServletRequest req){
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
			/** 加载  财务管理模块 --- 投资人财务管理 （copy investorFinanceController 的  toInvestorFinanceDetail 方法）*/
			if(StringUtils.isNotBlank(investorPid)){
				map = investorFinanceService.getInvestorFinanceDetail(investorPid);
			}
			
			List<DictDetail> corDataInfoList = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_DATA_TYPE);
			List<DictDetail> bankList = dictInfoService.getDictDetailsByCode(TagConstants.BANK_NAME);
			model.addAttribute("investor", map.get("investor"));
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
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "trade/investor_business_manage/investor_business_see";
	}

}
