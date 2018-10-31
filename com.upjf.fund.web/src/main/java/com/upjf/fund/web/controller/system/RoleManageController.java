package com.upjf.fund.web.controller.system;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.upjf.fund.dto.RoleInfo;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.aspect.annotation.Log;

/**
 * 角色管理
 * @author guantong
 *
 */
@Controller
public class RoleManageController extends SystemManageController {
	
	/**角色授权*/
	private static final String ROLE_AUTHORITY = "system/roleAuthority";

	@RequestMapping("/roleManage")
	public String toRoleList(){
		
		return ROLE_MANAGE_PAGE;
	}
	
	
	@RequestMapping(value="/queryRoleList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryRoleList(RoleInfo roleInfo,ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		
		List<Map<String, Object>> rows = this.roleInfoServicve.getRolesByCondition(roleInfo, getOffset(), getPageRows());
		
		Integer records = this.roleInfoServicve.countRolesByCondition(roleInfo);
		
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增权限信息
	 * @param userInfo
	 * @param request
	 * @param response
	 */
	@Log(module="系统管理-角色管理",content="新增角色")
	@RequestMapping("/addRole")
	public @ResponseBody ModelMap addRole(RoleInfo roleInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(roleInfo==null){
			mm.put("errMsg", "角色信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(roleInfo.getName())
				||StringUtils.isEmpty(roleInfo.getCode())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		roleInfo.setCreateId(getUserInfo().getPid());
		roleInfo.setCreateDate(new Date());
		
		RoleInfo existRole = this.roleInfoServicve.getRoleInfoByRoleName(roleInfo.getName());
		if(existRole!=null){
			mm.put("errMsg", "角色名称已存在");
			return mm;
		}
		existRole = this.roleInfoServicve.getRoleInfoByRoleCode(roleInfo.getCode());
		if(existRole!=null){
			mm.put("errMsg", "角色编码已存在");
			return mm;
		}
		roleInfo.setPid(UuidGenerator.getUuidGenerator());
		roleInfo.setStatus("1");
		int result = this.roleInfoServicve.addRole(roleInfo);
		if(result>0){
			mm.put("successMsg", "新增角色成功");
			return mm;
		}else{
			mm.put("errMsg", "新增角色失败");
			return mm;
		}
	}
	
	/**
	 * 修改权限
	 * @param authority
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-角色管理",content="修改角色")
	@RequestMapping("/modifyRole")
	public @ResponseBody ModelMap modifyRole(RoleInfo roleInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(roleInfo==null){
			mm.put("errMsg", "角色信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(roleInfo.getName())
				||StringUtils.isEmpty(roleInfo.getCode())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		RoleInfo existRole = null;
		String oriName = request.getParameter("oriName");
		if(!oriName.equals(roleInfo.getName())){
			existRole = this.roleInfoServicve.getRoleInfoByRoleName(roleInfo.getName());
			if(existRole!=null){
				mm.put("errMsg", "角色名称已存在");
				return mm;
			}
		}
		String oriCode = request.getParameter("oriCode");
		if(!oriCode.equals(roleInfo.getCode())){
			existRole = this.roleInfoServicve.getRoleInfoByRoleCode(roleInfo.getCode());
			if(existRole!=null){
				mm.put("errMsg", "角色编码已存在");
				return mm;
			}
		}
		roleInfo.setUpdateId(getUserInfo().getPid());
		roleInfo.setUpdateDate(new Date());
		int result = this.roleInfoServicve.modifyRole(roleInfo);
		if(result>0){
			mm.put("successMsg", "修改成功");
			return mm;
		}else{
			mm.put("errMsg", "修改失败");
			return mm;
		}
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-角色管理",content="删除角色")
	@RequestMapping("/delRole")
	public @ResponseBody ModelMap delRole(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String pid = request.getParameter("pid");
		if(StringUtils.isEmpty(pid)){
			mm.put("errMsg", "角色信息为空");
			return mm;
		}
		//判断该角色下是否还有用户
		int count = this.roleInfoServicve.coutRoleUser(pid);
		if(count>0){
			mm.put("errMsg", "该角色下存在用户，不允许删除该角色");
			return mm;
		}
		int result = this.roleInfoServicve.delRole(pid);
		addLogInfo(request, "系统管理-角色管理", "删除角色");
		if(result>0){
			mm.put("successMsg", "删除成功");
			return mm;
		}else{
			mm.put("errMsg", "删除失败");
			return mm;
		}
	}
	
	/**
	 * 角色授权
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/roleAuthority")
	public ModelAndView roleAuthority(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView  mv = new ModelAndView();
		
		String pid = request.getParameter("pid");
		String roleName = request.getParameter("name");
		String code = request.getParameter("code");
		try {
			if(StringUtils.isNotEmpty(roleName)){
				roleName = URLDecoder.decode(roleName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*根据pid 获取当前角色拥有的权限信息*/
		
		List<String> roleAuthorities = this.roleInfoServicve.getAuthoritiesByRoleId(pid);
		
		mv.addObject("roleName", roleName);
		mv.addObject("roleId", pid);
		mv.addObject("code", code);
		mv.addObject("authorities", roleAuthorities);
		mv.setViewName(ROLE_AUTHORITY);
		return mv;
	}
	
	@RequestMapping(value="/getAuthorities",produces = "application/json;charset=utf-8")
	public @ResponseBody String getAuthorities(HttpServletRequest request,HttpServletResponse response){
		List<Map<String,Object>> allAuthorities = this.authorityService.getAllAuthorities();
		allAuthorities = formatAuthorities(allAuthorities);
		String authorities = JSON.toJSONString(allAuthorities);
		/*替换掉fastJson循环引用出现的$ref子节点*/
		String regex = ",*\\{\"\\$ref\":\"\\$\\[\\d+\\](\\.children\\[\\d+\\])+\"\\}";
		authorities = authorities.replaceAll(regex, "");
		System.out.println(authorities);
		return authorities;
	}
	
	/**
	 * 角色授权
	 * @param authority
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-角色管理",content="角色授权")
	@RequestMapping("/addRoleAuthorities")
	public @ResponseBody ModelMap addRoleAuthorities(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String roleId = request.getParameter("roleId");
		if(StringUtils.isEmpty(roleId)){
			mm.put("errMsg", "未找到角色信息");
			return mm;
		}
		String authorityPids = request.getParameter("authorityPids");
		if(StringUtils.isNotEmpty(authorityPids)&&authorityPids.split(",").length>0){
			String [] authorities = authorityPids.split(",");
			int result = this.roleInfoServicve.addAuthorities(roleId,authorities);
			addLogInfo(request, "系统管理-角色管理", "修改角色授权");
			if(result>0){
				mm.put("successMsg", "授权成功");
				return mm;
			}else{
				mm.put("errMsg", "授权失败");
				return mm;
			}
		}else{
			mm.put("errMsg", "请勾选权限信息");
			return mm;
		}
	}
	
	/**
	 * 格式化权限信息
	 * @param authorities
	 */
	public List<Map<String,Object>> formatAuthorities(List<Map<String,Object>> authorities){
		List<Map<String,Object>> rootAuthorities = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> authority:authorities){
			if("0".equals((String)authority.get("parent_id"))){
				rootAuthorities.add(authority);
			}
		}
		for(Map<String,Object> root:rootAuthorities){
			formatAuthorities(authorities,root);
		}
		return rootAuthorities;
	}


	private void formatAuthorities(List<Map<String, Object>> authorities, Map<String, Object> root) {
			//找到当前节点下的所有子节点
			String pid = (String) root.get("pid");
			List<Map<String,Object>> childs = new ArrayList<Map<String,Object>>();
			for(int j = authorities.size()-1; j>=0;j--){
				String parent_id = (String) authorities.get(j).get("parent_id");
				if(pid.equals(parent_id)){
					Map<String,Object> sub_authority = authorities.get(j);
					childs.add(sub_authority);
					formatAuthorities(authorities, sub_authority);
				}
			}
			if(childs.size()>0){
				root.put("children", childs);
				authorities.removeAll(childs);
			}
		
	}
	
	@RequestMapping("/getRoleNames")
	public @ResponseBody ModelMap getRoleNames(HttpServletRequest request,HttpServletResponse response){
		Integer maxRows= Integer.valueOf( request.getParameter("maxRows"));
		String roleName=request.getParameter("name_startsWith");
		List<String> data=this.roleInfoServicve.getRoleNamesByRoleName(roleName,maxRows);
		ModelMap mm = new ModelMap();
		mm.put("roleNames", data);
		return mm;
	}
	
	
}
