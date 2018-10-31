package com.upjf.fund.web.controller.business.finance.invest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.Payment;
import com.upjf.fund.service.business.InvesterManageService;
import com.upjf.fund.service.system.EstateFundFileService;
import com.upjf.fund.web.controller.business.finance.FinanceManageController;
import com.upjf.fund.web.utils.file.upload.FileUpload;

/**
 * 出资管理基类
 * @company upjf.com
 * @author guantong
 *
 */
public class InvestManageController extends FinanceManageController{
	
	/**新增操作*/
	protected static final String OPERATION_TYPE_ADD = "add";
	
	/**修改操作*/
	protected static final String OPERATION_TYPE_MODIFY = "modify";
	
	/**查看操作*/
	protected static final String OPERATION_TYPE_VIEW = "view";
	
	/**项目股东出资审核列表页面*/
	protected static final String STOCKHOLDER_INVEST_LIST = "finance/invest/stockholder_invest/stockholder_invest_manage";
	
	/**项目股东出资审核确认页面*/
	protected static final String STOCKHOLDER_INVEST_CONFIRM = "finance/invest/stockholder_invest/stockholder_invest_confirm";
	
	/**投资主体出资管理页面*/
	protected static final String INVEST_SUBJECT_INVEST_LIST = "finance/invest/invest_subject_invest/invest_subject_invest_manage";
	
	/**投资主体出资操作页面*/
	protected static final String INVEST_SUBJECT_INVEST_OPERATION = "finance/invest/invest_subject_invest/invest_subject_invest_operation";
	
	/**投资人出资管理页面*/
	protected static final String INVESTOR_INVEST_LIST = "finance/invest/investor_invest/investor_invest_manage";
	
	/**投资人出资操作页面*/
	protected static final String INVESTOR_INVEST_OPERATION = "finance/invest/investor_invest/investor_invest_operation";
	
	@Autowired
	protected InvesterManageService investerManageService;
	
	@Autowired
	protected EstateFundFileService estateFundFileService;
	
	protected String queryInvestList(Payment payment, HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = this.investerManageService.getPaymentsByConditions(payment,getOffset(),getPageRows());
		Integer records = this.investerManageService.countPaymentsByConditions(payment);
		return putJsonData(rows, records);
	}
	
	/**
	 * 获取付款信息附件列表
	 * @param request
	 * @param response
	 * @return
	 */
	protected String queryInvestAccessoryList(String paymentId,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(paymentId)){
			List<Map<String, Object>> rows = this.estateFundFileService.getPaymentAccessoriesByPaymentId(paymentId,getOffset(),getPageRows());
			Integer records = this.estateFundFileService.countPaymentAccessoriesByPaymentId(paymentId);
			return putJsonData(rows, records);
		}
		return null;
	}
	
	/**
	 * 删除付款记录(逻辑删除)
	 * @param pid
	 * @param paymentType
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelMap delPaymentRecord(String pid,String paymentType,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		int result = this.investerManageService.updatePaymentStatus(pid,EstateFundConstants.STATUS_DELETE);
		if(result>0){
			mm.put("successMsg", "删除出资请求成功");
			Map<String,Object> amountMap = this.investerManageService.statisticsAmount(paymentType);
			mm.put("amount", amountMap);
			return mm;
		}
		mm.put("errMsg", "删除出资请求失败");
		return mm;
	}
	
	/**
	 * 上传附件
	 * @param paymentId
	 * @param modulePath 模块路径
	 * @param accessoryType 附件类型
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelMap uploadInvestPaymentAccessory(String paymentId,String modulePath,String accessoryType, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(StringUtils.isNotEmpty(paymentId)){
			List<EstateFundFile> files = FileUpload.uploadFile(request, response, modulePath);
			this.estateFundFileService.batchInsertPaymentAccessory(files,accessoryType,paymentId);
			if(files!=null && files.size()>0){
				mm.put("successMsg", "上传成功");
				return mm;
			}
		}else{
			mm.put("errMsg", "请先保存数据");
		}
		return mm;
	}

}
