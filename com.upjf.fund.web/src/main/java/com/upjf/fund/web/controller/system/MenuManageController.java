package com.upjf.fund.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.ResourceInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.aspect.annotation.Log;

/**
 * 菜单管理
 * @author guantong
 *
 */
@Controller
public class MenuManageController extends SystemManageController{
	
	public static final String REDIRECT_MENU_ROOT_PATH="redirect:/systemManage/menuManage";
	
	@RequestMapping("/menuManage")
	public String queryMenuList(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		List<Map<String,Object>> allRootMenus = resourceInfoService.findAllMenus();
		treeMenus(allRootMenus,allRootMenus.size()-1);
		String menus = JSON.toJSONString(allRootMenus);
		modelMap.put("menus", menus);
		return MENU_MANAGE_PAGE;
	}
	
	/**
	 * 新增菜单
	 * @param menuInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-菜单管理",content="新增菜单")
	@RequestMapping("/addMenu")
	public @ResponseBody ModelMap addMenu(ResourceInfo menuInfo,HttpServletRequest request,HttpServletResponse response){
		/*菜单重排序*/
		Integer maxSeriesNo = Integer.valueOf(request.getParameter("maxSeriesNo"));
		if(menuInfo.getSeriesNo()<=maxSeriesNo){
			/*更新当前seriesNo 之后的所有菜单将seriesNo+1*/
			this.resourceInfoService.reSortRecourceInfo(menuInfo.getParentId(),menuInfo.getSeriesNo(),EstateFundConstants.MATH_PLUS);
		}
		menuInfo.setPid(UuidGenerator.getUuidGenerator());
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo)subject.getPrincipal();
		menuInfo.setCreateId(userInfo.getUserName());
		menuInfo.setStatus("1");
		menuInfo.setCreateId(getUserInfo().getPid());
		resourceInfoService.addMenu(menuInfo);
		ModelMap mm = new ModelMap();
		mm.put("successMsg", "新增菜单成功");
		return mm;
	}
	
	
	@RequestMapping("/modifyMenu")
	@Log(module="系统管理-菜单管理",content="修改菜单")
	public @ResponseBody ModelMap modifyMenu(ResourceInfo menuInfo,HttpServletRequest request,HttpServletResponse response){
		/*需要交换顺序的*/
		Integer oriSeriesNo = Integer.valueOf(request.getParameter("oriSeriesNo"));
		if(menuInfo.getSeriesNo()!=oriSeriesNo){
			resourceInfoService.updateMenuByParentAndSerierNo(menuInfo.getSeriesNo(),menuInfo.getParentId(),oriSeriesNo);
		}
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo)subject.getPrincipal();
		menuInfo.setCreateId(userInfo.getUserName());
		menuInfo.setStatus("1");
		resourceInfoService.updateMenu(menuInfo);
		ModelMap mm = new ModelMap();
		mm.put("successMsg", "修改菜单成功");
		return mm;
	}
	
	/**
	 * 删除菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-菜单管理",content="删除菜单")
	@RequestMapping("/delMenu")
	public @ResponseBody List<String> delMenu(HttpServletRequest request,HttpServletResponse response){
		String pid = request.getParameter("pid");
		String parentId = request.getParameter("parentId");
		Integer seriesNo = Integer.valueOf(request.getParameter("seriesNo"));
		Integer maxSeriesNo = Integer.valueOf(request.getParameter("maxSeriesNo"));
		this.resourceInfoService.delMenuByPid(pid);
		if(seriesNo<maxSeriesNo){
			/*重排序*/
			this.resourceInfoService.reSortRecourceInfo(parentId,seriesNo,EstateFundConstants.MATH_MINUS);
		}
		String msg = "success";
		List<String> msgList = new ArrayList<String>();
		msgList.add(msg);
		return msgList;
	}
}
