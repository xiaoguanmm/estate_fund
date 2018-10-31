package com.upjf.fund.web.controller.business.trade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upjf.fund.web.controller.base.BaseController;

/**
 * 该控制层用于项目投资人付款请求列表查看与相关维护功能,投资人回款信息列表查看及检索
 * @author lixq
 * @date 2018年9月26日
 */
@Controller
@RequestMapping("/investorController")
public class InvestorController extends BaseController{
	
	
	/**
	 * 进入投资人付款请求信息列表查看及查询检索
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/investorPayList")
	private String toList(Model model,HttpServletRequest req){
		
		return "trade/investor_pay/investor_pay";
	}
	
	
	
	/**
	 * 进入投资人回款管理列表
	 * @author  lixq 
	 * @param   model
	 * @param   req
	 * @return  String  
	 * @date 2018年10月15日
	 */
	@RequestMapping(value="/investorPayBackList")
	private String investorPayBackList(Model model,HttpServletRequest req){
		
		return "trade/investor_pay_back/investor_return";
	}
	
	
}
