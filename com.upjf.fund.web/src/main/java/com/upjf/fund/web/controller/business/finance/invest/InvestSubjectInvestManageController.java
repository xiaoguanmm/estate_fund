package com.upjf.fund.web.controller.business.finance.invest;

import java.util.Date;
import java.util.List;
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
import com.upjf.fund.dto.Payment;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.aspect.annotation.Log;

/**
 * 投资主体出资管理
 * @company upjf.com
 * @author guantong
 *
 */
@Controller
public class InvestSubjectInvestManageController extends InvestManageController{
	
	@RequestMapping("/toInvestSubjectList")
	public ModelAndView toInvestSubjectList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> amountMap = this.investerManageService.statisticsAmount(EstateFundConstants.PAYMENT_TYPE_INVEST_SUBJECT);
		mv.addObject("amount", amountMap);
		mv.setViewName(INVEST_SUBJECT_INVEST_LIST);
		return mv;
	}
	
	@RequestMapping(value="/queryInvestSubjectInvestList", produces = "application/json;charset=utf-8")
	public @ResponseBody String queryInvestSubjectInvestList(Payment payment, HttpServletRequest request,HttpServletResponse response){
		// (accessType=1代表从资管计划页面进入投资主体付款信息)
		String accessType = request.getParameter("accessType");
		if("1".equals(accessType)) {
			String investPlanManagePid = request.getParameter("investPlanManagePid");
			String investSubjectPid = request.getParameter("investSubjectPid");
			String paymentType = request.getParameter("paymentType");
			payment.setAccessType(accessType);
			payment.setInvestPlanManagePid(investPlanManagePid);
			payment.setInvestSubjectPid(investSubjectPid);
			payment.setPaymentType(paymentType);
		}
		return queryInvestList(payment, request, response);
	}
	
	@RequestMapping(value="/queryInvestSubjectAccessoryList", produces = "application/json;charset=utf-8")
	public @ResponseBody String queryInvestSubjectAccessoryList(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return queryInvestAccessoryList(pid, request, response);
	}
	
	/**
	 * 跳转到新增投资主体投资页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toAddInvestSubjectInvest")
	public ModelAndView toAddInvestSubjectInvest(String operation,String pid,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isNotEmpty(pid) && (OPERATION_TYPE_MODIFY.equals(operation) || OPERATION_TYPE_VIEW.equals(operation))){
			Payment payment = this.investerManageService.getPaymentByPrimaryKey(pid);
			mv.addObject("payment", payment);
		}
		mv.addObject("operation", operation);
		mv.setViewName(INVEST_SUBJECT_INVEST_OPERATION);
		return mv;
	}
	
	/**
	 * 新增或者修改投资主体投资
	 * @param operation
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="财务管理-投资主体出资管理",content="新增/修改 投资主体出资")
	@RequestMapping("/addInvestSubjectInvest")
	public @ResponseBody ModelMap addInvestSubjectInvest(Payment payment,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(payment!=null){
			/*首先判断该投资主体是否存在这一期的出资*/
			List<Payment> paymentList = investerManageService.getPaymentByParams(payment);
			if(paymentList !=null && paymentList.size()>0){
				mm.put("errMsg", "已存在第【"+payment.getPayTerm()+"】期的付款请求，请重新选择付付款期数");
				return mm;
			}
			
			UserInfo userInfo = getUserInfo();
			if(StringUtils.isNotEmpty(payment.getPid())){
				mm.put("pid", payment.getPid());
				payment.setUpdateId(userInfo.getPid());
				payment.setUpdateDate(new Date());
				payment.setReceiverAmount(payment.getPayAmount());
				int result = this.investerManageService.updatePaymentBySelective(payment);
				if(result>0){
					mm.put("successMsg", "更新付款信息成功");
				}else{
					mm.put("errMsg", "更新付款信息失败");
				}
				return mm;
			}else{
				String pid = UuidGenerator.getUuidGenerator();
				payment.setPid(pid);
				payment.setCreateId(userInfo.getPid());
				payment.setCreateDate(new Date());
				payment.setInvestorOp(userInfo.getPid());
				payment.setInvestorOpDate(new Date());
				payment.setReceiverAmount(payment.getPayAmount());
				payment.setPaymentType(EstateFundConstants.PAYMENT_TYPE_INVEST_SUBJECT);
				int result = this.investerManageService.insertPaymentBySelective(payment);
				if(result>0){
					mm.put("pid", pid);
					mm.put("successMsg", "新增付款信息成功");
				}else{
					mm.put("errMsg", "新增付款信息失败");
				}
				return mm;
			}
			
		}else{
			mm.put("errMsg", "付款信息不存在");
		}
		
		return mm;
	}
	
	/**
	 * 删除投资主体出资记录
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="财务管理-投资主体出资管理",content="删除投资主体出资记录")
	@RequestMapping("/delInvestSubjectPaymentRecord")
	public @ResponseBody ModelMap delInvestSubjectPaymentRecord(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return delPaymentRecord(pid, EstateFundConstants.PAYMENT_TYPE_INVEST_SUBJECT, request, response);
	}
	
	/**
	 * 上传投资主体出资付款凭证
	 * @return
	 */
	@Log(module="财务管理-投资主体出资管理",content="上传投资主体付款凭证")
	@RequestMapping("/uploadInvestSubjectPaymentAccessory")
	public @ResponseBody ModelMap uploadInvestSubjectPaymentAccessory(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return uploadInvestPaymentAccessory(pid,EstateFundModelConstants.INVEST_SUBJECT_PAYMENT_ACCESSORY, EstateFundConstants.PAYMENT_ACCESSORY_PAY, request, response);
	}

}
