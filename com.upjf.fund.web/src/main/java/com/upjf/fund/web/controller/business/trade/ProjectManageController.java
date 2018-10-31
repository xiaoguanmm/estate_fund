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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upjf.fund.base.Page;
import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.constants.TagConstants;
import com.upjf.fund.dto.BusinessComposition;
import com.upjf.fund.dto.BusinessPrjInfo;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.ProjectBudget;
import com.upjf.fund.dto.ProjectBudgetAccessory;
import com.upjf.fund.dto.ProjectContract;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.dto.ProjectProgress;
import com.upjf.fund.dto.ProjectProgressAccessory;
import com.upjf.fund.dto.ProjectVo;
import com.upjf.fund.dto.SysAreaInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.business.BusinessCompositionService;
import com.upjf.fund.service.business.BusinessPrjInfoService;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.ProjectBudgetAccessoryService;
import com.upjf.fund.service.business.ProjectBudgetService;
import com.upjf.fund.service.business.ProjectContractService;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.business.ProjectProgressAccessoryService;
import com.upjf.fund.service.business.ProjectProgressService;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.service.system.SysAreaInfoService;
import com.upjf.fund.utils.SplitCommaUtils;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.file.upload.FileUpload;

/**
 * 该控制层用于项目管理信息的列表查看与相关维护功能
 * @author lixq
 * @date 2018年9月26日
 */
