package com.upjf.fund.web.controller.business.trade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upjf.fund.web.controller.base.BaseController;

/**
 * 该控制层用于项目投资主体付款请求列表查看与相关维护功能,以及投资主体回款信息列表查看和检索
 * @author lixq
 * @date 2018年9月26日
 */
@Controller
@RequestMapping("/investSubjectController")
public class InvestorSubjectController extends BaseController{
	
	
	/**
	 * 进入投资主体付款请求信息列表查看及查询检索
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/subjectPayList")
	private String toList(Model model,HttpServletRequest req){
		
		return "trade/investor_subject_pay/subject_pay";
	}
	
	
	/**
	 * 进入投资主体回款列表及查询检索
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/subjectPayBackList")
	private String subjectPayBackList(Model model,HttpServletRequest req){
		
		return "trade/investor_subject_pay_back/subject_return";
	}
	
	
	
}
