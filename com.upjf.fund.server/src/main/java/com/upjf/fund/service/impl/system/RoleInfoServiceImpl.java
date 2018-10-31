/** Project Name:com.upjf.risk.server File Name:RoleInfoServiceImpl.java Package Name:com.upjf.risk.roleinfo.service.impl Date:2018年3月26日上午11:33:01 Copyright (c)
 * 2018, chenzhou1025@126.com All Rights Reserved. */

package com.upjf.fund.service.impl.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.RoleInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.dto.UserRole;
import com.upjf.fund.service.impl.system.mapper.RoleInfoMapper;
import com.upjf.fund.service.impl.system.mapper.RoleResourceOperatorMapper;
import com.upjf.fund.service.impl.system.mapper.UserRoleMapper;
import com.upjf.fund.service.system.RoleInfoService;


/** ClassName:RoleInfoServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年3月26日 上午11:33:01 <br/>
 * 
 * @author huxinlong
 * @version */
@Service("roleInfoService")
public class RoleInfoServiceImpl implements RoleInfoService{
	
	@Autowired
	private RoleInfoMapper roleInfoMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleResourceOperatorMapper roleResourceOperatorMapper;

	@Override
	public List<Map<String, Object>> getAllSimpleRoleInfos() {
		
		return this.roleInfoMapper.getAllSimpleRoleInfos();
	}

	@Override
	public List<Map<String, Object>> getUserRoleInfosByPid(String pid) {
		
		return this.roleInfoMapper.getUserRoleInfosByPid(pid);
	}

	@Override
	@Transactional
	public int batchInsertUserRole(String[] pids, UserInfo userInfo,String userPid) {
		UserRole userRole = new UserRole();
		int result =0;
		/*先删后插*/
		result = this.userRoleMapper.deleteUserRoleByUserId(userPid);
		for(String pid:pids){
			userRole.setUserId(userPid);
			userRole.setRoleId(pid);
			userRole.setCreateDate(new Date());
			userRole.setCreateId(userInfo.getPid());
			result = this.userRoleMapper.insert(userRole);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getRolesByCondition(RoleInfo roleInfo,	Integer offset, Integer pageRows) {
		
		return this.roleInfoMapper.getRolesByCondition(roleInfo, offset, pageRows);
	}

	@Override
	public int countRolesByCondition(RoleInfo roleInfo) {
		
		return this.roleInfoMapper.countRolesByCondition(roleInfo);
	}

	@Override
	@Transactional
	public int addRole(RoleInfo roleInfo) {
		
		return this.roleInfoMapper.insertSelective(roleInfo);
	}

	@Override
	@Transactional
	public int modifyRole(RoleInfo roleInfo) {
		
		return this.roleInfoMapper.updateByPrimaryKeySelective(roleInfo);
	}

	@Override
	@Transactional
	public int delRole(String pid) {
		
		return this.roleInfoMapper.deleteByPrimaryKey(pid);
	}

	@Override
	@Transactional
	public int addAuthorities(String roleId, String[] authorities) {
		//首先 删除原有权限信息
		int result = this.roleResourceOperatorMapper.deleteByRoleId(roleId);
		result = this.roleResourceOperatorMapper.batchInsertAuthorities(roleId,authorities);
		return result;
	}

	@Override
	public List<String> getAuthoritiesByRoleId(String roleId) {
		
		return this.roleResourceOperatorMapper.getAuthoritiesByRoleId(roleId);
	}

	@Override
	public List<String> getRoleNamesByRoleName(String roleName, Integer maxRows) {
		return this.roleInfoMapper.getRoleNamesByRoleName(roleName,maxRows);
	}

	@Override
	public RoleInfo getRoleInfoByRoleName(String name) {
		
		return this.roleInfoMapper.getRoleInfoByRoleName(name);
	}

	@Override
	public RoleInfo getRoleInfoByRoleCode(String code) {
		
		return this.roleInfoMapper.getRoleInfoByRoleCode(code);
	}

	@Override
	public int coutRoleUser(String pid) {
		// TODO Auto-generated method stub
		return this.roleInfoMapper.coutRoleUser(pid);
	}
	
}
