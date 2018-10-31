package com.upjf.fund.web.controller.business.trade;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.InvestManagePlan;
import com.upjf.fund.dto.InvestSubject;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.InvestPlanManageService;
import com.upjf.fund.service.business.InvestSubjectService;
import com.upjf.fund.service.business.InvesterManageService;
import com.upjf.fund.utils.SplitCommaUtils;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.file.upload.FileUpload;

/**
 * 资管计划管理 
 * @author zhangcai
 * @time：2018年10月11日下午2:28:47
 * @version：
 * @ClassAnnotation：资管计划管理 
 *
 */
@Controller
@RequestMapping("/investPlanManage")
public class InvestPlanManageController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(InvestPlanManageController.class);
	
	/**新增操作*/
	protected static final String OPERATION_TYPE_ADD = "add";
	
	/**修改操作*/
	protected static final String OPERATION_TYPE_MODIFY = "modify";
	
	@Autowired
	protected InvestPlanManageService investPlanManageService;
	
	@Autowired
	protected InvestSubjectService investSubjectService;
	
	@Autowired
	protected CorporationService corporationService;
	
	@Autowired
	protected InvesterManageService investerManageService;
	
	
	/**
	 * 资管计划管理index页
	 * @author zhangcai
	 * @return
	 */
	@RequestMapping("/toInvestPlanManageIndex")
	public String toInvestPlanManageIndex(){
		return "trade/invest_plan_manage/invest_plan_manage_index";
	}
	
	/**
	 * 查询资管计划管理index页列表
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.11
	 */
	@RequestMapping(value="/queryInvestPlanManageList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryInvestPlanManageList(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> condtions = new HashMap<String,String>();
		condtions.put("project_info_pid", request.getParameter("projectInfoPid"));
		condtions.put("business_prj_info_pid", request.getParameter("businessPrjInfoPid"));
		condtions.put("corporation_info_pid", request.getParameter("corporationInfoPid"));
		List<Map<String, Object>> rows = this.investPlanManageService.getInvestPlanManageByConditon(condtions,getOffset(), getPageRows());
		Integer records = this.investPlanManageService.countInvestPlanManageByConditon(condtions);
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增、修改资管计划管理页面
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.11
	 */
	@RequestMapping("/toInvestPlanManageAddPage")
	public String toInvestPlanManageAddPage(Model model, HttpServletRequest request,HttpServletResponse response){
		String investPlanManageOperation = request.getParameter("operation");
		String investManagePlanPid = request.getParameter("investManagePlanPid");
		try {
			if(StringUtils.isNotBlank(investManagePlanPid) && (OPERATION_TYPE_MODIFY.equals(investPlanManageOperation))){
				InvestManagePlan investPlanManage = this.investPlanManageService.getInvestPlanManageByPrimaryKey(investManagePlanPid);
				model.addAttribute("investPlanManage", investPlanManage);
			}
			model.addAttribute("investPlanManageOperation", investPlanManageOperation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trade/invest_plan_manage/invest_plan_manage_add";
	}
	
	/**
	 * 新增或者修改资管计划管理
	 * @author zhangcai
	 * @param operation
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addInvestPlanManage")
	public @ResponseBody ModelMap addInvestPlanManage(InvestManagePlan investPlanManage,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		
		String investPlanManagePid = request.getParameter("investPlanManagePid");
		String projectInfoPid = request.getParameter("projectInfoPid");
		String businessPrjInfoPid = request.getParameter("businessPrjInfoPid");
		String stockholderInfoPid = request.getParameter("stockholderInfoPid");
		
		UserInfo userInfo = getUserInfo();
		if(investPlanManage!=null){
			investPlanManage.setPid(investPlanManagePid);
			investPlanManage.setPrjId(projectInfoPid);
			investPlanManage.setBusinessPrjInfoId(businessPrjInfoPid);
			investPlanManage.setStockholderId(stockholderInfoPid);
			
			//修改操作
			if(StringUtils.isNotBlank(investPlanManagePid)){
				mm.put("investPlanManagePid", investPlanManage.getPid());
				investPlanManage.setUpdateId(userInfo.getPid());
				investPlanManage.setUpdateDate(new Date());
				int result = this.investPlanManageService.updateInvestPlanManageByPid(investPlanManage);
				if(result>0){
					mm.put("projectInfoPid", projectInfoPid);
					mm.put("businessPrjInfoPid", businessPrjInfoPid);
					mm.put("successMsg", "更新资管计划信息成功");
				}else{
					mm.put("errMsg", "更新资管计划信息失败");
				}
				return mm;
			}
			// 新增操作
			else{
				
				/**
				 * 根据项目名称Pid、项目公司Pid、股东 PID 查询资管计划表，校验是否重复
				 * */
				InvestManagePlan existInvestPlanManage = this.investPlanManageService.getInvestPlanManageByAllPid(projectInfoPid,businessPrjInfoPid,stockholderInfoPid);
				if(existInvestPlanManage != null) {
					mm.put("errMsg", "该资管计划记录已存在，请修改！");
					return mm;
				}
				
				String pid = UuidGenerator.getUuidGenerator();
				investPlanManage.setPid(pid);
				investPlanManage.setCreateId(userInfo.getPid());
				investPlanManage.setCreateDate(new Date());
				int result = this.investPlanManageService.insertInvestPlanManageByPid(investPlanManage);
				if(result>0){
					mm.put("investPlanManagePid", pid);
					mm.put("projectInfoPid", projectInfoPid);
					mm.put("businessPrjInfoPid", businessPrjInfoPid);
					mm.put("successMsg", "新增资管计划信息成功");
				}else{
					mm.put("errMsg", "新增资管计划信息失败");
				}
				return mm;
			}
			
			
		}else{
			mm.put("errMsg", "资管计划信息不存在");
		}
		
		return mm;
	}
	
	/**
	 * 新增、修改资管计划管理页面----投资主体管理列表
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.11
	 */
	@RequestMapping(value="/queryInvestSubjectList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryInvestSubjectList(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		String investPlanManagePid = request.getParameter("investPlanManagePid");
		List<Map<String, Object>> rows = this.investPlanManageService.queryInvestSubjectList(investPlanManagePid,getOffset(), getPageRows());
		Integer records = this.investPlanManageService.countInvestSubjectList(investPlanManagePid);
		return putJsonData(rows, records);
	}
	
	/**
	 * 资管计划管理--->查看付款信息页面的
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.24
	 */
	@RequestMapping("/toInvestSubjectPaymentInfo")
	public String toInvestSubjectPaymentInfo(Model model, HttpServletRequest request,HttpServletResponse response){
		Map<String,String> condtions = new HashMap<String,String>();
		String investSubjectName = request.getParameter("investSubjectName");
		String investPlanManagePid = request.getParameter("investPlanManagePid");
		String investSubjectPid = request.getParameter("investSubjectPid");
		String accessType = request.getParameter("accessType");
		
		condtions.put("investPlanManagePid", investPlanManagePid);
		condtions.put("investSubjectPid", investSubjectPid);
		condtions.put("accessType", accessType);
		
		try {
			Map<String,Object> amountMap = this.investerManageService.statisticsAmountByCondition(condtions, EstateFundConstants.PAYMENT_TYPE_INVEST_SUBJECT);
			model.addAttribute("amount", amountMap);
			model.addAttribute("investSubjectName", URLDecoder.decode(investSubjectName,"UTF-8"));
			model.addAttribute("investPlanManagePid", investPlanManagePid);
			model.addAttribute("investSubjectPid", investSubjectPid);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "trade/invest_plan_manage/view_invest_subject_payment";
	}
	
	/**
	 * 新增/修改投资主体
	 * @author zhangcai
	 * @date 2018.10.15
	 * @param userInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addInvestSubject")
	public @ResponseBody ModelMap addInvestSubject(InvestSubject investSubject, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		UserInfo userInfo = getUserInfo();
		if(investSubject==null){
			mm.put("errMsg", "投资主体信息为空!");
			return mm;
		}
		String investPlanManagePid = request.getParameter("investPlanManagePid");
		investSubject.setInvestManangeId(investPlanManagePid);
		
		String businessPrjInfoPid = request.getParameter("businessPrjInfoPid");
		investSubject.setPrjCorpId(businessPrjInfoPid);
		
		// 投资主体名称 
		String investSubjectName = request.getParameter("investSubjectName");
		investSubject.setInvestSubjectId(investSubjectName);
		// 主键
		String investSubjectPid = request.getParameter("investSubjectPid");
		
		
		if(StringUtils.isEmpty(investSubject.getPrjId())){
			mm.put("errMsg", "请选择项目名称!");
			return mm;
		}
		if(StringUtils.isEmpty(investSubject.getPrjCorpId())){
			mm.put("errMsg", "请选择项目公司!");
			return mm;
		}
		if(StringUtils.isEmpty(investSubject.getInvestSubjectId())){
			mm.put("errMsg", "请选择投资主体名称!");
			return mm;
		}
		try {
			int result = 0;
			
			// 修改
			if(StringUtils.isNotBlank(investSubjectPid)) {
				investSubject.setPid(investSubjectPid);
				investSubject.setUpdateDate(new Date());
				investSubject.setUpdateId(userInfo.getPid());
				result = this.investSubjectService.updateInvestSubjectByPrimaryKey(investSubject);
				if(result > 0){
					mm.put("investSubjectPid", investSubjectPid);
					mm.put("successMsg", "修改投资主体成功!");
				}else{
					mm.put("errMsg", "修改投资主体失败!");
				}
			} 
			// 新增
			else {
				/* 根据选中的投资主体名称 PID 查询投资主体表，校验是否重复
				 * 当前资管计划下对应的投资主体Pid不能重复
				 * */
				if(StringUtils.isNotBlank(investPlanManagePid) && StringUtils.isNotBlank(investSubjectName)) {
					InvestSubject existInvestSubject = this.investSubjectService.getInvestSubjectByInvestPlanPidAndPid(investPlanManagePid,investSubjectName);
					if(existInvestSubject != null) {
						CorporationInfo corporationInfo = this.corporationService.getCorByPrimaryKey(existInvestSubject.getInvestSubjectId());
						mm.put("errMsg", String.format("投资主体名称  %s - %s 已存在", corporationInfo.getName(),corporationInfo.getSimpleName()));
						return mm;
					}
				}
				
				//插入投资主体表
				String pid = UuidGenerator.getUuidGenerator();
				investSubject.setPid(pid);
				investSubject.setInvestType("1");
				investSubject.setCreateId(userInfo.getPid());
				investSubject.setCreateDate(new Date());
				result = this.investSubjectService.insertInvestSubject(investSubject);
				if(result > 0){
					mm.put("investSubjectPid", pid);
					mm.put("successMsg", "新增投资主体成功");
				}else{
					mm.put("errMsg", "新增投资主体失败");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增/修改投资主体失败!");
			mm.put("errMsg", "系统出错!");
		}
		return mm;
	}
	
	/**
	 * 删除投资主体（可批量删除）
	 * @author zhangcai
	 * @param investorPids
	 * @return
	 * @date 2018.10.25
	 */
	@RequestMapping(value="/deleteInvestSubjects",method=RequestMethod.POST)
	private @ResponseBody ModelMap deleteInvestSubjects(HttpServletRequest request, HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String investSubjectPids = request.getParameter("investSubjectPids");
		UserInfo userInfo = getUserInfo();
		String curPid = userInfo.getPid();
		try {
			if(StringUtils.isBlank(investSubjectPids)){
				mm.put("errMsg", "删除失败,请选择数据!");
			}else{
				List<String> pids = SplitCommaUtils.splitCorPids(investSubjectPids);
				int result = this.investSubjectService.deleteInvestSubjectByPids(pids,curPid,EstateFundConstants.INVEST_TYPE_INVEST_SUBJECT);
				
				if(result <= 0){
					mm.put("errMsg", "删除失败!");
				}else{
					mm.put("successMsg", "删除成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除投资主体失败!");
			mm.put("errMsg", "系统出错!");
		}
		
		return mm;
	}
	
	
	/**
	 * 资管计划管理---新增资管计划---新增投资主体---上传投资主体附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.15
	 */
	@RequestMapping("/uploadInvestSubjectAccessory")
	public @ResponseBody ModelMap uploadInvestSubjectAccessory(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		List<EstateFundFile> files = null;
		try {
			String investSubjectPid = request.getParameter("pid");
			// accessoryType==1:投资主体附件；accessoryType==2:投资人附件；
			String accessoryType = request.getParameter("accessoryType");
			UserInfo userInfo = getUserInfo();
			if(StringUtils.isNotBlank(investSubjectPid) && StringUtils.isNotBlank(accessoryType)){
				if("1".equals(accessoryType)) {
					files = FileUpload.uploadFile(request, response, EstateFundModelConstants.ADD_INVEST_SUBJECT_ACCESSORY);
				} else if("2".equals(accessoryType)) {
					files = FileUpload.uploadFile(request, response, EstateFundModelConstants.ADD_INVESTOR_ACCESSORY);
				}
				this.investSubjectService.batchInsertInvestSubjectAccessory(accessoryType,files,investSubjectPid,userInfo.getPid());
				if(files!=null && files.size()>0){
					mm.put("successMsg", "上传成功");
					return mm;
				}
			}else{
				mm.put("errMsg", "请先保存数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("上传投资主体附件失败!");
			mm.put("errMsg", "系统出错!");
		}
		return mm;
	}
	
	/**
	 * 资管计划管理---新增资管计划---新增投资主体---查询投资主体附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.10.15
	 */
	@RequestMapping(value="/queryInvestSubjectAccessoryList", produces = "application/json;charset=utf-8")
	public @ResponseBody String queryInvestSubjectAccessoryList(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		Integer records = 0;
		try {
			String investSubjectPid = request.getParameter("pid");
			if(StringUtils.isNotEmpty(investSubjectPid)){
				rows = this.investSubjectService.getInvestSubjectAccessoryByInvestSubjectId(investSubjectPid, getOffset(), getPageRows());
				records = this.investSubjectService.countInvestSubjectAccessoryByInvestSubjectId(investSubjectPid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询投资主体附件失败!");
		}
		return putJsonData(rows, records);
	}
	
	
	
	

}
