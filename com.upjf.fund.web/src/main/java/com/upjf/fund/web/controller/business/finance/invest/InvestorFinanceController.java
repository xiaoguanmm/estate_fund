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
import org.springframework.web.bind.annotation.ResponseBody;

import com.upjf.fund.base.Page;
import com.upjf.fund.constants.TagConstants;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.InvestSubjectFinanceVo;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.InvestorFinanceService;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.web.controller.base.BaseController;

/**
 * 该控制层用于投资人财务管理列表查看与相关维护功能
 * @author lixq
 * @date 2018年9月26日
 */
@Controller
@RequestMapping("/investorFinanceController")
public class InvestorFinanceController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(InvestSubjectFinanceController.class);
	
	
	@Autowired
	private DictInfoService dictInfoService;													//系统字典服务
	
	@Autowired
	private CorporationService corporationService;												//企业信息服务
	
	
	@Autowired
	private InvestorFinanceService investorFinanceService;										//投资人财务管理服务
	
	/**
	 * 进入投资主人财务管理列表及查询检索
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/investorFinanceList")
	private String investorFinanceList(InvestSubjectFinanceVo investSubjectFinanceVo,Page page,Model model,HttpServletRequest req){
		List<CorporationInfo> investSubjectList = null;														//投资主体信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			investSubjectList = corporationService.getAllCorInfoList(1);
			map = investorFinanceService.getInvestorFinanceListByPage(investSubjectFinanceVo, page);
			
			model.addAttribute("page", map.get("page"));
			model.addAttribute("investorFinanceList", map.get("investorFinanceList"));
			model.addAttribute("investSubjectList", investSubjectList);
			model.addAttribute("investSubjectFinanceVo", investSubjectFinanceVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "finance/invest/investor_finance/investor_finance";
	}
	
	
	
	/**
	 * 进入每一条投资人的详情
	 * @author  lixq 
	 * @param   investorPid
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年10月25日
	 */
	@RequestMapping(value="/toInvestorFinanceDetail")
	private String toInvestorFinanceDetail(String investorPid,Model model,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(investorPid)){
				map = investorFinanceService.getInvestorFinanceDetail(investorPid);
			}
			
			List<DictDetail> corDataInfoList = dictInfoService.getDictDetailsByCode(TagConstants.CORPORATION_DATA_TYPE);
			List<DictDetail> bankList = dictInfoService.getDictDetailsByCode(TagConstants.BANK_NAME);
			model.addAttribute("investor", map.get("investor"));
			model.addAttribute("corporationInfo", map.get("corporationInfo"));
			model.addAttribute("corDataInfoList", corDataInfoList);
			model.addAttribute("bankList", bankList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取数据失败!");
		}
		
		return "finance/invest/investor_finance/investor_finance_see";
	}
	
	
	/**
	 * 分页加载投资人付款信息
	 * @author  lixq 
	 * @param   subjectPid
	 * @param   page
	 * @param   model
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月29日
	 */
	@RequestMapping(value="/investorFinanceDetailList")
	@ResponseBody
	private Map<String, Object> investorFinanceDetailList(String subjectPid,Page page,Model model,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(StringUtils.isNotBlank(subjectPid)){
				Map<String, Object> map = investorFinanceService.getInvestorFinanceDetailListByPage(subjectPid, page);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取数据成功!");
				resultMap.put("investorFinanceDetailList", map.get("investorFinanceDetailList"));
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
	 * 分页获取投资人的回款信息列表
	 * @author  lixq 
	 * @param   subjectPid
	 * @param   page
	 * @param   model
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月29日
	 */
	@RequestMapping(value="/investorReceiveDetailList")
	@ResponseBody
	private Map<String, Object> investorReceiveDetailList(String subjectPid,Page page,Model model,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(StringUtils.isNotBlank(subjectPid)){
				Map<String, Object> map = investorFinanceService.getInvestorReceiveDetailListByPage(subjectPid, page);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取数据成功!");
				resultMap.put("investorReceiveDetailList", map.get("investorReceiveDetailList"));
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
	
	
}
