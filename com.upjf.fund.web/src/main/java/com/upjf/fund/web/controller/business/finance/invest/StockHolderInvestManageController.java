package com.upjf.fund.web.controller.business.finance.invest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.dto.InvestManagePlan;
import com.upjf.fund.dto.Payment;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.web.aspect.annotation.Log;

/**
 * 项目股东出资审核管理
 * @company upjf.com
 * @author guantong
 *
 */
@Controller
public class StockHolderInvestManageController extends InvestManageController{
	
	@RequestMapping("/toStockholderInvestList")
	public ModelAndView toStockholderInvestList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> amountMap = this.investerManageService.statisticsAmount(EstateFundConstants.PAYMENT_TYPE_STOCKHOLDER);
		mv.addObject("amount", amountMap);
		mv.setViewName(STOCKHOLDER_INVEST_LIST);
		return mv;
	}
	
	@RequestMapping(value="/queryStockholderInvestList", produces = "application/json;charset=utf-8")
	protected @ResponseBody String queryStockholderInvestList(Payment payment, HttpServletRequest request,HttpServletResponse response){
		
		return queryInvestList(payment, request, response);
	}
	
	@RequestMapping("/toStockholderInvestConfirm")
	public ModelAndView toStockholderInvestConfirm(String pid,String showDetail){
		ModelAndView mv = new ModelAndView();
		Payment payment = this.investerManageService.getPaymentByPrimaryKey(pid);
		/*获取资管计划中的【预计出资规模】，为了计算【预计待付本金】*/
		InvestManagePlan investManagePlan = new InvestManagePlan();
		investManagePlan.setPrjId(payment.getPrjId());
		investManagePlan.setBusinessPrjInfoId(payment.getReceiverId());
		investManagePlan.setStockholderId(payment.getStockholderId());
		investManagePlan = this.investerManageService.getInvestManagePlanByParams(investManagePlan);
		if(investManagePlan!=null && investManagePlan.getExpectInvestAmount() !=null){
			investManagePlan.setExpectInvestAmount(investManagePlan.getExpectInvestAmount().divide(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		mv.addObject("investManagePlan", investManagePlan);
		mv.addObject("payment", payment);
		if(EstateFundConstants.YES.equals(showDetail)){
			mv.addObject("showDetail", EstateFundConstants.YES);
		}
		mv.setViewName(STOCKHOLDER_INVEST_CONFIRM);
		return mv;
	}
	
	@RequestMapping(value="/queryStockholderInvestAccessoryList", produces = "application/json;charset=utf-8")
	protected @ResponseBody String queryStockholderInvestAccessoryList(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return queryInvestAccessoryList(pid, request, response);
	}
	
	/**
	 * 出资现金流确认
	 * @param payment
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="财务管理-项目股东出资审核",content="出资现金流确认")
	@RequestMapping("/stockholderInvestConfirm")
	public @ResponseBody ModelMap stockholderInvestConfirm(Payment payment, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(payment!=null && StringUtils.isNotEmpty(payment.getPid())){
			UserInfo userInfo = getUserInfo();
			payment.setUpdateId(userInfo.getPid());
			payment.setUpdateDate(new Date());
			if(EstateFundConstants.FINANCE_CONFIRM.equals(payment.getFinanceConfirmStatus())){
				payment.setInvestorConfirm(userInfo.getPid());
				payment.setInvestorConfirmDate(new Date());
			}
			payment.setPaymentType(EstateFundConstants.PAYMENT_TYPE_STOCKHOLDER);
			int result = this.investerManageService.updatePaymentBySelective(payment);
			if(result>0){
				mm.put("successMsg", "更新成功");
				/*已确认的不允许再次确认*/
				if(EstateFundConstants.FINANCE_CONFIRM.equals(payment.getFinanceConfirmStatus())){
					mm.put("readonly", true);//保存成功之后，不允许再次保存,只为只读状态
				}
			}
			
		}else{
			mm.put("errMsg", "更新失败");
		}
		return mm;
	}
	
	/**
	 * 上传股东出资付款凭证
	 * @return
	 */
	@Log(module="财务管理-项目股东出资审核",content="上传股东收款凭证")
	@RequestMapping("/uploadStockholderPaymentAccessory")
	public @ResponseBody ModelMap uploadStockholderPaymentAccessory(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return uploadInvestPaymentAccessory(pid,EstateFundModelConstants.STOCKHOLDER_INVEST_PAYMENT_ACCESSORY,EstateFundConstants.PAYMENT_ACCESSORY_RECEIVE, request, response);
	}
}
