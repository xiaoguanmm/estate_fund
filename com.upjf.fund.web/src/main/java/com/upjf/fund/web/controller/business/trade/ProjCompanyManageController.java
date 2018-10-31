package com.upjf.fund.web.controller.business.trade;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

import com.google.common.base.Joiner;
import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.dto.BusinessPrjInfo;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.Payment;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.dto.StockholderInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.business.BusinessPrjInfoService;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.InvesterManageService;
import com.upjf.fund.service.business.PrjCompanyContractService;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.business.ReceivedManageService;
import com.upjf.fund.service.business.StockRightsChangeService;
import com.upjf.fund.service.business.StockholderInfoService;
import com.upjf.fund.utils.SplitCommaUtils;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.file.upload.FileUpload;

/**
 * 项目公司管理
 * @author zhangcai
 * @time：2018年9月18日下午2:28:47
 * @version：
 * @ClassAnnotation： 项目公司管理
 *
 */
@Controller
@RequestMapping("/tradeManage")
public class ProjCompanyManageController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(ProjCompanyManageController.class);
	
	/**新增操作*/
	protected static final String OPERATION_TYPE_ADD = "add";
	
	/**修改操作*/
	protected static final String OPERATION_TYPE_MODIFY = "modify";
	
	/**查看操作*/
	protected static final String OPERATION_TYPE_VIEW = "view";
	
	@Autowired
	protected CorporationService corporationService;
	
	@Autowired
	protected BusinessPrjInfoService businessPrjInfoService;
	
	@Autowired
	protected StockholderInfoService stockholderInfoService;
	
	@Autowired
	protected StockRightsChangeService stockRightsChangeService;
	
	@Autowired
	protected PrjCompanyContractService prjCompanyContractService;
	
	@Autowired
	protected InvesterManageService investerManageService;
	
	@Autowired
	protected ReceivedManageService receivedManageService;
	
	@Autowired
	private ProjectInfoService projectInfoService;	
	
	/**
	 * 项目公司管理index页
	 * @author zhangcai
	 * @return
	 */
	@RequestMapping("/projectEnterpriseManage")
	public String toList(){
		return "trade/proj_company_manage/proj_company_manage_index";
	}
	
	/**
	 * 查询项目公司管理index页列表
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryProjCompanyList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryProjCompanyList(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> condtions = new HashMap<String,String>();
		condtions.put("businessPrjInfoPid", request.getParameter("businessPrjInfoPid"));
		condtions.put("orgCodeCert", request.getParameter("orgCodeCert"));
		condtions.put("businessLicenceCode", request.getParameter("businessLicenceCode"));
		condtions.put("projectInfoPid", request.getParameter("projectInfoPid"));
		condtions.put("legalRepresentative", request.getParameter("legalRepresentative"));
		List<Map<String, Object>> rows = this.businessPrjInfoService.getProjCompanyByConditon(condtions,getOffset(), getPageRows());
		Integer records = this.businessPrjInfoService.countProjCompanyByCondition(condtions);
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增、修改、查看项目公司页面
	 * @author zhangcai
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toProjCompanyAddPage")
	public String toProjCompanyAddPage(Model model, HttpServletRequest request,HttpServletResponse response){
		String tabType = request.getParameter("tabType");
		String projCompanyOperation = request.getParameter("operation");
		String businessPrjInfoId = request.getParameter("businessPrjPid");
		String projectInfoPid = request.getParameter("projectInfoPid");
		try {
			if(StringUtils.isNotBlank(businessPrjInfoId) && (OPERATION_TYPE_MODIFY.equals(projCompanyOperation) || OPERATION_TYPE_VIEW.equals(projCompanyOperation))){
				BusinessPrjInfo businessPrjInfo = this.businessPrjInfoService.selectByPrimaryKey(businessPrjInfoId);
				if(businessPrjInfo != null) {
					CorporationInfo corporationInfo = this.corporationService.getCorByPrimaryKey(businessPrjInfo.getCorporationInfoId());
					model.addAttribute("corporationInfo", corporationInfo);
				}
				model.addAttribute("businessPrjInfo", businessPrjInfo);
			}
			model.addAttribute("projectInfoPid", projectInfoPid);
			model.addAttribute("tabType", tabType);
			model.addAttribute("projCompanyOperation", projCompanyOperation);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return "trade/proj_company_manage/proj_company_manage_add";
	}
	
	/**
	 * 查询企业信息表
	 * @author zhangcai
	 * @param userInfo
	 * @param request
	 * @param response
	 */
	@RequestMapping("/queryCorporationByPrimaryKey")
	public void queryCorporationByPrimaryKey(String corporationPid, HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		CorporationInfo existCorporationInfo = null;
		try {
			// 根据主键  查询数据库中已有的企业
			existCorporationInfo = this.corporationService.getCorByPrimaryKey(corporationPid);
			if(null != existCorporationInfo) {
				resultMap.put("existCorporationInfo", existCorporationInfo);
				resultMap.put("code", EstateFundConstants.MessageCode.SUCCESS.getCode());
				resultMap.put("msg", EstateFundConstants.MessageCode.SUCCESS.getMsg());
			} else {
				resultMap.put("code", EstateFundConstants.MessageCode.PROJ_COMPANY_100000.getCode());
				resultMap.put("msg", EstateFundConstants.MessageCode.PROJ_COMPANY_100000.getMsg());
			}
		} catch (Exception e) {
			resultMap.put("code", EstateFundConstants.MessageCode.FAIL.getCode());
			resultMap.put("msg", EstateFundConstants.MessageCode.FAIL.getMsg());
			e.printStackTrace();
		}
		// 输出
		outPutJson(resultMap,response);
	}
	
	
	
	/**
	 * 新增、修改项目公司基本信息
	 * @author zhangcai
	 * @param corporationInfo
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addProjCompanyBaseInfo")
	public @ResponseBody ModelMap addProjCompanyBaseInfo(CorporationInfo corporationInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		UserInfo userInfo = getUserInfo();
		if(corporationInfo==null){
			mm.put("errMsg", "项目公司基本信息为空");
			return mm;
		}
		String prjCorpName = request.getParameter("prjCorpName");
		String businessPrjInfoId = request.getParameter("businessPrjInfoId");
		String corporationInfoPid = request.getParameter("corpPid");
		corporationInfo.setPid(corporationInfoPid);
		
		if(StringUtils.isEmpty(corporationInfo.getName()) || StringUtils.isEmpty(corporationInfo.getOrgCodeCert()) || 
				StringUtils.isEmpty(corporationInfo.getBusinessLicenceCode()) || StringUtils.isBlank(prjCorpName)){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		
		try {
			/*********** 新增操作**********/
			if(StringUtils.isBlank(businessPrjInfoId)) {
				int result = 0;
				String businessPrjInfoPid = UuidGenerator.getUuidGenerator();
				// 1.【选择企业】下拉框不选择，输入框手动录入企业；增加   企业、企业项目关系表
				if("-1".equals(corporationInfoPid)) {
					corporationInfo.setCreateDate(new Date());
					corporationInfo.setCreateId(userInfo.getPid());
					Map<String,Object> resualtMap = this.corporationService.insertCorporation(corporationInfo);
					if(null != resualtMap) {
						String code = (String) resualtMap.get("success");
						String msg = (String) resualtMap.get("msg");
						if("s".equals(code)) {
							corporationInfoPid = (String) resualtMap.get("newPid");
							// 插入企业表成功后    才插入企业项目关系表
							result = this.businessPrjInfoService.insertBusinessPrjInfo(businessPrjInfoPid,prjCorpName,corporationInfoPid,userInfo.getPid());
						} else {
							mm.put("errMsg", msg);
						}
					}
				}
				// 下拉框选择已有企业   只增加  企业项目关系表
				else {
					// 通过企业Pid查找该企业下所有的项目公司
					List<BusinessPrjInfo> businessPrjInfoList = this.businessPrjInfoService.getBusinessPrjInfoByCorPid(corporationInfoPid);
					if(null != businessPrjInfoList && businessPrjInfoList.size() > 0 && StringUtils.isNotBlank(prjCorpName)) {
						for (BusinessPrjInfo busPrjInfo : businessPrjInfoList) {
							if(prjCorpName.equals(busPrjInfo.getPrjCorpName())) {
								mm.put("errMsg", corporationInfo.getName()+"  对应的项目公司  "+busPrjInfo.getPrjCorpName() + "  已存在,请重新输入！");
								return mm;
							}
						}
					}
					result = this.businessPrjInfoService.insertBusinessPrjInfo(businessPrjInfoPid,prjCorpName,corporationInfoPid,userInfo.getPid());
				}
				
				if(result > 0){
					mm.put("businessPrjInfoId", businessPrjInfoPid);
					mm.put("successMsg", "新增项目公司成功");
				}else{
					mm.put("errMsg", "新增项目公司失败");
				}
				
			} 
			
			
			/*********** 修改操作**********/
			else {
				// 同时修改企业表、企业项目关系表
				BusinessPrjInfo businessPrj = this.businessPrjInfoService.selectByPrimaryKey(businessPrjInfoId);
				int corporationResult = this.corporationService.updateCorInfoByKey(corporationInfo);
				int businessPrjInfoResult = 0;
				if(corporationResult > 0) {
					businessPrj.setPrjCorpName(prjCorpName);
					businessPrj.setUpdateId(userInfo.getPid());
					businessPrj.setUpdateDate(new Date());
					businessPrjInfoResult = this.businessPrjInfoService.updateBusinessPrjInfo(businessPrj);
				}
				if(corporationResult > 0 && businessPrjInfoResult > 0){
					mm.put("businessPrjInfoId", businessPrjInfoId);
					mm.put("successMsg", "修改项目公司成功");
				}else{
					mm.put("errMsg", "修改项目公司失败");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return mm;
	}
	
	/**
	 * 关联项目公司信息
	 * @author zhangcai
	 * @return
	 * @date 2018.10.22
	 */
	@RequestMapping(value="/linkedToProject",method=RequestMethod.POST)
	private @ResponseBody ModelMap linkedToProject(HttpServletRequest request, HttpServletResponse response){
		ModelMap mm = new ModelMap();
		// 选择要关联的项目Pid
		String projectInfoPid = request.getParameter("projectInfoPid");
		// 项目公司列表中关联的项目Pid
		String linkedPrjId = request.getParameter("linkedPrjId");
		// 项目公司列表中 项目公司Pid
		String businessPrjPid = request.getParameter("businessPrjPid");
		
		UserInfo userInfo = getUserInfo();
		int projectUpdateResult = 0;
		int stockUpdateResult = 0;
		try {
			if(StringUtils.isBlank(projectInfoPid)){
				mm.put("errMsg", "请选择需要关联的项目数据!");
				return mm;
			}
			ProjectInfo projectInfo = projectInfoService.getProjectInfoByKey(projectInfoPid);
			
			String stockholderPids = "";
			List<String> pids = null;
			// 根据prj_id(linkedPrjId)、business_prj_info_id(businessPrjPid) 查询股东表 
			List<String> stockholderList = this.stockholderInfoService.queryStockholderInfo(businessPrjPid, linkedPrjId);
			if(stockholderList != null && stockholderList.size() > 0) {
				stockholderPids = Joiner.on(",").join(stockholderList.toArray()); 
			}
			if(StringUtils.isNotBlank(stockholderPids)) {
				pids = SplitCommaUtils.splitCorPids(stockholderPids);
			}
			/**
			 * 一.项目公司管理列表中prj_id不存在（未关联项目）
			 * 		1:有股东---> 变更所有的股东信息
			 * 		2：无股东---> 不作操作
			 * */
			if(StringUtils.isBlank(linkedPrjId)) {
				//变更该项目公司和该项目下所有的股东
				if(pids != null && pids.size() > 0) {
					stockUpdateResult = this.stockholderInfoService.updateStockholderInfoByPids(pids,projectInfoPid,userInfo.getPid());
				}
			} 
			
			/**
			 * 二.项目公司管理列表中prj_id存在（已关联项目，现要变更关联项目）
			 * 		1:通过prj_id查询在收付款表中有记录---> 不允许变更
			 * 		2：通过prj_id查询在收付款表中无记录---> 2.1：有股东---> 变更所有的股东信息
			 * 									   2.2：无股东---> 不作操作
			 * */
			if(StringUtils.isNotBlank(linkedPrjId)) {
				List<Map<String, Object>> paymentList = investerManageService.queryPaymentList(linkedPrjId);
				List<Map<String, Object>> receivedList = receivedManageService.queryReceivedList(linkedPrjId);
				if((paymentList!=null && paymentList.size()>0) || (receivedList!=null && receivedList.size()>0)) {
					mm.put("errMsg", "该项目数据已经付款或回款，不能重新关联项目!");
					return mm;
				} else {
					if(pids != null && pids.size() > 0) {
						//变更该项目公司和该项目下所有的股东
						stockUpdateResult = this.stockholderInfoService.updateStockholderInfoByPids(pids,projectInfoPid,userInfo.getPid());
					}
				}
			}
			// 关联项目; 更新 project_info项目表
			projectInfo.setBusinessPrjInfoId(businessPrjPid);
			projectInfo.setUpdateId(userInfo.getPid());
			projectInfo.setUpdateDate(new Date());
			projectUpdateResult = projectInfoService.updateProjectInfoByKey(projectInfo);
			
			if(projectUpdateResult > 0){
				if(stockUpdateResult > 0) {
					mm.put("successMsg", "关联项目成功且更新股东成功!");
				} else {
					mm.put("successMsg", "关联项目成功,更新股东失败!");
				}
			}else{
				mm.put("errMsg", "关联项目失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("关联项目失败!");
			mm.put("errMsg", "系统出错!");
		}
		
		return mm;
	}
	
	/**
	 * 删除项目公司信息（可批量删除）
	 * @author zhangcai
	 * @param stockholderIds
	 * @return
	 * @date 2018.10.8
	 */
	@RequestMapping(value="/deleteBusinessPrjInfos",method=RequestMethod.POST)
	private @ResponseBody ModelMap deleteBusinessPrjInfos(HttpServletRequest request, HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String businessPrjPids = request.getParameter("businessPrjPids");
		UserInfo userInfo = getUserInfo();
		String curPid = userInfo.getPid();
		try {
			if(StringUtils.isBlank(businessPrjPids)){
				mm.put("errMsg", "删除失败,请选择数据!");
			}else{
				List<String> pids = SplitCommaUtils.splitCorPids(businessPrjPids);
				int result = this.businessPrjInfoService.delBusinessPrjInfoByPids(pids,curPid);
				
				if(result <= 0){
					mm.put("errMsg", "删除失败!");
				}else{
					mm.put("successMsg", "删除成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除项目公司信息失败!");
			mm.put("errMsg", "系统出错!");
		}
		
		return mm;
	}
	
	
	/**
	 * 上传项目公司合同附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.09.29
	 */
	@RequestMapping("/uploadProjCompContractAccessory")
	public @ResponseBody ModelMap uploadProjCompContractAccessory(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		try {
			String businessPrjInfoId = request.getParameter("businessPrjInfoId");
			String contractName = request.getParameter("contractName");
			UserInfo userInfo = getUserInfo();
			if(StringUtils.isNotBlank(businessPrjInfoId)){
				List<EstateFundFile> files = FileUpload.uploadFile(request, response, EstateFundModelConstants.PROJ_COMP_CONTRACT_ACCESSORY);
				this.prjCompanyContractService.batchInsertPrjCompanyContract(files,businessPrjInfoId,contractName,userInfo.getPid());
				if(files!=null && files.size()>0){
					mm.put("successMsg", "上传成功");
					return mm;
				}
			}else{
				mm.put("errMsg", "请先保存数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return mm;
	}
	
	/**
	 * 查询项目公司合同附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.09.29
	 */
	@RequestMapping(value="/queryProjCompContractAccessoryList", produces = "application/json;charset=utf-8")
	public @ResponseBody String queryProjCompContractAccessoryList(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		Integer records = 0;
		try {
			String businessPrjInfoId = request.getParameter("businessPrjInfoId");
			if(StringUtils.isNotEmpty(businessPrjInfoId)){
				rows = this.prjCompanyContractService.getPrjCompContractByBusPrjInfoId(businessPrjInfoId,getOffset(),getPageRows());
				records = this.prjCompanyContractService.countPrjCompContractByBusPrjInfoId(businessPrjInfoId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return putJsonData(rows, records);
	}
	
	
	/**
	 * 新增项目公司---项目公司股东信息 index
	 * 根据businessPrjInfoId查询该项目公司下对应的股东信息列表
	 * @author zhangcai
	 * @date 2018.09.21
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryProjCompanyStockholderList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryProjCompanyStockholderList(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		List<Map<String, Object>> rows = null;
		Integer records = 0;
		String businessPrjInfoId = request.getParameter("businessPrjInfoId");
		String projectInfoPid = request.getParameter("projectInfoPid");
		try {
			rows = this.stockholderInfoService.queryProjCompanyStockholderList(businessPrjInfoId, projectInfoPid, getOffset(), getPageRows());
			records = this.stockholderInfoService.countProjCompanyStockholderList(businessPrjInfoId, projectInfoPid);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return putJsonData(rows, records);
	}
	
	
	/**
	 * 新增、修改 股东公司信息
	 * @author zhangcai
	 * @date 2018.09.25
	 * @param userInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addStockholderInfo")
	public @ResponseBody ModelMap addStockholderInfo(StockholderInfo stockholderInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		UserInfo userInfo = getUserInfo();
		if(stockholderInfo==null){
			mm.put("errMsg", "股东信息为空");
			return mm;
		}
		// 得到企业Pid
		String corPid = request.getParameter("stockholderName");
		// 得到企业项目关系表Pid
		String businessPrjInfoId = request.getParameter("businessPrjInfoId");
		// 得到股东Pid
		String stockholderPid = request.getParameter("stockholderPid");
		
		String projectInfoPid = request.getParameter("projectInfoPid");
		if(StringUtils.isNotBlank(projectInfoPid)) {
			stockholderInfo.setPrjId(projectInfoPid);
		}
		
		if(StringUtils.isEmpty(corPid)){
			mm.put("errMsg", "请选择股东");
			return mm;
		}
		if(StringUtils.isEmpty(businessPrjInfoId)){
			mm.put("errMsg", "请保存项目公司基本信息");
			return mm;
		}
		try {
			stockholderInfo.setPid(stockholderPid);
			stockholderInfo.setCorpId(corPid);
			stockholderInfo.setBusinessPrjInfoId(businessPrjInfoId);
			if(stockholderInfo.getRegisterCapital() != null) {
				stockholderInfo.setRegisterCapital(stockholderInfo.getRegisterCapital().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
			}
			if(stockholderInfo.getStockholderContacts() != null) {
				stockholderInfo.setStockholderContacts(stockholderInfo.getStockholderContacts().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
			}
			//修改操作
			if(StringUtils.isNotBlank(stockholderPid)){
				stockholderInfo.setUpdateId(userInfo.getPid());
				stockholderInfo.setUpdateDate(new Date());
				int result = this.stockholderInfoService.updateByPrimaryKeySelective(stockholderInfo);
				if(result>0){
					mm.put("stockholderPid", stockholderPid);
					mm.put("corporationInfoPid", corPid);
					mm.put("businessPrjInfoPid", businessPrjInfoId);
					mm.put("successMsg", "更新股东信息成功");
				}else{
					mm.put("errMsg", "更新股东信息失败");
				}
				return mm;
			}
			// 新增操作
			else{
				/**
				 * 根据选中的股东PID（即企业Pid）、项目公司Pid、项目Pid 查询股东表，校验是否重复
				 * */
				StockholderInfo existStockholderInfo = this.stockholderInfoService.getStockholderByCorpId(businessPrjInfoId,corPid,projectInfoPid);
				if(existStockholderInfo!=null){
					// 查询企业表
					CorporationInfo corporationInfo = this.corporationService.getCorByPrimaryKey(corPid);
					mm.put("errMsg", "当前项目公司对应的股东 "+corporationInfo.getName()+"已存在！");
					return mm;
				}
				
				//插入股东表
				String pid = UuidGenerator.getUuidGenerator();
				stockholderInfo.setPid(pid);
				stockholderInfo.setCorpId(corPid);
				stockholderInfo.setBusinessPrjInfoId(businessPrjInfoId);
				// 股权确认状态;0:未确认；1：确认
				stockholderInfo.setStockRightsStatus("0");
				stockholderInfo.setCreateId(userInfo.getPid());
				stockholderInfo.setCreateDate(new Date());
				int result = 0;
				result = this.stockholderInfoService.insertSelective(stockholderInfo);
				if(result > 0){
					mm.put("stockholderPid", pid);
					mm.put("successMsg", "新增股东成功");
				}else{
					mm.put("errMsg", "新增股东失败");
				}
				return mm;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return mm;
	}
	
	
	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---新增股东公司---上传股东附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.09.29
	 */
	@RequestMapping("/uploadStockholderAccessory")
	public @ResponseBody ModelMap uploadStockholderAccessory(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		try {
			String stockholderPid = request.getParameter("pid");
			UserInfo userInfo = getUserInfo();
			if(StringUtils.isNotBlank(stockholderPid)){
				List<EstateFundFile> files = FileUpload.uploadFile(request, response, EstateFundModelConstants.STOCKHOLDER_ACCESSORY);
				this.stockholderInfoService.batchInsertStockholderAccessory(files,stockholderPid,userInfo.getPid());
				if(files!=null && files.size()>0){
					mm.put("successMsg", "上传成功");
					return mm;
				}
			}else{
				mm.put("errMsg", "请先保存数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return mm;
	}
	
	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---修改股东公司---查询股东附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.09.29
	 */
	@RequestMapping(value="/queryStockholderAccessoryList", produces = "application/json;charset=utf-8")
	public @ResponseBody String queryStockholderAccessoryList(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		Integer records = 0;
		try {
			String stockholderId = request.getParameter("pid");
			if(StringUtils.isNotEmpty(stockholderId)){
				rows = this.stockholderInfoService.getStockholderAccessoryByStockholderId(stockholderId, getOffset(), getPageRows());
				records = this.stockholderInfoService.countStockholderAccessoryByStockholderId(stockholderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return putJsonData(rows, records);
	}
	
	
	/**
	 * 股权确认、股权变更
	 * @author zhangcai
	 * @date 2018.09.25
	 * @param userInfo
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateStockholderInfo")
	public @ResponseBody ModelMap updateStockholderInfo(StockholderInfo stockholderInfo, HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		ModelMap mm = new ModelMap();
		UserInfo userInfo = getUserInfo();
		if(stockholderInfo==null){
			mm.put("errMsg", "股东信息为空");
			return mm;
		}
		/**
		 * 获取 butt_type 判断从哪个按钮进来
		 * butt_type=1：股权确认；butt_type=2：股权变更；
		 * */
		String buttType = request.getParameter("buttType");
		// 得到企业Pid
		String corPid = request.getParameter("corPid");
		// 得到股东Pid
		String stockholderPid = request.getParameter("stockholderPid");
		// 得到企业项目关系表Pid
		String businessPrjInfoId = request.getParameter("businessPrjInfoId");
		
		stockholderInfo.setCorpId(corPid);
		stockholderInfo.setPid(stockholderPid);
		if(StringUtils.isEmpty(corPid)){
			mm.put("errMsg", "请选择股东");
			return mm;
		}
		try {
			if(StringUtils.isNotBlank(buttType)) {
				if("1".equals(buttType)) {
					stockholderInfo.setConfirmId(userInfo.getPid());
					stockholderInfo.setConfirmDate(new Date());
				} else if("2".equals(buttType)) {
					// 插入股权变更历史表
					resultMap = this.stockRightsChangeService.insertStockRightsChange(stockholderInfo,userInfo);
				}
				int result = 0;
				result = this.stockholderInfoService.updateByPrimaryKeySelective(stockholderInfo);
				if(result > 0){
					if("1".equals(buttType)) {
						mm.put("successMsg", "股权确认成功");
					} else if("2".equals(buttType)) {
						if(resultMap != null && resultMap.size() > 0) {
							String code = (String) resultMap.get("code");
							String msg = (String) resultMap.get("msg");
							String stockRightsChangePid = (String) resultMap.get("stockRightsChangePid");
							if(StringUtils.isNotBlank(code) && EstateFundConstants.SUCCESS.equals(code)) {
								mm.put("stockRightsChangePid", stockRightsChangePid);
								mm.put("successMsg", "股权变更成功!");
							} else {
								mm.put("errMsg", "股权变更失败!");
								return mm;
							}
						}
					}
				}else{
					if("1".equals(buttType)) {
						mm.put("errMsg", "股权确认失败");
					} else if("2".equals(buttType)) {
						mm.put("errMsg", "股权变更失败");
					}
				}
			} else {
				mm.put("errMsg", "请点击按钮！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return mm;
	}
	
	
	
	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---股权变更---上传股权变更附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.09.29
	 */
	@RequestMapping("/uploadStockRightsChangeAccessory")
	public @ResponseBody ModelMap uploadStockRightsChangeAccessory(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		try {
			String stockRightsChangePid = request.getParameter("pid");
			UserInfo userInfo = getUserInfo();
			if(StringUtils.isNotBlank(stockRightsChangePid)){
				List<EstateFundFile> files = FileUpload.uploadFile(request, response, EstateFundModelConstants.STOCK_RIGHTS_CHANGE_ACCESSORY);
				this.stockRightsChangeService.batchInsertStockRightsChangeAccessory(files,stockRightsChangePid,userInfo.getPid());
				if(files!=null && files.size()>0){
					mm.put("successMsg", "上传成功");
					return mm;
				}
			}else{
				mm.put("errMsg", "请先保存数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return mm;
	}
	
	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---股权变更---查询股权变更附件
	 * @author zhangcai
	 * @param request
	 * @param response
	 * @return
	 * @date 2018.09.29
	 */
	@RequestMapping(value="/queryStockRightsChangeAccessoryList", produces = "application/json;charset=utf-8")
	public @ResponseBody String queryStockRightsChangeAccessoryList(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		Integer records = 0;
		try {
			String stockRightsChangePid = request.getParameter("pid");
			if(StringUtils.isNotEmpty(stockRightsChangePid)){
				rows = this.stockRightsChangeService.getStockChangeAccessoryByStockRightsChangeId(stockRightsChangePid, getOffset(), getPageRows());
				records = this.stockRightsChangeService.countStockChangeAccessoryByStockRightsChangeId(stockRightsChangePid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return putJsonData(rows, records);
	}
	
	
	
	
	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---股权变更历史记录页面
	 * @author zhangcai
	 * @date 2018.09.28
	 * @return
	 */
	@RequestMapping("/toStockChangeHisPage")
	public String toStockChangeHisPage(String stockholderPid, Model model, HttpServletRequest request, HttpServletResponse response){
		try {
			String stockholderName = request.getParameter("stockholderName");
			String businessPrjPid = request.getParameter("businessPrjPid");
			String operation = request.getParameter("operation");
			
			model.addAttribute("stockholderPid", stockholderPid);
			model.addAttribute("businessPrjInfoId", businessPrjPid);
			model.addAttribute("operation", operation);
			model.addAttribute("stockholderName", URLDecoder.decode(stockholderName,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return "trade/proj_company_manage/stock_change_his";
	}
	
	/**
	 * 新增项目公司---项目公司股东信息---股权变更历史记录页面 index
	 * 查询股权变更历史记录index页列表
	 * @author zhangcai
	 * @date 2018.09.28
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryStockChangeHisList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryStockChangeHisList(String stockholderPid, ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		List<Map<String, Object>> rows = null;
		Integer records = 0;
		try {
			rows = this.stockRightsChangeService.queryStockChangeHisList(stockholderPid, getOffset(), getPageRows());
			records = this.stockRightsChangeService.countStockChangeHisList(stockholderPid);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增、修改股东付款请求
	 * @author zhangcai
	 * @date 2018.09.25
	 * @param userInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addStockholderPaymentRequest")
	public @ResponseBody ModelMap addStockholderPaymentRequest(Payment payment, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		UserInfo userInfo = getUserInfo();
		if(payment==null){
			mm.put("errMsg", "股东付款请求信息为空!");
			return mm;
		}
		// 收款公司Pid（项目公司Pid）
		String businessPrjInfoPid = request.getParameter("businessPrjInfoPid");
		// 出资股东的Pid
		String stockholderPid = request.getParameter("stockholderPid");
		// 出资股东的企业id
		String contributiveId = request.getParameter("contributiveId");
		// 得到付款请求Pid
		String paymentPid = request.getParameter("paymentPid");
		// 得到付款期数
		String payTerm = request.getParameter("payTerm");
		
		if(StringUtils.isEmpty(payment.getPrjId())){
			mm.put("errMsg", "项目名称不能为空!");
			return mm;
		}
		if(StringUtils.isEmpty(payment.getPrjId())){
			mm.put("errMsg", "收款公司不能为空!");
			return mm;
		}
		if(StringUtils.isEmpty(payment.getPrjId())){
			mm.put("errMsg", "出资股东不能为空!");
			return mm;
		}
		try {
			payment.setPid(paymentPid);
			payment.setStockholderId(stockholderPid);
			payment.setPaymentType(EstateFundConstants.PAYMENT_TYPE_STOCKHOLDER);
			payment.setReceiverId(businessPrjInfoPid);
			payment.setContributiveId(contributiveId);
			//修改操作
			if(StringUtils.isNotBlank(paymentPid)){
				payment.setInvestorOp(userInfo.getPid());
				payment.setInvestorOpDate(new Date());
				payment.setUpdateId(userInfo.getPid());
				payment.setUpdateDate(new Date());
				int result = this.investerManageService.updatePaymentBySelective(payment);
				if(result>0){
					mm.put("successMsg", "更新股东付款请求信息成功");
				}else{
					mm.put("errMsg", "更新股东付款请求信息失败");
				}
				return mm;
			}
			// 新增操作
			else{
				// 同一个项目公司下的同一个股东  不能有相同的付款期数(即每期只能付款一次) 
				
				//插入股东表
				String pid = UuidGenerator.getUuidGenerator();
				payment.setPid(pid);
				payment.setFinanceConfirmStatus(EstateFundConstants.FINANCE_NOT_CONFIRM);
				payment.setInvestorOp(userInfo.getPid());
				payment.setInvestorOpDate(new Date());
				payment.setCreateId(userInfo.getPid());
				payment.setCreateDate(new Date());
				int result = 0;
				result = this.investerManageService.insertPaymentBySelective(payment);
				if(result > 0){
					mm.put("stockPaymentPid", pid);
					mm.put("successMsg", "新增股东付款请求信息成功");
				}else{
					mm.put("errMsg", "新增股东付款请求信息失败");
				}
				return mm;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return mm;
	}
	
	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---查看股东付款信息页面
	 * @author zhangcai
	 * @date 2018.09.28
	 * @return
	 */
	@RequestMapping("/toStockPaymentInfoPage")
	public String toStockPaymentInfoPage(String corp_id, Model model, HttpServletRequest request, HttpServletResponse response){
		Map<String,String> condtions = new HashMap<String,String>();
		try {
			String stockholderName = request.getParameter("stockholderName");
			String viewStockPayment = request.getParameter("viewStockPayment");
			// 查看股东付款信息页面   返回 按钮会用到此参数
			String businessPrjInfoId = request.getParameter("businessPrjPid");
			String operation = request.getParameter("operation");
			// 股东对应的企业id
			model.addAttribute("corp_id", corp_id);
			model.addAttribute("viewStockPayment", viewStockPayment);
			model.addAttribute("stockholderName", URLDecoder.decode(stockholderName,"UTF-8"));
			model.addAttribute("businessPrjInfoId", businessPrjInfoId);
			model.addAttribute("operation", operation);
			
//			condtions.put("stockholderPid", stockholderPid);
//			condtions.put("prjId", prjId);
			condtions.put("businessPrjInfoId", businessPrjInfoId);
			
			Map<String,Object> amountMap = this.investerManageService.statisticsAmountByCondition(condtions,EstateFundConstants.PAYMENT_TYPE_STOCKHOLDER);
			model.addAttribute("amount", amountMap);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return "trade/proj_company_manage/view_stock_payment_request";
	}
	
	/**
	 * 新增项目公司---项目公司股东信息---查看股东付款信息列表
	 * 根据股东对应的企业id   corp_id查询该股东下的股东付款信息列表
	 * @author zhangcai
	 * @date 2018.10.16
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryStockPaymentInfoList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryStockPaymentInfoList(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		List<Map<String, Object>> rows = null;
		Integer records = 0;
		Map<String,String> condtions = new HashMap<String,String>();
		try {
			// 项目名称 检索框
			condtions.put("prjId", request.getParameter("prjId"));
			// 出资主(当前股东对应的企业id)体 检索框
			condtions.put("corp_id", request.getParameter("corp_id"));
			// 财务确认状态  检索框
			condtions.put("financeConfirmStatus", request.getParameter("financeConfirmStatus"));
			
			rows = this.investerManageService.queryStockPaymentInfoList(condtions, getOffset(), getPageRows());
			records = this.investerManageService.countStockPaymentInfoList(condtions);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		return putJsonData(rows, records);
	}
	
	
	/**
	 * 新增项目公司---项目公司股东信息---删除
	 * 删除股东信息（可批量删除）
	 * @param stockholderIds
	 * @return
	 */
	@RequestMapping(value="/deleteStockholders",method=RequestMethod.POST)
	private @ResponseBody ModelMap deleteStockholders(HttpServletRequest request, HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String stockholderIds = request.getParameter("stockholderPids");
		UserInfo userInfo = getUserInfo();
		String curPid = userInfo.getPid();
		try {
			if(StringUtils.isBlank(stockholderIds)){
				mm.put("errMsg", "删除失败,请选择股东!");
			}else{
				List<String> pids = SplitCommaUtils.splitCorPids(stockholderIds);
				int result = this.stockholderInfoService.delStockholderInfoByPids(pids,curPid);
				
				if(result <= 0){
					mm.put("errMsg", "删除失败!");
				}else{
					mm.put("successMsg", "删除成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除企业信息失败!");
			mm.put("errMsg", "系统出错!");
		}
		
		return mm;
	}
	
	
	
	

}
