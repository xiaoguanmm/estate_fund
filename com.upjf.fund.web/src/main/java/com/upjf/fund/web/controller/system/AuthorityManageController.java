package com.upjf.fund.web.controller.system;

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

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.Authority;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.aspect.annotation.Log;

@Controller
public class AuthorityManageController extends SystemManageController {

	@RequestMapping("/authorityManage")
	public String toAuthorityList(){
		return AUTHORITY_MANAGE_PAGE;
	}
	
	@RequestMapping(value="/queryAuthorityList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryAuthorityList(Authority authority,ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		
		List<Map<String, Object>> rows = this.authorityService.getAuthoriyByCondition(authority, getOffset(), getPageRows());
		
		Integer records = this.authorityService.countAuthoriyByCondition(authority);
		
		return putJsonData(rows, records);
	}
	
	
	/**
	 * 新增权限信息
	 * @param userInfo
	 * @param request
	 * @param response
	 */
	@Log(module="系统管理-权限管理",content="新增权限")
	@RequestMapping("/addAuthority")
	public @ResponseBody ModelMap addAuthority(Authority authority, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(authority==null){
			mm.put("errMsg", "权限信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(String.valueOf(authority.getType()))
				||StringUtils.isEmpty(authority.getName())
				||StringUtils.isEmpty(authority.getAuthorityCode())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}

		Authority existAuth = this.authorityService.getAuthorityByAuthName(authority.getName());
		if(existAuth!=null){
			mm.put("errMsg", "权限名称已存在");
			return mm;
		}
		existAuth = this.authorityService.getAuthorityByAuthId(authority.getAuthorityCode());
		if(existAuth!=null){
			mm.put("errMsg", "权限编码已存在");
			return mm;
		}
		if(StringUtils.isNotEmpty(authority.getParentName())){
			Authority pAuthority = this.authorityService.getParentAuthorityByNameAndId(authority.getParentName(),authority.getParentId());
			if(pAuthority==null){
				mm.put("errMsg", "上级权限不存在，请重新选择填写！");
				return mm;
			}
		}
		authority.setCreateId(getUserInfo().getPid());
		authority.setCreateDate(new Date());
		authority.setPid(UuidGenerator.getUuidGenerator());
		authority.setStatus("1");
		if(EstateFundConstants.AUTHORITY_TYPE_MENU.equals(authority.getType()) && StringUtils.isEmpty(authority.getParentId())){
			authority.setParentId("0");
		}
		int result = this.authorityService.addAuthority(authority);
		if(result>0){
			mm.put("successMsg", "新增权限成功");
			return mm;
		}else{
			mm.put("errMsg", "新增权限失败");
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
	@Log(module="系统管理-权限管理",content="修改权限")
	@RequestMapping("/modifyAuthority")
	public @ResponseBody ModelMap modifyAuthority(Authority authority, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(authority==null){
			mm.put("errMsg", "权限信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(String.valueOf(authority.getType()))
				||StringUtils.isEmpty(authority.getName())
				||StringUtils.isEmpty(authority.getAuthorityCode())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		
		Authority existAuth = null;
		String oriName = request.getParameter("oriName");
		if(!oriName.equals(authority.getName())){
			existAuth = this.authorityService.getAuthorityByAuthName(authority.getName());
			if(existAuth!=null){
				mm.put("errMsg", "权限名称已存在");
				return mm;
			}
		}
		String oriAuthId = request.getParameter("oriAuthId");
		if(!oriAuthId.equals(authority.getAuthorityCode())){
			existAuth = this.authorityService.getAuthorityByAuthId(authority.getAuthorityCode());
			if(existAuth!=null){
				mm.put("errMsg", "权限编码已存在");
				return mm;
			}
		}
		if(StringUtils.isNotEmpty(authority.getParentName())){
			Authority pAuthority = this.authorityService.getParentAuthorityByNameAndId(authority.getParentName(),authority.getParentId());
			if(pAuthority==null){
				mm.put("errMsg", "上级权限不存在，请重新选择填写！");
				return mm;
			}
		}
		authority.setUpdateId(getUserInfo().getPid());
		authority.setUpdateDate(new Date());
		if(EstateFundConstants.AUTHORITY_TYPE_MENU.equals(authority.getType()) && StringUtils.isEmpty(authority.getParentId())){
			authority.setParentId("0");
		}
		int result = this.authorityService.modifyAuthority(authority);
		addLogInfo(request, "系统管理-权限管理", "修改权限");
		if(result>0){
			mm.put("successMsg", "修改成功");
			return mm;
		}else{
			mm.put("errMsg", "修改失败");
			return mm;
		}
	}
	
	/**
	 * 删除权限
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-权限管理",content="删除权限")
	@RequestMapping("/delAuthority")
	public @ResponseBody ModelMap delAuthority(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String pid = request.getParameter("pid");
		if(StringUtils.isEmpty(pid)){
			mm.put("errMsg", "权限信息为空");
			return mm;
		}
		/*首先校验该权限下是否存在子权限，若存在不予以删除*/
		List<Authority> subAuthorityList = this.authorityService.getSubAuthorityByParentId(pid);
		if(subAuthorityList != null && subAuthorityList.size()>0){
			mm.put("errMsg", "当前权限下存在子权限，请先删除子权限");
			return mm;
		}
		int result = this.authorityService.delAuthority(pid);
		addLogInfo(request, "系统管理-权限管理", "删除权限");
		if(result>0){
			mm.put("successMsg", "删除成功");
			return mm;
		}else{
			mm.put("errMsg", "删除失败");
			return mm;
		}
	}
	
	@RequestMapping("/getSimpleAuthorities")
	public @ResponseBody ModelMap getSimpleAuthorities(HttpServletRequest request,HttpServletResponse response){
		Integer maxRows= Integer.valueOf( request.getParameter("maxRows"));
		String name=request.getParameter("name_startsWith");
		List<Map<String,Object>> data=this.authorityService.getAuthoritiesByName(name,maxRows);
		ModelMap mm = new ModelMap();
		mm.put("authorities", data);
		return mm;
	}
	
	@RequestMapping("/getSimpleResource")
	public @ResponseBody ModelMap getSimpleResource(HttpServletRequest request,HttpServletResponse response){
		Integer maxRows= Integer.valueOf( request.getParameter("maxRows"));
		String name=request.getParameter("name_startsWith");
		List<Map<String,Object>> data=this.authorityService.getSimpleResourceByName(name,maxRows);
		ModelMap mm = new ModelMap();
		mm.put("resource", data);
		return mm;
	}
	
}
