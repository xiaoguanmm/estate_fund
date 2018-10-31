package com.upjf.fund.web.controller.system;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.exception.UserException;
import com.upjf.fund.utils.MD5Utils;
import com.upjf.fund.web.aspect.annotation.Log;

/**
 * 用户登录
 * @company upjf.com
 * @author guantong
 *
 */
@Controller
public class LoginController extends SystemManageController{
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		
		return LOGIN;
	}

	@Log(module="系统管理",content="用户登录")
	@RequestMapping("/login")
	public @ResponseBody ModelMap login(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelMap msg = new ModelMap();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String captchaCode = request.getParameter("captcha");
		String captchaSessionCode = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String errMsg ="";
		if(StringUtils.isEmpty(captchaSessionCode)){
			errMsg ="验证码失效";
		}else if(!captchaSessionCode.equalsIgnoreCase(captchaCode)){
			errMsg ="验证码错误";
		}else if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			errMsg = "用户名密码不能为空";
		}
		if(StringUtils.isNotEmpty(errMsg)){
			msg.put("errMsg", errMsg);
			return msg;
		}
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		try {
			token.setUsername(userName);
			token.setPassword(MD5Utils.encryptWithSalt(password,EstateFundConstants.SALT).toCharArray());
			subject.login(token);
		} catch (UnknownAccountException e) {
				errMsg = "用户名或密码错误";
		}catch(IncorrectCredentialsException e){
			errMsg = "用户名或密码错误";
		}catch(UserException e){
			errMsg = e.getMessage();
		}
		
		if(StringUtils.isNotEmpty(errMsg)){
			if(StringUtils.isNotEmpty(errMsg)){
				msg.put("errMsg", errMsg);
				return msg;
			}
		}
		if(!"1".equals(((UserInfo)subject.getPrincipal()).getStatus())){
			msg.put("errMsg", "用户不为有效状态，请联系管理员");
			return msg;
		}
		
		msg.put("successMsg", "登录成功");
		return msg;
	}
	
	@RequestMapping("/main")
	public String redirectToMain(HttpServletRequest request,HttpServletResponse response){
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo)subject.getPrincipal();
		request.getSession().setAttribute("userInfo", userInfo);
		/*查询获取目录信息*/
		if(request.getSession().getAttribute("menuList")==null){
			List<Map<String,Object>> allRootMenus = resourceInfoService.findMenusByUserPid(userInfo.getPid());
			treeMenus(allRootMenus,allRootMenus.size()-1);
			String menus = JSON.toJSONString(allRootMenus);
			request.getSession().setAttribute("menuList", menus);
		}
		request.getSession().setAttribute("Globals", globals());
		return SUCCESS;
	}
	
	@Log(module="系统管理",content="用户修改密码")
	@RequestMapping("/changePassword")
	public @ResponseBody ModelMap changePassword(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) subject.getPrincipal();
		String oripassword = request.getParameter("oripassword");
		/*校验原密码*/
		if(StringUtils.isEmpty(oripassword)){
			mm.put("errMsg", "原密码不能为空");
			return mm;
		}else{
			String encryptOriPassword = MD5Utils.encryptWithSalt(oripassword, EstateFundConstants.SALT);
			if(!encryptOriPassword.equals(userInfo.getPassword())){
				mm.put("errMsg", "原密码不正确");
				return mm;
			}
		}
		/*校验新密码*/
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(password)){
			mm.put("errMsg", "新密码不能为空");
			return mm;
		}else if(!password.equals(request.getParameter("repassword"))){
			mm.put("errMsg", "两次输入密码不一致");
			return mm;
		}else if(password.equals(oripassword)){
			mm.put("errMsg", "不能与原密码一致");
			return mm;
		}else{
			String pid = request.getParameter("pid");
			password = MD5Utils.encryptWithSalt(password, EstateFundConstants.SALT);
			int result = this.userInfoService.updatePasswordByPid(pid,password);
			if(result>0){
				mm.put("successMsg", "更新成功");
				return mm;
			}else{
				mm.put("errMsg", "更新失败");
				return mm;
			}
		}
	}
	
	@Log(module="系统管理",content="新用户强制修改密码")
	@RequestMapping("/chgnewpwd")
	public @ResponseBody ModelMap chgnewpwd(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) subject.getPrincipal();
		/*校验新密码*/
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(password)){
			mm.put("errMsg", "新密码不能为空");
			return mm;
		}else if(!password.equals(request.getParameter("repassword"))){
			mm.put("errMsg", "两次输入密码不一致");
			return mm;
		}else if(MD5Utils.encryptWithSalt(password, EstateFundConstants.SALT).equals(userInfo.getPassword())){
			mm.put("errMsg", "不能与原密码一致");
			return mm;
		}else{
			String pid = request.getParameter("pid");
			password = MD5Utils.encryptWithSalt(password, EstateFundConstants.SALT);
			int result = this.userInfoService.updateNewUserPasswordByPid(pid,password,EstateFundConstants.NO);
			if(result>0){
				mm.put("successMsg", "更新成功");
				return mm;
			}else{
				mm.put("errMsg", "更新失败");
				return mm;
			}
		}
	}
	
}
