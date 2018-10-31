
package com.upjf.fund.service.system;
import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.Authority;
import com.upjf.fund.dto.RoleInfo;
import com.upjf.fund.dto.UserInfo;


/** ClassName:RoleInfoServicve <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年3月26日 上午11:15:19 <br/>
 * 
 * @author huxinlong
 * @version */
public interface RoleInfoService
{

	/**
	 * 获取所有角色信息
	 * @return
	 */
	List<Map<String, Object>> getAllSimpleRoleInfos();

	/**
	 * 根据pid获取用户角色信息
	 * @param pid
	 * @return
	 */
	List<Map<String, Object>> getUserRoleInfosByPid(String pid);

	/**
	 * 数据授权
	 * @param userPid 
	 * @param userRole
	 * @return
	 */
	int batchInsertUserRole(String[] pids, UserInfo userInfo, String userPid);
	
	/**
	 * 根据条件获取角色信息(分页)
	 * @return
	 */
	List<Map<String,Object>> getRolesByCondition(RoleInfo roleInfo,Integer offset, Integer pageRows);
	/**
	 * 根据条件统计角色信息
	 * @return
	 */
	int countRolesByCondition(RoleInfo roleInfo);

	/**
	 * 新增角色
	 * @param roleInfo
	 * @return
	 */
	int addRole(RoleInfo roleInfo);

	/**
	 * 修改角色
	 * @param roleInfo
	 * @return
	 */
	int modifyRole(RoleInfo roleInfo);

	/**
	 * 删除角色
	 * @param pid
	 * @return
	 */
	int delRole(String pid);

	/**
	 * 角色授权
	 * @param roleId
	 * @param authorities
	 * @return
	 */
	int addAuthorities(String roleId, String[] authorities);

	/**
	 * 获取当前角色所拥有的权限信息
	 * @param pid
	 * @return
	 */
	List<String> getAuthoritiesByRoleId(String roleId);

	/**
	 * 根据角色名称获取角色信息
	 * @param roleName
	 * @param maxRows
	 * @return
	 */
	List<String> getRoleNamesByRoleName(String roleName, Integer maxRows);


	RoleInfo getRoleInfoByRoleName(String name);

	RoleInfo getRoleInfoByRoleCode(String code);

	int coutRoleUser(String pid);

}