@Controller
@RequestMapping("/projectManageController")
public class ProjectManageController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(ProjectManageController.class);
	
	
	@Autowired
	private CorporationService corporationService;												//企业信息服务
	
	@Autowired
	private DictInfoService dictInfoService;													//系统字典服务
	
	@Autowired
	private SysAreaInfoService sysAreaInfoService;												//系统地区服务
	
	@Autowired
	private BusinessPrjInfoService businessPrjInfoService;										//项目公司服务
	
	@Autowired
	private ProjectInfoService projectInfoService;												//项目基本信息服务
	
	@Autowired
	private BusinessCompositionService businessCompositionService;								//项目业态组成服务
	
	@Autowired
	private ProjectProgressService projectProgressService;										//项目进度信息服务
	
	@Autowired
	private ProjectProgressAccessoryService projectProgressAccessoryService;					//项目进度与文件关系信息服务
	
	@Autowired
	private ProjectContractService projectContractService;										//项目合同附件信息服务
	
	@Autowired
	private ProjectBudgetService projectBudgetService;											//项目预算信息服务
	
	@Autowired
	private ProjectBudgetAccessoryService projectBudgetAccessoryService;						//项目预算附件信息服务
	
	
	
	
	/**
	 * 项目管理信息列表查看及查询检索
	 * @author  lixq 
	 * @param   corInfo
	 * @param   page
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年9月27日
	 */
	@RequestMapping(value="/proList")
	private String toList(ProjectVo projectVo,Page page,Model model,HttpServletRequest req){
		List<SysAreaInfo> provinceList = null;												//省级信息
		List<DictDetail> proCateGoryList = null;											//项目种类
		List<DictDetail> landGetWayList = null;												//土地获取方式
		List<DictDetail> progressList = null;												//项目进度状态
		String entranceType = req.getParameter("entranceType");								//从项目公司管理-关联项目入口进来
		String businessPrjPid = req.getParameter("businessPrjPid");							//项目公司管理列表选中数据的   项目公司PId
		String linkedPrjId = req.getParameter("linkedPrjId");							    //选中数据关联的项目Id
		try {
			provinceList = sysAreaInfoService.getSysAreaByLevel(TagConstants.PROVINCE_CODE);
			proCateGoryList = dictInfoService.getDictDetailsByCode(TagConstants.PROJECT_CATEGORY);
			landGetWayList = dictInfoService.getDictDetailsByCode(TagConstants.LAND_GET_WAY);
			progressList = dictInfoService.getDictDetailsByCode(TagConstants.PROJECT_PROGRESS);
			
			Map<String, Object> resultMap = projectInfoService.getProjectVoListByPage(projectVo,page);
			if(resultMap != null){
				model.addAttribute("projectVoList", resultMap.get("projectVoList"));
				model.addAttribute("page", resultMap.get("page"));
			}
			
			model.addAttribute("provinceList", provinceList);
			model.addAttribute("landGetWayList", landGetWayList);
			model.addAttribute("proCateGoryList", proCateGoryList);
			model.addAttribute("progressList", progressList);
			model.addAttribute("linkedPrjId", linkedPrjId);
			model.addAttribute("businessPrjPid", businessPrjPid);
			model.addAttribute("entranceType", entranceType);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		//响应界面
		return "trade/project_manage/project_manage";
	}
	
	
	
	/**
	 * 进入新增,修改,查看项目界面
	 * @author  lixq 
	 * @param   type: 1为新增,2为修改,3为查看
	 * @param   projectPid:  项目基本信息主键
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月12日
	 */
	@RequestMapping(value="/toProject")
	private String toAdd(String type,String projectPid,Model model,HttpServletRequest req){
		List<BusinessPrjInfo> busProInfoList = null;										//获取项目公司信息
		List<CorporationInfo> corInfoList = null;											//开发商信息
		List<SysAreaInfo> provinceList = null;												//省级信息
		List<DictDetail> proCateGoryList = null;											//项目种类
		List<DictDetail> landGetWayList = null;												//土地获取方式
		List<DictDetail> busCompoList = null;												//业态组成下拉框
		List<DictDetail> progressList = null;												//项目进度状态
		List<DictDetail> certBuildAreaList = null;											//证载建面
		ProjectInfo projectInfo = null;
		ProjectProgress projectProgress = null;
		ProjectBudget projectBudget = null;
		
		String accessType = req.getParameter("accessType");									// accessType=1表示从新增股东入口过来
		try {
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
				
				//加载最新项目进度信息
				projectProgress = projectProgressService.getLatestProgressByProjectPid(projectPid);
				
				//加载最新项目预算信息
				projectBudget = projectBudgetService.getLatestBudgetByProjectPid(projectPid);
			}
			
			
			model.addAttribute("type", type);
			model.addAttribute("projectInfo", projectInfo);
			model.addAttribute("projectProgress", projectProgress);
			model.addAttribute("projectBudget", projectBudget);
			model.addAttribute("corInfoList", corInfoList);
			model.addAttribute("busCompoList", busCompoList);
			model.addAttribute("provinceList", provinceList);
			model.addAttribute("busProInfoList", busProInfoList);
			model.addAttribute("landGetWayList", landGetWayList);
			model.addAttribute("proCateGoryList", proCateGoryList);
			model.addAttribute("progressList", progressList);
			model.addAttribute("certBuildAreaList", certBuildAreaList);
			if("1".equals(accessType)) {
				model.addAttribute("accessType", accessType);
				model.addAttribute("businessPrjIdFromAddStock", req.getParameter("businessPrjInfoId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "trade/project_manage/project_manage_add";
	}
	
	
	
	/**
	 * 保存项目基本信息(新增,修改)
	 * @author  lixq 
	 * @param   proInfo
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年9月28日
	 */
	@RequestMapping(value="/saveProInfo",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> saveProInfo(ProjectInfo proInfo,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo currUser = super.getCurrUser(req);
		try {
			if(proInfo != null){
				proInfo.setCreateId(currUser.getPid());
				proInfo.setCreateDate(new Date());
				proInfo.setUpdateDate(new Date());
				proInfo.setUpdateId(currUser.getPid());
				
				Map<String, Object> saveMap = projectInfoService.saveProjectInfo(proInfo);
				if(saveMap != null){
					resultMap.put("success", "s");
					resultMap.put("msg", "保存项目基本信息成功!");
					resultMap.put("pid", saveMap.get("pid"));
					resultMap.put("projectCode", saveMap.get("projectCode"));
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "系统出错,保存项目基本信息失败!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,保存项目基本信息失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,保存项目基本信息失败!");
		}
		
		return resultMap;
	}
	
	
	
	
	
	/**
	 * 保存项目业态组成信息(新增/修改)
	 * @author  lixq 
	 * @param   busCompo
	 * @param   req
	 * @return  String  
	 * @date 2018年9月28日
	 */
	@RequestMapping(value="/saveBusCompo")
	@ResponseBody
	private Map<String, Object> saveBusCompo(BusinessComposition busCompo,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserInfo currUser = super.getCurrUser(req);
			busCompo.setCreateId(currUser.getPid());
			busCompo.setCreateDate(new Date());
			busCompo.setUpdateId(currUser.getPid());
			busCompo.setUpdateDate(new Date());
			
			String pid = businessCompositionService.saveBusCompoInfo(busCompo);
			if(StringUtils.isNotBlank(pid)){
				resultMap.put("success", "s");
				resultMap.put("msg", "保存成功!");
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "系统出错,保存业态组成信息失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,保存项目业态组成信息失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,保存业态组成信息失败!");
		}
		
		return resultMap;
	}
	
	
	
	
	/**
	 * 分页获取项目业态组成信息
	 * @author  lixq 
	 * @param   projectId
	 * @param   page
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年9月28日
	 */
	@RequestMapping(value="/getBusComPoList")
	@ResponseBody
	private Map<String, Object> getBusComPoList(BusinessComposition busCompo,Page page,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> businessMap = businessCompositionService.getBusCompoListByPage(busCompo, page);
			if(businessMap != null){
				resultMap.put("success", "s");
				resultMap.put("msg", "加载成功!");
				resultMap.put("busCompoList", businessMap.get("busCompoList"));
				resultMap.put("page", businessMap.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "加载数据列表失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,分页获取项目业态组成信息!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,分页获取项目业态组成信息失败!");
		}
		
		return resultMap;
	}
	
	
	
	
	/**
	 * 删除项目业态组成信息
	 * @author  lixq 
	 * @param   busCompo
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年9月28日
	 */
	@RequestMapping(value="/delBusComPo")
	@ResponseBody
	private Map<String, Object> delBusComPo(BusinessComposition busCompo,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(busCompo != null){
				busCompo.setStatus(0);
				int result = businessCompositionService.updateBusCompoByKey(busCompo);
				if(result > 0){
					resultMap.put("success", "s");
					resultMap.put("msg", "删除成功!");
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "删除失败!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "请选择要删除的数据!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,删除项目业态组成信息失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,删除项目业态组成信息失败!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 进入项目业态组成修改界面
	 * @author  lixq 
	 * @param pid
	 * @param req
	 * @return  Map<String,Object>  
	 * @date 2018年9月29日
	 */
	@RequestMapping(value="/toUpdateBusCompo")
	@ResponseBody
	private Map<String, Object> toUpdateBusCompo(String pid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(pid)){
				BusinessComposition busCompoInfo = businessCompositionService.getBusCompoByKey(pid);
				
				if(busCompoInfo != null){
					resultMap.put("success", "s");
					resultMap.put("msg", "获取数据成功!");
					resultMap.put("busCompoInfo", busCompoInfo);
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "获取数据失败!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,删除项目业态组成信息失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,删除项目业态组成信息失败!");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 保存项目进度信息(新增,修改)
	 * @author  lixq 
	 * @param   ProProgress
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年9月29日
	 */
	@RequestMapping(value="/saveProProgress")
	@ResponseBody
	private Map<String, Object> saveProProgress(ProjectProgress proProgress,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(proProgress != null){
				UserInfo currUser = super.getCurrUser(req);
				proProgress.setCreateId(currUser.getPid());
				proProgress.setCreateDate(new Date());
				
				String newPid = projectProgressService.saveProProgress(proProgress);
				if(StringUtils.isNotBlank(newPid)){
					resultMap.put("success", "s");
					resultMap.put("msg", "保存成功!");
					resultMap.put("progressId", newPid);
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "系统出错,保存项目进度信息失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,删除项目业态组成信息失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,保存项目进度信息失败!");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 上传项目进度信息文件
	 * @author  lixq 
	 * @param   proProgress
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年9月30日
	 */
	@RequestMapping(value="/uploadProgressFile")
	@ResponseBody
	private Map<String, Object> uploadProgressFile(String progressId,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EstateFundFile> uploadFile = null;
		ProjectProgressAccessory ppa = new ProjectProgressAccessory();
		UserInfo currUser = super.getCurrUser(request);
		
		try {
			String modulePath = EstateFundModelConstants.TRADE;
			uploadFile = FileUpload.uploadFile(request, response, modulePath);
			
			if(uploadFile != null && uploadFile.size() > 0){
				EstateFundFile estateFundFile = uploadFile.get(0);
				String file_id = estateFundFile.getPid();
				
				ppa.setCreateId(currUser.getPid());
				ppa.setCreateDate(new Date());
				ppa.setPrjProgressId(progressId);
				ppa.setFileId(file_id);
				String resultId = projectProgressAccessoryService.saveProgressAccessory(ppa);
				if(StringUtils.isNotBlank(resultId)){
					resultMap.put("success", "s");
					resultMap.put("msg", "上传成功!");
					resultMap.put("refreshType", "progress");
				}else{
					FileUpload.deleteFile(uploadFile.get(0).getPid(), uploadFile.get(0).getFileUrl());
					resultMap.put("success", "e");
					resultMap.put("msg", "上传失败!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "上传失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,上传项目进度附件失败!");
			if(uploadFile != null && uploadFile.size() > 0){
				FileUpload.deleteFile(uploadFile.get(0).getPid(), uploadFile.get(0).getFileUrl());
			}
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 校验变动的项目进度信息是否与最新一条记录一致
	 * @author  lixq 
	 * @param   proProgress
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月9日
	 */
	@RequestMapping(value="/cheackProProgress")
	@ResponseBody
	private Map<String, Object> cheackProProgress(ProjectProgress proProgress,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(proProgress != null){
				boolean result = projectProgressService.cheackProProgress(proProgress);
				resultMap.put("success", "s");
				resultMap.put("msg", "操作成功!");
				resultMap.put("isTheSame", result);
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,校验变动的项目进度信息是否与最新一条记录一致!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 加载项目进度附件信息列表
	 * @author  lixq 
	 * @param   proProgress
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月9日
	 */
	@RequestMapping(value="/getProProgressList")
	@ResponseBody
	private Map<String, Object> getProProgressList(String projectId,Page page,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> proProgressMap = projectProgressService.getProgressFileByPage(projectId, page);
			if(proProgressMap != null){
				resultMap.put("success", "s");
				resultMap.put("msg", "加载成功!");
				resultMap.put("progressFileList", proProgressMap.get("progressFileList"));
				resultMap.put("page", proProgressMap.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "加载数据列表失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 删除项目进度状态记录
	 * @author  lixq 
	 * @param   pid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月9日
	 */
	@RequestMapping(value="/delProgress")
	@ResponseBody
	private Map<String, Object> delProgress(String pid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(pid)){
				int delProgress = projectProgressService.delProgress(pid);
				if(delProgress > 0){
					resultMap.put("success", "s");
					resultMap.put("msg", "删除成功!");
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "系统出错!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 上传项目合同附件
	 * @author  lixq 
	 * @param   pid
	 * @param   contractName
	 * @param   request
	 * @param   response
	 * @return  Map<String,Object>  
	 * @date 2018年10月10日
	 */
	@RequestMapping(value="/uploadContractFile")
	@ResponseBody
	private Map<String, Object> uploadContractFile(String projectPid,String contractName,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EstateFundFile> uploadFile = null;
		ProjectContract pc = new ProjectContract();
		UserInfo currUser = super.getCurrUser(request);
		
		try {
			String modulePath = EstateFundModelConstants.TRADE;
			uploadFile = FileUpload.uploadFile(request, response, modulePath);
			
			if(uploadFile != null && uploadFile.size() > 0){
				EstateFundFile estateFundFile = uploadFile.get(0);
				String file_id = estateFundFile.getPid();
				
				pc.setCreateId(currUser.getPid());
				pc.setCreateDate(new Date());
				pc.setPrjId(projectPid); 
				pc.setFileId(file_id);
				
				if(StringUtils.isNotBlank(contractName)){
					pc.setContractName(contractName);
				}else{
					String realName = estateFundFile.getRealName();
					pc.setContractName(realName);
				}
				
				String resultId = projectContractService.saveProjectContract(pc);
				if(StringUtils.isNotBlank(resultId)){
					resultMap.put("success", "s");
					resultMap.put("msg", "上传成功!");
					resultMap.put("refreshType", "contract");
				}else{
					FileUpload.deleteFile(uploadFile.get(0).getPid(), uploadFile.get(0).getFileUrl());
					resultMap.put("success", "e");
					resultMap.put("msg", "上传失败!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "上传失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
			if(uploadFile != null && uploadFile.size() > 0){
				FileUpload.deleteFile(uploadFile.get(0).getPid(), uploadFile.get(0).getFileUrl());
			}
		}
		return resultMap;
	}
	
	
	/**
	 * 分页加载项目合同附件列表
	 * @author  lixq 
	 * @param   projectId
	 * @param   page
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月10日
	 */
	@RequestMapping(value="/getContractList")
	@ResponseBody
	private Map<String, Object> getContractList(String projectId,Page page,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> contractMap = projectContractService.getContractListByPage(projectId, page);
			if(contractMap != null){
				resultMap.put("success", "s");
				resultMap.put("msg", "加载成功!");
				resultMap.put("contractList", contractMap.get("contractList"));
				resultMap.put("page", contractMap.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "加载数据列表失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 删除项目合同附件
	 * @author  lixq 
	 * @param   pid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月10日
	 */
	@RequestMapping(value="/delContract")
	@ResponseBody
	private Map<String, Object> delContract(String pid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(pid)){
				int delContract = projectContractService.delContract(pid);
				if(delContract > 0){
					resultMap.put("success", "s");
					resultMap.put("msg", "删除成功!");
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "系统出错!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 保存项目预算数据
	 * @author  lixq 
	 * @param   projectBudget
	 * @param   request
	 * @param   response
	 * @return  Map<String,Object>  
	 * @date 2018年10月11日
	 */
	@RequestMapping(value="/saveBudgetData")
	@ResponseBody
	private Map<String, Object> uploadBudgetFile(ProjectBudget pb,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo currUser = super.getCurrUser(request);
		try {
			if(pb != null){
				pb.setCreateDate(new Date());
				pb.setCreateId(currUser.getPid());
				pb.setUpdateDate(new Date());
				pb.setUpdateId(currUser.getPid());
				
				String reslut = projectBudgetService.saveProjectBudget(pb);
				if(StringUtils.isNotBlank(reslut)){
					resultMap.put("success", "s");
					resultMap.put("msg", "保存成功!");
					resultMap.put("projectBudgetId", reslut);
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "保存信息失败!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		return resultMap;
	}
	
	
	/**
	 * 校验预算名称是否改变:true表示预算名称已改变,false表示项目预算名称尚未改变
	 * @author  lixq 
	 * @param   pb
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月11日
	 */
	@RequestMapping(value="/cheackBudget")
	@ResponseBody
	private Map<String, Object> cheackBudget(ProjectBudget pb,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(pb != null){
				boolean result = projectBudgetService.cheackBudget(pb);
				resultMap.put("success", "s");
				resultMap.put("msg", "操作成功!");
				resultMap.put("isTheSame", result);
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 上传项目预算附件
	 * @author  lixq
	 * @param   projectPid
	 * @param   request
	 * @param   response
	 * @return  Map<String,Object>  
	 * @date 2018年10月11日
	 */
	@RequestMapping(value="/uploadBudgetFile")
	@ResponseBody
	private Map<String, Object> uploadBudgetFile(String projectBudgetPid,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EstateFundFile> uploadFile = null;
		ProjectBudgetAccessory pba = new ProjectBudgetAccessory();
		UserInfo currUser = super.getCurrUser(request);
		
		try {
			String modulePath = EstateFundModelConstants.TRADE;
			uploadFile = FileUpload.uploadFile(request, response, modulePath);
			
			if(uploadFile != null && uploadFile.size() > 0){
				EstateFundFile estateFundFile = uploadFile.get(0);
				String file_id = estateFundFile.getPid();
				
				pba.setCreateId(currUser.getPid());
				pba.setCreateDate(new Date());
				pba.setFileId(file_id);
				pba.setPrjBudgetId(projectBudgetPid);
				
				String resultId = projectBudgetAccessoryService.saveProjectBudgetAccessory(pba);
				if(StringUtils.isNotBlank(resultId)){
					resultMap.put("success", "s");
					resultMap.put("msg", "上传成功!");
					resultMap.put("refreshType", "budget");
				}else{
					FileUpload.deleteFile(uploadFile.get(0).getPid(), uploadFile.get(0).getFileUrl());
					resultMap.put("success", "e");
					resultMap.put("msg", "上传失败!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "上传失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
			if(uploadFile != null && uploadFile.size() > 0){
				FileUpload.deleteFile(uploadFile.get(0).getPid(), uploadFile.get(0).getFileUrl());
			}
		}
		return resultMap;
	}
	
	
	
	
	/**
	 * 分页加载项目预算记录
	 * @author  lixq 
	 * @param   projectId
	 * @param   page
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月11日
	 */
	@RequestMapping(value="/getBudgetList")
	@ResponseBody
	private Map<String, Object> getBudgetList(String projectId,Page page,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> budgetMap = projectBudgetService.getBudgetDataByPage(projectId, page);
			if(budgetMap != null){
				resultMap.put("success", "s");
				resultMap.put("msg", "加载成功!");
				resultMap.put("budgetList", budgetMap.get("budgetList"));
				resultMap.put("page", budgetMap.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "加载数据列表失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 删除项目预算记录
	 * @author  lixq 
	 * @param   pid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月11日
	 */
	@RequestMapping(value="/delBudget")
	@ResponseBody
	private Map<String, Object> delBudget(String pid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(pid)){
				int delContract = projectBudgetService.delbudget(pid);
				if(delContract > 0){
					resultMap.put("success", "s");
					resultMap.put("msg", "删除成功!");
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "系统出错!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 进入变更项目进度状态界面
	 * @author  lixq 
	 * @param   projectPid
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月13日
	 */
	@RequestMapping(value="/toChangeProgress")
	private String toChangeProgress(String projectPid,Model model,HttpServletRequest req){
		List<DictDetail> progressList = null;												//项目进度状态
		List<DictDetail> certBuildAreaList = null;											//证载建面
		ProjectInfo projectInfo = null;
		ProjectProgress projectProgress = null;
		ProjectBudget projectBudget = null;
		try {
			
			progressList = dictInfoService.getDictDetailsByCode(TagConstants.PROJECT_PROGRESS);
			certBuildAreaList = dictInfoService.getDictDetailsByCode(TagConstants.CERT_BUILD_AREA);
			
			if(StringUtils.isNotBlank(projectPid)){
				//加载最新项目进度信息
				projectProgress = projectProgressService.getLatestProgressByProjectPid(projectPid);
			}
			
			
			model.addAttribute("projectInfo", projectInfo);
			model.addAttribute("projectProgress", projectProgress);
			model.addAttribute("projectBudget", projectBudget);
			model.addAttribute("progressList", progressList);
			model.addAttribute("certBuildAreaList", certBuildAreaList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "trade/project_manage/project_manage_change";
	}
	
	
	/**
	 * 删除批量删除项目信息(伪删除,单纯只变更状态值)
	 * @author  lixq 
	 * @param   projectPids :逗号拼接的项目主键字符串
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月13日
	 */
	@RequestMapping(value="/delProject")
	@ResponseBody
	private Map<String, Object> delProject(String projectPids,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo currUser = super.getCurrUser(request);
		String updatePid = currUser.getPid();
		try {
			if(StringUtils.isNotBlank(projectPids)){
				List<String> projectPidList = SplitCommaUtils.splitCorPids(projectPids);
				int delContract = projectInfoService.delProjectByPids(projectPidList,updatePid);
				if(delContract > 0){
					resultMap.put("success", "s");
					resultMap.put("msg", "删除成功!");
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "系统出错!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		
		return resultMap;
	}
	
	
	
}
