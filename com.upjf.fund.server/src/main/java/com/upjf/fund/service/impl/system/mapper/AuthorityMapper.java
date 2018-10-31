package com.upjf.fund.service.impl.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.Authority;

public interface AuthorityMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    /**
     * 根据条件获取授权信息
     * @param authority
     * @param offset
     * @param pageRows
     * @return
     */
	List<Map<String, Object>> getAuthoriyByCondition(@Param("authority")Authority authority,@Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件统计授权信息
	 * @param authority
	 * @return
	 */
	Integer countAuthoriyByCondition(@Param("authority")Authority authority);

	/**
	 * 根据pid 统计子权限数量
	 * @param pid
	 * @return
	 */
	int countSubAuthority(@Param("pid")String pid);

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
	 * 根据权限名称获取权限信息
	 * @param name
	 * @param maxRows
	 * @return
	 */
	List<Map<String, Object>> getAuthoritiesByName(@Param("name")String name, @Param("maxRows")Integer maxRows);
	
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

	List<Map<String, Object>> getSimpleResourceByName(@Param("name")String name, @Param("maxRows")Integer maxRows);
	
	Authority getAuthorityByAuthName(@Param("name")String name);

	Authority getAuthorityByAuthId(@Param("authorityCode")String authorityCode);

	Authority getParentAuthorityByNameAndId(@Param("parentName")String parentName, @Param("parentId")String parentId);
}