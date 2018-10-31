package com.upjf.fund.web.controller.business.trade;

import java.util.ArrayList;
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
import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.dto.CorporationInfo;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.Payment;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.service.business.CorporationService;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.business.StockholderInfoService;
import com.upjf.fund.service.system.EstateFundFileService;
import com.upjf.fund.utils.SplitCommaUtils;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.file.upload.FileUpload;

/**
 * 该控制层用于项目股东付款请求的列表查看与相关维护功能,项目股东回款管理
 * @author lixq
 * @date   2018年9月26日
 */
@Controller
@RequestMapping("/projectStockerController")
public class ProjectStockerController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(ProjectStockerController.class);
	
	@Autowired
	private ProjectInfoService projectInfoService;								//项目信息服务
	
	@Autowired
	private CorporationService corporationService;								//企业信息服务
	
	@Autowired
	private StockholderInfoService stockholderInfoService;						//股东相关操作服务
	
	@Autowired
	protected EstateFundFileService estateFundFileService;						//文件服务
	
	
	/**
	 * 进入项目股东付款请求信息列表查看及查询检索
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年10月15日
	 */
	@RequestMapping(value="/stoPayList")
	private String stoPayList(Payment payMent,Page page,Model model,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<ProjectInfo> projectList = projectInfoService.getProjectListByStatus(1);
			List<CorporationInfo> corInfoList = corporationService.getAllCorInfoList(1);
			
			resultMap = stockholderInfoService.getStockHolderPayByPage(payMent, page);
			
			model.addAttribute("projectList", projectList);
			model.addAttribute("corInfoList", corInfoList);
			model.addAttribute("stockHolderPayList", resultMap.get("stockHolderPayList")); 
			model.addAttribute("page", resultMap.get("page")); 
			model.addAttribute("payMent", payMent); 
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,查询获取项目股东付款请求列表信息失败!");
		}
		
		return "trade/project_stocker_pay/share_pay";
	}
	
	
	
	/**
	 * 根据付款主键,分页获取该付款主键下的项目股东付款请求附件信息
	 * @author  lixq 
	 * @param   payMentPid
	 * @param   page
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月22日
	 */
	@RequestMapping(value="/getStockHolderVoucherList",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> getStockHolderVoucherList(String payMentPid,Page page,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(payMentPid)){
				Map<String, Object> voucherMap = stockholderInfoService.getStockHolderVoucherByPage(payMentPid, page);
				resultMap.put("success", "s");
				resultMap.put("msg", "获取凭证信息成功!");
				resultMap.put("voucherList", voucherMap.get("voucherList"));
				resultMap.put("page", voucherMap.get("page"));
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "获取凭证信息失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取股东付款凭证列表信息失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错!");
		}
		return resultMap;
	}
	
	
	
	
	
	/**
	 * 进入新增,修改界面
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年10月22日
	 */
	@RequestMapping(value="/toStockHolderPay")
	private String toStockHolderPay(String payMentPid,String type,Model model,HttpServletRequest req){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			List<ProjectInfo> projectList = projectInfoService.getProjectListByStatus(1);
			
			if(StringUtils.isNotBlank(payMentPid)){
				resultMap = stockholderInfoService.getStockHolderPayMentByPayMentPid(payMentPid);
				
			}
			
			model.addAttribute("projectList", projectList);
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("type", type);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,进入新增,修改获取下拉框列表数据失败!");
		}
		
		return "trade/project_stocker_pay/share_pay_add";
	}
	
	
	
	/**
	 * 根据项目主键,获取对应的项目公司信息
	 * @author  lixq 
	 * @param   firstRelationPid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月22日
	 */
	@RequestMapping(value="/getReceiveCompany",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> getReceiveCompany(String firstRelationPid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(firstRelationPid)){
				List<Map<String,Object>> list = stockholderInfoService.getProjectCompanyByProjectPid(firstRelationPid);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取成功!");
				resultMap.put("resultDataList", list);
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数非法!");
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
	 * 根据项目公司主键及项目主键,查询获取有效状态下的股东信息
	 * @author  lixq 
	 * @param   firstRelationPid
	 * @param   secondRelationPid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月22日
	 */
	@RequestMapping(value="/getContributiveCompany",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> getContributiveCompany(String firstRelationPid,String secondRelationPid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(firstRelationPid) && StringUtils.isNotBlank(secondRelationPid)){
				List<Map<String,Object>> list = stockholderInfoService.getStockHolderByProjectComPanyPid(firstRelationPid,secondRelationPid);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取成功!");
				resultMap.put("resultDataList", list);
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数非法!");
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
	 * 根据企业主键查询获取该企业银行信息
	 * @author  lixq 
	 * @param   firstRelationPid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date    2018年10月22日
	 */
	@RequestMapping(value="/getBankInfo",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> getBankInfo(String firstRelationPid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(firstRelationPid)){
				List<Map<String,Object>> list = stockholderInfoService.getBankInfoByComPanyPid(firstRelationPid);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取成功!");
				resultMap.put("resultDataList", list);
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数非法!");
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
	 * 根据企业信息主键和银行主键,获取银行账号信息
	 * @author  lixq 
	 * @param   firstRelationPid
	 * @param   secondRelationPid
	 * @param   req
	 * @return  Map<String,Object>  
	 * @date 2018年10月23日
	 */
	@RequestMapping(value="/getBankAccount",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> getBankAccount(String firstRelationPid,String secondRelationPid,HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(firstRelationPid)){
				List<Map<String,Object>> list = stockholderInfoService.getBankInfoByComPanyPidAndBankId(secondRelationPid,firstRelationPid);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "获取成功!");
				resultMap.put("resultDataList", list);
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "参数非法!");
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
	 * 上传股东付款请求附件
	 * @author  lixq 
	 * @param   payMentPid
	 * @param   request
	 * @param   response
	 * @return  Map<String,Object>  
	 * @date    2018年10月23日
	 */
	@RequestMapping(value="/uploadStockHolderPayFile")
	@ResponseBody
	private Map<String, Object> uploadStockHolderPayFile(String payMentPid,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EstateFundFile> uploadFile = new ArrayList<EstateFundFile>();
		try {
			String modulePath = EstateFundModelConstants.STOCKHOLDER_INVEST_PAYMENT_ACCESSORY;
			uploadFile = FileUpload.uploadFile(request, response, modulePath);
			if(uploadFile != null && uploadFile.size() > 0){
				String accessoryType = EstateFundConstants.PAYMENT_ACCESSORY_PAY;
				estateFundFileService.batchInsertPaymentAccessory(uploadFile, accessoryType, payMentPid);
				
				resultMap.put("success", "s");
				resultMap.put("msg", "上传成功!");
				resultMap.put("refreshType", "stockHolderPay");
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "系统出错,上传股东付款请求附件失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,上传股东付款请求附件失败!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 批量删除股东付款请求
	 * @author  lixq 
	 * @param   payMentPids
	 * @param   request
	 * @param   response
	 * @return  Map<String,Object>  
	 * @date    2018年10月23日
	 */
	@RequestMapping(value="/delStockHolderPay")
	@ResponseBody
	private Map<String, Object> delStockHolderPay(String payMentPids,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(payMentPids)){
				List<String> pids = SplitCommaUtils.splitCorPids(payMentPids);
				if(pids !=null && pids.size() > 0){
					int result = stockholderInfoService.delStockHolderPayByPids(pids);
					if(result > 0){
						resultMap.put("success", "s");
						resultMap.put("msg", "删除成功!");
					}else{
						resultMap.put("success", "e");
						resultMap.put("msg", "删除失败!");
					}
				}else{
					resultMap.put("success", "e");
					resultMap.put("msg", "请选择要删除的记录!");
				}
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "请选择要删除的记录!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,删除失败!");
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 项目股东回款管理初始化信息列表
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date    2018年10月22日
	 */
	@RequestMapping(value="/stoPayBackList")
	private String stoPayBackList(Model model,HttpServletRequest req){
		
		return "trade/project_stocker_pay_back/share_return";
	}
	
	
}
