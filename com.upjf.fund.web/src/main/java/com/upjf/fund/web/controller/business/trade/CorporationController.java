package com.upjf.fund.web.controller.business.trade;

import java.math.BigDecimal;
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
import com.upjf.fund.dto.CorporationBankInfo;
import com.upjf.fund.dto.CorporationDataInfo;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.business.CorporationBankService;
import com.upjf.fund.service.business.CorporationDataInfoService;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.utils.SplitCommaUtils;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.file.upload.FileUpload;

/**
 * 该控制层用于企业管理信息的列表查看与相关维护功能
 * @author lixq
 * @date 2018年9月19日
 */
@Controller
@RequestMapping("/corporationController")
public class CorporationController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(CorporationController.class);
	
	@Autowired
	private CorporationService corporationService;							//企业信息服务
	
	
	@Autowired
	private DictInfoService dictInfoService;								//系统字典服务
	
	@Autowired
	private CorporationBankService corporationBankService;					//企业银行信息服务
	
	@Autowired
	private CorporationDataInfoService corporationDataInfoService;			//企业资料信息服务
	
	
	
	/**
	 * 企业管理信息列表查看及查询检索
	 */
	@RequestMapping(value="/corInfoList")
	private String toList(CorporationInfo corInfo,Page page,Model model,HttpServletRequest req){
		try {
			//调取服务查询 结果
			Map<String, Object> resualtMap = corporationService.getCorporationListByPage(corInfo,page);
			
			//存入域中
			model.addAttribute("corList", resualtMap.get("corList"));
			model.addAttribute("page", resualtMap.get("page"));
			model.addAttribute("review", corInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取企业信息列表失败,系统出错!");
		}
		
		//响应界面
		return "trade/firm_manage/firm_manage";
	}
	
	
	
	
	/**
	 * 进入新增企业
	 */
	@RequestMapping(value="/toAddCorInfo",method=RequestMethod.GET)
	private String toAddCorporation(Model model,HttpServletRequest req){
		try {
			//调取服务
			List<DictDetail> list = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_PROPERTY);
			List<DictDetail> bankList = dictInfoService.getDictDetailsByCode(TagConstants.BANK_NAME);
			List<DictDetail> corDataInfoList = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_DATA_TYPE);
			
			//存入域中
			model.addAttribute("corList", list);
			model.addAttribute("bankList", bankList);
			model.addAttribute("corDataInfoList", corDataInfoList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("进入新增企业获取企业所有制性质及开户行数据失败,系统出错!");
		}
		
		//响应界面
		return "trade/firm_manage/firm_manage_add";
	}
	
	
	
	/**
	 * 进入修改企业基本信息页面
	 * @param corporationId
	 */
	@RequestMapping(value="/toUpdateCorInfo",method=RequestMethod.GET)
	private String toUpdateCorporation(String pid,Model model,HttpServletRequest req){
		try {
			//调取服务
			CorporationInfo baseCorInfo = null;
			Map<String, Object> bankInfoList = null;
			List<DictDetail> bankList = dictInfoService.getDictDetailsByCode(TagConstants.BANK_NAME);
			List<DictDetail> list = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_PROPERTY);
			List<DictDetail> corDataInfoList = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_DATA_TYPE);
			
			if(StringUtils.isNotBlank(pid)){
				baseCorInfo = corporationService.getCorByPrimaryKey(pid);
				
				CorporationBankInfo corBankInfo = new CorporationBankInfo();
				corBankInfo.setCorpId(pid);
				bankInfoList = corporationBankService.getCorporationBankListByPage(corBankInfo, new Page());
				
			}else{
				baseCorInfo = new CorporationInfo();
				bankInfoList = new HashMap<String, Object>();
			}
			
			
			//存入域中
			model.addAttribute("corList", list);
			model.addAttribute("bankList", bankList);
			model.addAttribute("baseCorInfo",baseCorInfo);
			model.addAttribute("page", bankInfoList.get("page"));
			model.addAttribute("corBankList",bankInfoList.get("corBankList"));
			model.addAttribute("corDataInfoList", corDataInfoList);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("进入企业信息修改页面获取企业相关信息失败,系统出错!");
		}
		return "trade/firm_manage/firm_manage_modify";
	}
	
	
	
	
	
	/**
	 * 保存企业基本信息
	 * @param info
	 * @param modelMap
	 * @param req
	 */
	@ResponseBody
	@RequestMapping(value="/saveBase",method=RequestMethod.POST)
	private Map<String,Object> saveBaseCorInfo(CorporationInfo corInfo,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		try {
			if(corInfo != null){
				UserInfo currUser = super.getCurrUser(req);
				corInfo.setCreateId(currUser.getPid());
				corInfo.setCreateDate(new Date());
				corInfo.setUpdateId(currUser.getPid());
				corInfo.setUpdateDate(new Date());
				
				resualtMap = corporationService.insertCorporation(corInfo);
			}else{
				resualtMap.put("success", "e");
				resualtMap.put("msg", "保存失败,不能提交空信息!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	
	/**
	 * 保存修改后的企业基本信息
	 * @param info
	 * @param modelMap
	 * @param req
	 */
	@ResponseBody
	@RequestMapping(value="/updateBase",method=RequestMethod.POST)
	private Map<String,Object> saveUpdateBaseCorInfo(CorporationInfo corInfo,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		try {
			if(corInfo != null){
				UserInfo currUser = super.getCurrUser(req);
				corInfo.setUpdateId(currUser.getPid());
				corInfo.setUpdateDate(new Date());
				
				BigDecimal registerCapital = corInfo.getRegisterCapital();
				registerCapital = registerCapital.multiply(new BigDecimal("10000"));
				corInfo.setRegisterCapital(registerCapital);
				
				int result = corporationService.updateCorInfoByKey(corInfo);
				if(result == TagConstants.EXIST_CODE){
					resualtMap.put(TagConstants.SUCCESS, TagConstants.SUCCESS_CODE_E);
					resualtMap.put(TagConstants.MSG, "存在相同组织机构代码企业!");
				}else if(result == 0){
					resualtMap.put(TagConstants.SUCCESS, TagConstants.SUCCESS_CODE_E);
					resualtMap.put(TagConstants.MSG, "保存失败!");
				}else{
					resualtMap.put(TagConstants.SUCCESS, TagConstants.SUCCESS_CODE_S);
					resualtMap.put(TagConstants.MSG, "保存成功!");
				}
			}else{
				resualtMap.put(TagConstants.SUCCESS, TagConstants.SUCCESS_CODE_E);
				resualtMap.put(TagConstants.MSG, "保存失败,不能提交空信息!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	
	
	/**
	 * 删除企业信息(可批量删除,非物理删除)
	 * @param corporationId
	 * @return
	 */
	@RequestMapping(value="/delCorInfo",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> delCorInfo(String corporationIds,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isBlank(corporationIds)){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "删除失败,请选择企业!");
			}else{
				UserInfo currUser = super.getCurrUser(req);
				String curPid = currUser.getPid();
				
				List<String> pids = SplitCommaUtils.splitCorPids(corporationIds);
				int resualt = corporationService.delCorporationByPids(pids,curPid);
				
				 if(resualt <= 0){
					 resualtMap.put("success", "e");
					 resualtMap.put("msg", "删除失败,系统出错或企业主键集合为空!");
				 }else{
					 resualtMap.put("success", "s");
					 resualtMap.put("msg", "删除成功");
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除企业信息失败!");
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	/**
	 * 保存企业银行信息
	 * @param corBank
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/saveBank",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> saveCorBankInfo(CorporationBankInfo bankInfo,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		try {
			if(bankInfo != null){
				UserInfo currUser = super.getCurrUser(req);
				bankInfo.setCreateId(currUser.getPid());
				bankInfo.setCreateDate(new Date());
				
				resualtMap = corporationBankService.insertCorporationBank(bankInfo);	
			}else{
				resualtMap.put("success", "e");
				resualtMap.put("msg", "保存失败,请先保存企业基础信息!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存企业银行信息失败,系统出错!");
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	
	
	/**
	 * 根据主键获取企业银行信息
	 * @param pid
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getBank",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> getBankInfoByKey(String pid,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isBlank(pid)){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "获取企业银行信息失败!");
			}else{
				CorporationBankInfo corBankInfo = corporationBankService.getCorporationBankByKey(pid);
				if(corBankInfo != null){
					resualtMap.put("success", "s");
					resualtMap.put("msg", "获取企业银行信息成功!");
					resualtMap.put("bankInfo", corBankInfo);
				}else{
					resualtMap.put("success", "e");
					resualtMap.put("msg", "获取企业银行信息失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		return resualtMap;
	}
	
	
	/**
	 * 分页获取企业银行信息
	 * @param corPid
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getBankList",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> getBankInfoByPage(String corPid,Page page,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isBlank(corPid)){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "获取企业银行信息失败!");
			}else{
				CorporationBankInfo newBankInfo = new CorporationBankInfo();
				newBankInfo.setCorpId(corPid);
				Map<String, Object> pageListMap = corporationBankService.getCorporationBankListByPage(newBankInfo, page);
				resualtMap.put("success", "s");
				resualtMap.put("msg", "获取企业银行信息成功!");
				resualtMap.put("corBankList", pageListMap.get("corBankList"));
				resualtMap.put("page", pageListMap.get("page"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	/**
	 * 保存修改企业银行信息
	 * @param bankInfo
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/updateBank",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> updateBank(CorporationBankInfo bankInfo,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		try {
			if(bankInfo ==  null){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "修改银行信息失败!");
			}else{
				UserInfo currUser = super.getCurrUser(req);
				bankInfo.setUpdateId(currUser.getPid());
				bankInfo.setUpdateDate(new Date());
				
				int result = corporationBankService.updateCorporationBankByKey(bankInfo);
				if(result <= 0){
					resualtMap.put("success", "e");
					resualtMap.put("msg", "修改银行信息失败!");
				}else{
					resualtMap.put("success", "s");
					resualtMap.put("msg", "修改银行信息成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	/**
	 * 根据主键删除企业银行信息(非物理删除)
	 * @param pid
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/delBank",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> delBankByKey(String pid,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		CorporationBankInfo bankInfo = new CorporationBankInfo();
		try {
			if(StringUtils.isBlank(pid)){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "删除失败!");
			}else{
				UserInfo currUser = super.getCurrUser(req);
				bankInfo.setUpdateId(currUser.getPid());
				bankInfo.setUpdateDate(new Date());
				bankInfo.setStatus(0);
				
				int result = corporationBankService.updateCorporationBankByKey(bankInfo);
				if(result <= 0){
					resualtMap.put("success", "e");
					resualtMap.put("msg", "删除失败!");
				}else{
					resualtMap.put("success", "s");
					resualtMap.put("msg", "删除成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	
	/**
	 * 上传企业资料扫描件
	 * @author  lixq 
	 * @param   corPid
	 * @param   corDataType
	 * @param   fileName
	 * @param   request
	 * @param   response
	 * @return  Map<String,Object>  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/uploadCorDataFile")
	@ResponseBody
	private Map<String, Object> uploadCorDataFile(String corPid,String corDataType,String fileTypeName,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EstateFundFile> uploadFile = null;
		UserInfo currUser = super.getCurrUser(request);
		CorporationDataInfo cdi = new CorporationDataInfo();
		try {
			String modulePath = EstateFundModelConstants.TRADE;
			uploadFile = FileUpload.uploadFile(request, response, modulePath);
			
			if(uploadFile != null && uploadFile.size() > 0){
				EstateFundFile estateFundFile = uploadFile.get(0);
				String file_id = estateFundFile.getPid();
				String fileUrl = estateFundFile.getFileUrl();
				String realName = estateFundFile.getRealName();
				
				cdi.setFileId(file_id);
				cdi.setCorpId(corPid);
				cdi.setFileTypeName(fileTypeName);
				cdi.setCorDataType(corDataType);
				cdi.setCreateDate(new Date());
				cdi.setCreateId(currUser.getPid());
				
				String resultId = corporationDataInfoService.saveCorporationDataInfo(cdi);
				if(StringUtils.isNotBlank(resultId)){
					resultMap.put("success", "s");
					resultMap.put("msg", "上传成功!");
					resultMap.put("fileUrl", fileUrl);
					resultMap.put("fileName", realName);
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
			resultMap.put("msg", "系统出错,上传企业资料失败!");
			if(uploadFile != null && uploadFile.size() > 0){
				FileUpload.deleteFile(uploadFile.get(0).getPid(), uploadFile.get(0).getFileUrl());
			}
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 删除企业资料扫描件
	 * @author  lixq 
	 * @param   corPid
	 * @param   corDataType
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/delCorFile",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> delCorFile(String corPid,String corDataType,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		UserInfo currUser = super.getCurrUser(req);
		String updateId = currUser.getPid();
		try {
			int result = corporationDataInfoService.delCorporationByCondition(corPid, corDataType, updateId);
			if(result <= 0){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "删除失败!");
			}else{
				resualtMap.put("success", "s");
				resualtMap.put("msg", "删除成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	
	/**
	 * 根据企业信息主键获取所有有效状态的企业资料扫描件信息
	 * @author  lixq 
	 * @param   corPid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月15日
	 */
	@ResponseBody
	@RequestMapping(value="/getAllCorFile")
	private Map<String,Object> getAllCorFile(String corPid,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		List<CorporationDataInfo> dataInfoList = null;
		try {
			if(StringUtils.isNotBlank(corPid)){
				dataInfoList = corporationDataInfoService.getCorDataInfoByCorPid(corPid);
				resualtMap.put("success", "s");
				resualtMap.put("msg", "获取数据成功!");
				resualtMap.put("dataInfoList",dataInfoList);
			}else{
				resualtMap.put("success", "e");
				resualtMap.put("msg", "获取企业资料数据失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
}
