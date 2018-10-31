package com.upjf.fund.service.system;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.Authority;

/**
 * 权限管理service
 * @author guantong
 *
 */
public interface AuthorityService
{

	/**
	 * 根据条件获取权限信息
	 * @param authority
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getAuthoriyByCondition(Authority authority,Integer offset, Integer pageRows);

	/**
	 * 根据条件统计权限信息
	 * @param authority
	 * @return
	 */
	Integer countAuthoriyByCondition(Authority authority);

	/**
	 * 新增权限信息
	 * @param authority
	 * @return
	 */
	int addAuthority(Authority authority);
	
	/**
	 * 修改权限信息
	 * @param authority
	 * @return
	 */
	public int modifyAuthority(Authority authority);

	/**
	 * 删除权限信息
	 * @param pid
	 * @return
	 */
	int delAuthority(String pid);

	/**
	 * 统计子权限数量
	 * @param pid
	 * @return
	 */
	int countSubAuthority(String pid);

	/**
	 * 根据父权限pid获取子权限信息
	 * @param pid
	 * @return
	 */
	List<Authority> getSubAuthorityByParentId(String pid);

	/**
	 * 获取所有权限信息
	 * @return
	 */
	List<Map<String, Object>> getAllAuthorities();

	/**
	 * 根据权限名获取权限信息
	 * @param name
	 * @param maxRows
	 * @return
	 */
	List<Map<String, Object>> getAuthoritiesByName(String name, Integer maxRows);
	
	/**
	 * 获取用户所有角色以及角色下的操作权限信息
	 * @return
	 */
	List<Map<String, Object>> getAuthoritiesByUserId(String userId);
	
	/**
	 * 获取用户所有角色下的菜单操作权限信息
	 * @return
	 */
	List<Map<String, Object>> getAuthoritiesMenuByUserId(String userId);

	List<Map<String, Object>> getSimpleResourceByName(String name,Integer maxRows);

	Authority getAuthorityByAuthName(String name);

	Authority getAuthorityByAuthId(String authorityId);

	Authority getParentAuthorityByNameAndId(String parentName, String parentId);
	
}
