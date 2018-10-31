package com.upjf.fund.web.controller.business.finance.invest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.upjf.fund.constants.TagConstants;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.InvestSubjectFinanceVo;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.InvestSubjectFinanceService;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.web.controller.base.BaseController;

/**
 * 该控制层用于投资主体财务管理列表查看与相关维护功能
 * @author lixq
 * @date 2018年9月26日
 */
@Controller
@RequestMapping("/investSubjectFinanceController")
public class InvestSubjectFinanceController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(InvestSubjectFinanceController.class);
	
	
	
	@Autowired
	private DictInfoService dictInfoService;													//系统字典服务
	
	@Autowired
	private CorporationService corporationService;												//企业信息服务
	
	@Autowired
	private InvestSubjectFinanceService investSubjectFinanceService;							//投资主体财务管理服务
	
	
	/**
	 * 进入投资主体财务管理列表及查询检索
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/subjectFinanceList")
	private String subjectFinanceList(InvestSubjectFinanceVo investSubjectFinanceVo,Page page,Model model,HttpServletRequest req){
		List<DictDetail> contributiveTypeList = null;														//出资类别
		List<CorporationInfo> investSubjectList = null;														//投资主体信息
		Map<String, Object> map = new HashMap<String, Object>() ;
		try {
			investSubjectList = corporationService.getAllCorInfoList(1);
			contributiveTypeList = dictInfoService.getDictDetailsByCode(TagConstants.CONTRIBUTIVE_TYPE);
			map = investSubjectFinanceService.getInvestSubjectFinanceListByPage(investSubjectFinanceVo, page);
			
			
			model.addAttribute("subjectFinanceList", map.get("subjectFinanceList"));
			model.addAttribute("page", map.get("page"));
			model.addAttribute("investSubjectList", investSubjectList);
			model.addAttribute("contributiveTypeList", contributiveTypeList);
			model.addAttribute("investSubjectFinanceVo", investSubjectFinanceVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "finance/invest/invest_subject_finance/subject_finance";
	}
	
	
	
	
	/**
	 * 进入投资主体查看详情
	 * @author  lixq 
	 * @param   investSubjectFinanceVo
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年10月17日
	 */
	@RequestMapping(value="/toSubjectFinanceDetail")
	private String toSubjectFinanceDetail(InvestSubjectFinanceVo subjectFinanceVo,Model model,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(subjectFinanceVo !=null){
				String projectPid = subjectFinanceVo.getProjectPid();
				String subjectPid = subjectFinanceVo.getSubjectPid();
				if(StringUtils.isNotBlank(subjectPid) && StringUtils.isNotBlank(projectPid)){
					map = investSubjectFinanceService.getSubjectFinanceDetail(subjectPid, projectPid);
				}
				
				List<DictDetail> corDataInfoList = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_DATA_TYPE);
				List<DictDetail> bankList = dictInfoService.getDictDetailsByCode(TagConstants.BANK_NAME);
				model.addAttribute("investSubject", map.get("investSubject"));
				model.addAttribute("corporationInfo", map.get("corporationInfo"));
				model.addAttribute("corDataInfoList", corDataInfoList);
				model.addAttribute("bankList", bankList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "finance/invest/invest_subject_finance/subject_finance_see";
	}
	
	
	
	/**
	 * 分页获取投资主体(投资人)的附件列表
	 * @author  lixq 
	 * @param   subjectPid
	 * @param   page
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月18日
	 */
	@RequestMapping(value="/getSubjectFileList",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> getSubjectFileListByPage(String subjectPid,Page page,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		Map<String,Object> pageListMap = new HashMap<String, Object>();
		
		try {
			if(StringUtils.isBlank(subjectPid)){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "获取投资主体信息失败!");
			}else{
				pageListMap = investSubjectFinanceService.getSubjectFileListByPage(subjectPid, page);
				resualtMap.put("success", "s");
				resualtMap.put("msg", "获取企业银行信息成功!");
				resualtMap.put("subjectFileList", pageListMap.get("subjectFileList"));
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
	 * 分页加载投资主体付款信息
	 * @author  lixq 
	 * @param   subjectPid
	 * @param   page
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月18日
	 */
	@RequestMapping(value="/subFinanceDetailList")
	@ResponseBody
	private Map<String, Object> subFinanceDetailList(String subjectPid,Page page,Model model,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(StringUtils.isNotBlank(subjectPid)){
				Map<String, Object> map = investSubjectFinanceService.getSubjectFinanceDetailListByPage(subjectPid, page);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取数据成功!");
				resultMap.put("subFinanceDetailList", map.get("subFinanceDetailList"));
				resultMap.put("PrePayAndActualPayAmount", map.get("PrePayAndActualPayAmount"));
				resultMap.put("page", map.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "获取数据失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,获取数据失败!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 根据付款主键,获取当前该条件下对应的付款明细信息
	 * @author  lixq 
	 * @param   subjectPayPid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月19日
	 */
	@RequestMapping(value="/getSubjectPaymentDetail",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> getSubjectPaymentDetail(String subjectPayPid,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		Map<String,Object> resultListMap = new HashMap<String, Object>();
		
		try {
			if(StringUtils.isBlank(subjectPayPid)){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "获取投资主体付款明细信息失败!");
			}else{
				resultListMap = investSubjectFinanceService.getSubjectPaymentDetailByPid(subjectPayPid);
				resualtMap.put("success", "s");
				resualtMap.put("msg", "获取成功!");
				resualtMap.put("subjectPaymentDetail", resultListMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	/**
	 * 分页加载投资主体单条付款对应的附件信息列表
	 * @author  lixq 
	 * @param   subjectPayPid
	 * @param   page
	 * @param   model
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月19日
	 */
	@RequestMapping(value="/getSubjectPaymentDetailFileList")
	@ResponseBody
	private Map<String, Object> getSubjectPaymentDetailFileList(String subjectPayPid,Page page,Model model,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(StringUtils.isNotBlank(subjectPayPid)){
				Map<String, Object> map = investSubjectFinanceService.getSubjectPaymentDetailFileList(subjectPayPid, page);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取数据成功!");
				resultMap.put("subjectPaymentDetailFileList", map.get("subjectPaymentDetailFileList"));
				resultMap.put("page", map.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "获取数据失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,获取数据失败!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 根据投资主体主键,分页加载投资主体财务详情回款信息列表
	 * @author  lixq 
	 * @param   subjectPid
	 * @param   page
	 * @param   model
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月19日
	 */
	@RequestMapping(value="/subjectReceiveDetailList")
	@ResponseBody
	private Map<String, Object> subjectReceiveDetailList(String subjectPid,Page page,Model model,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(StringUtils.isNotBlank(subjectPid)){
				Map<String, Object> map = investSubjectFinanceService.getSubjectReceiveDetailListByPage(subjectPid, page);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取数据成功!");
				resultMap.put("subjectReceiveDetailList", map.get("subjectReceiveDetailList"));
				resultMap.put("receiverAmountAndProfit", map.get("receiverAmountAndProfit"));
				resultMap.put("page", map.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "获取数据失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,获取数据失败!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 根据投资主体回款信息主键,获取该条主键下的回款明细
	 * @author  lixq 
	 * @param   subjectReturnPid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月19日
	 */
	@RequestMapping(value="/getSubjectReturnDetail",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> getSubjectReturnDetail(String subjectReturnPid,HttpServletRequest req){
		Map<String,Object> resualtMap = new HashMap<String, Object>();
		Map<String,Object> resultListMap = new HashMap<String, Object>();
		
		try {
			if(StringUtils.isBlank(subjectReturnPid)){
				resualtMap.put("success", "e");
				resualtMap.put("msg", "获取投资主体回款明细信息失败!");
			}else{
				resultListMap = investSubjectFinanceService.getSubjectReturnDetailByPid(subjectReturnPid);
				resualtMap.put("success", "s");
				resualtMap.put("msg", "获取成功!");
				resualtMap.put("subjectReturnDetail", resultListMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resualtMap.put("success", "e");
			resualtMap.put("msg", "系统出错!");
		}
		
		return resualtMap;
	}
	
	
	@RequestMapping(value="/getSubjectReturnDetailFileList")
	@ResponseBody
	private Map<String, Object> getSubjectReturnDetailFileList(String subjectReturnPid,Page page,Model model,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(StringUtils.isNotBlank(subjectReturnPid)){
				Map<String, Object> map = investSubjectFinanceService.getSubjectReturnDetailFileList(subjectReturnPid, page);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取数据成功!");
				resultMap.put("subjectReturnDetailFileList", map.get("subjectReturnDetailFileList"));
				resultMap.put("page", map.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "获取数据失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,获取数据失败!");
		}
		
		return resultMap;
	}
	
	
}
