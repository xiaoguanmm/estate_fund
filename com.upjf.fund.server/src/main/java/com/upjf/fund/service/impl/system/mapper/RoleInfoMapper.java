package com.upjf.fund.service.impl.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.RoleInfo;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(String pid);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

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
	List<Map<String, Object>> getUserRoleInfosByPid(@Param("pid")String pid);

	/**
	 * 根据条件获取角色信息
	 * @param roleInfo
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getRolesByCondition(@Param("roleInfo")RoleInfo roleInfo, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件统计角色信息
	 * @param roleInfo
	 * @return
	 */
	int countRolesByCondition(RoleInfo roleInfo);

	/**
	 * 根据角色名称获取角色信息
	 * @param roleName
	 * @param maxRows
	 * @return
	 */
	List<String> getRoleNamesByRoleName(@Param("roleName")String roleName, @Param("maxRows")Integer maxRows);

	RoleInfo getRoleInfoByRoleName(@Param("name")String name);

	RoleInfo getRoleInfoByRoleCode(@Param("code")String code);

	int coutRoleUser(@Param("pid")String pid);
}