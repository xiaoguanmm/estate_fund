package com.upjf.fund.web.controller.system;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upjf.fund.constants.TagConstants;
import com.upjf.fund.service.system.AuthorityService;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.service.system.ResourceInfoService;
import com.upjf.fund.service.system.RoleInfoService;
import com.upjf.fund.service.system.UserInfoService;
import com.upjf.fund.web.controller.base.BaseController;

/**
 * 系统管理
 * 该controller作为公共的systemManageController
 * 不做具体业务请求
 * @author guantong
 *
 */
@RequestMapping("/systemManage")
public class SystemManageController extends BaseController
{
	@Autowired
	protected UserInfoService userInfoService;
	
	@Autowired
	protected ResourceInfoService resourceInfoService;
	
	@Autowired
	protected RoleInfoService roleInfoServicve;
	
	@Autowired
	protected AuthorityService authorityService;
	
	@Autowired
	protected DictInfoService dictInfoService;
	
	/**登录页面*/
	protected static final String LOGIN = "home/login";
	/**登录成功页面*/
	protected static final String SUCCESS = "home/index";
	/**菜单管理页面*/
	protected static final String MENU_MANAGE_PAGE = "system/menuList";
	/**用户管理页面*/
	protected static final String USER_MANAGE_PAGE = "system/userInfoList";
	/**权限管理页面*/
	protected static final String AUTHORITY_MANAGE_PAGE = "system/authorityList";
	/**角色管理页面*/
	protected static final String ROLE_MANAGE_PAGE = "system/roleList";
	/**字典管理页面*/
	protected static final String DICT_MANAGE_PAGE = "system/dictList";
	
	/**
	 * 将菜单按树形列表展示(JSON)
	 * @param menus
	 * @param size
	 */
	protected void treeMenus(List<Map<String, Object>> menus, int size) {
		if (size <= 0) {
			return;
		}
		List<Map<String, Object>> childMenus = new ArrayList<Map<String, Object>>();
		Map<String, Object> menu = menus.get(size);
		for (int j = 0; j <= size; j++) {
			// 如果两者的parent_id 相同，说明是同一层菜单，排除根目录
			if (!menus.get(j).get("parent_id").equals("0")) {
				if (menus.get(j).get("parent_id").equals(menu.get("parent_id"))) {
					childMenus.add(menus.get(j));
				}
			}
		}
		if (childMenus.size() > 0) {
			// 移除menus中包含的childMenus
			menus.removeAll(childMenus);
			String parentId = (String) childMenus.get(0).get("parent_id");
			// 找到pid = parent_id 的元素
			for (int k = 0; k < menus.size(); k++) {
				if (menus.get(k).get("pid").equals(parentId)) {
					menus.get(k).put("childs", childMenus);
					break;
				}
			}
			size = menus.size() - 1;
		} else {
			size--;
		}
		treeMenus(menus, size);
	}
	
	/**
	 * 通过反射将TagContants放到Map中
	 * @return
	 */
	protected static Map<String,Object> globals(){
		Map<String,Object> globals = new HashMap<String,Object>();
		Class<?> clazz = TagConstants.class;
		Field[] fs = clazz.getDeclaredFields();
		for(Field f:fs){
			Object value=null;
			try {
				value = f.get(clazz);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			globals.put(f.getName(), value);
		}
		
		
		return globals;
		
		
	}
	
	
}
