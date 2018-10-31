package com.upjf.fund.web.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.utils.MD5Utils;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.aspect.annotation.Log;


/**
 * 用户管理
 * @author guantong
 *
 */
@Controller
public class UserManageController extends SystemManageController{
	
	@RequestMapping("/userManage")
	public String toMenuList(){
		return USER_MANAGE_PAGE;
	}
	
	@RequestMapping(value="/queryMenuList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryUserList(UserInfo userInfo,ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		
		List<Map<String, Object>> rows = this.userInfoService.getUserInfoByCondition(userInfo, getOffset(), getPageRows());
		
		Integer records = this.userInfoService.countUserInfoByCondition(userInfo);
		
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增用户信息
	 * @param userInfo
	 * @param request
	 * @param response
	 */
	@Log(module="系统管理-用户管理",content="新增用户")
	@RequestMapping("/addUserInfo")
	public @ResponseBody ModelMap addUserInfo(UserInfo userInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(userInfo==null){
			mm.put("errMsg", "用户信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(userInfo.getUserName())
				||StringUtils.isEmpty(userInfo.getName())
				||StringUtils.isEmpty(userInfo.getStatus())
				||StringUtils.isEmpty(userInfo.getPassword())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		String password = MD5Utils.encryptWithSalt(userInfo.getPassword(), EstateFundConstants.SALT);
		userInfo.setPassword(password);
		userInfo.setCreateId(getUserInfo().getPid());
		UserInfo existUser = this.userInfoService.getUserInfoByUserName(userInfo.getUserName());
		if(existUser!=null){
			mm.put("errMsg", "用户名已存在");
			return mm;
		}
		userInfo.setPid(UuidGenerator.getUuidGenerator());
		userInfo.setIsNew(EstateFundConstants.YES);
		userInfo.setCreateDate(new Date());
		int result = this.userInfoService.addUserInfo(userInfo);
		if(result>0){
			mm.put("successMsg", "新增用户成功");
			return mm;
		}else{
			mm.put("errMsg", "新增用户失败");
			return mm;
		}
	}
	
	@Log(module="系统管理-用户管理",content="修改用户")
	@RequestMapping("/modifyUserInfo")
	public @ResponseBody ModelMap modifyUserInfo( UserInfo userInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(userInfo==null){
			mm.put("errMsg", "用户信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(userInfo.getUserName())
				||StringUtils.isEmpty(userInfo.getName())
				||StringUtils.isEmpty(userInfo.getStatus())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		
		String oriUserName = request.getParameter("oriUserName");
		if(!oriUserName.equals(userInfo.getUserName())){
			UserInfo existUser = this.userInfoService.getUserInfoByUserName(userInfo.getUserName());
			if(existUser!=null){
				mm.put("errMsg", "用户名已存在");
				return mm;
			}
		}
		
		String resetPwdFlg = request.getParameter("resetPassword");
		if(EstateFundConstants.YES.equals(resetPwdFlg)){
			String password = MD5Utils.encryptWithSalt(EstateFundConstants.DEFALUT_USER_PWD, EstateFundConstants.SALT);
			userInfo.setPassword(password);
			userInfo.setIsNew(EstateFundConstants.YES);
		}
		userInfo.setUpdateId(getUserInfo().getPid());
		userInfo.setUpdateDate(new Date());
		int result = this.userInfoService.modifyUserInfo(userInfo);
		if(result>0){
			mm.put("successMsg", "修改成功");
			return mm;
		}else{
			mm.put("errMsg", "修改失败");
			return mm;
		}
	}
	
	@Log(module="系统管理-用户管理",content="修改用户状态")
	@RequestMapping("/switchStatus")
	public @ResponseBody ModelMap switchStatus(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String pid = request.getParameter("pid");
		String status = request.getParameter("status");
		if(StringUtils.isEmpty(pid)){
			mm.put("errMsg", "用户信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(status)){
			mm.put("errMsg", "状态为空");
			return mm;
		}else if(!("1".equals(status) || "0".equals(status))){
			mm.put("errMsg", "不为有效状态");
			return mm;
		}
		int result = this.userInfoService.switchStatus(pid,status);
		addLogInfo(request, "系统管理-用户管理", "修改用户状态");
		if(result>0){
			mm.put("successMsg", "修改成功");
			return mm;
		}else{
			mm.put("errMsg", "修改失败");
			return mm;
		}
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-用户管理",content="删除用户")
	@RequestMapping("/delUserInfo")
	public @ResponseBody ModelMap delUserInfo(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String pid = request.getParameter("pid");
		if(StringUtils.isEmpty(pid)){
			mm.put("errMsg", "用户信息为空");
			return mm;
		}
		int result = this.userInfoService.delUserInfo(pid);
		addLogInfo(request, "系统管理-用户管理", "删除用户");
		if(result>0){
			mm.put("successMsg", "删除成功");
			return mm;
		}else{
			mm.put("errMsg", "删除失败");
			return mm;
		}
	}
	
	/**
	 * 获取角色信息及用户角色信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getRoleInfo",produces = "application/json;charset=utf-8")
	public @ResponseBody String getRoleInfo(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> mm = new HashMap<String,Object>();
		String pid = request.getParameter("pid");
		if(StringUtils.isEmpty(pid)){
			mm.put("errMsg", "用户信息为空");
			
			return JSONObject.toJSONString(mm);
		}
		/*首先获取所有角色信息*/
		List<Map<String,Object>> allRoleInfos = this.roleInfoServicve.getAllSimpleRoleInfos();
		
		List<Map<String,Object>> ownRoleInfos = this.roleInfoServicve.getUserRoleInfosByPid(pid);
		
		if(allRoleInfos==null || allRoleInfos.size()==0){
			mm.put("errMsg", "系统无角色信息，请维护角色信息");
			return JSONObject.toJSONString(mm);
		}
		mm.put("successMsg", "查询成功");
		mm.put("allRoleInfos", allRoleInfos);
		mm.put("ownRoleInfos", ownRoleInfos);
		return JSONObject.toJSONString(mm);
	}
	
	/**
	 * 数据授权
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-用户管理",content="用户授权")
	@RequestMapping("/userAuthorization")
	public @ResponseBody ModelMap userAuthorization(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String userPid = request.getParameter("pid");
		if(StringUtils.isEmpty(userPid)){
			mm.put("errMsg", "用户信息为空");
			return mm;
		}
		
		String rolePids = request.getParameter("rolePids");
		if(StringUtils.isEmpty(rolePids)){
			mm.put("errMsg", "至少选择一种角色");
			return mm;
		}
		String[] pids = rolePids.split("-");
		Subject subject  = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo)subject.getPrincipal();
		int result = this.roleInfoServicve.batchInsertUserRole(pids,userInfo,userPid); 
		addLogInfo(request, "系统管理-用户管理", "用户数据授权");
		if(result>0){
			mm.put("successMsg", "数据授权成功");
			return mm;
		}else{
			mm.put("errMsg", "数据授权失败");
			return mm;
		}
	}
	
}
