package com.upjf.fund.service.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.Authority;
import com.upjf.fund.service.impl.system.mapper.AuthorityMapper;
import com.upjf.fund.service.system.AuthorityService;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	private AuthorityMapper authorityMapper;
	
	@Override
	public List<Map<String, Object>> getAuthoriyByCondition(
			Authority authority, Integer offset, Integer pageRows) {
		return this.authorityMapper.getAuthoriyByCondition(authority,offset,pageRows);
	}

	@Override
	public Integer countAuthoriyByCondition(Authority authority) {
		
		return this.authorityMapper.countAuthoriyByCondition(authority);
	}

	@Override
	@Transactional
	public int addAuthority(Authority authority) {
		return this.authorityMapper.insertSelective(authority);
	}

	@Override
	@Transactional
	public int modifyAuthority(Authority authority) {
		
		return this.authorityMapper.updateByPrimaryKeySelective(authority);
	}

	@Override
	@Transactional
	public int delAuthority(String pid) {
		
		return this.authorityMapper.deleteByPrimaryKey(pid);
	}

	@Override
	public int countSubAuthority(String pid) {
		
		return this.authorityMapper.countSubAuthority(pid);
	}

	@Override
	public List<Authority> getSubAuthorityByParentId(String pid) {
		
		return this.authorityMapper.getSubAuthorityByParentId(pid);
	}

	@Override
	public List<Map<String, Object>> getAllAuthorities() {
		
		return this.authorityMapper.getAllAuthorities();
	}

	@Override
	public List<Map<String, Object>> getAuthoritiesByName(String name,	Integer maxRows) {
		// TODO Auto-generated method stub
		return this.authorityMapper.getAuthoritiesByName(name,maxRows);
	}

	/**
	 * 获取用户所有角色以及角色下的操作权限信息
	 * @return
	 */
	public List<Map<String, Object>> getAuthoritiesByUserId(String userId){
		return authorityMapper.getAuthoritiesByUserId(userId);
	}
	
	/**
	 * 获取用户所有角色下的菜单操作权限信息
	 * @return
	 */
	public List<Map<String, Object>> getAuthoritiesMenuByUserId(String userId){
		return authorityMapper.getAuthoritiesMenuByUserId(userId);
	}

	@Override
	public List<Map<String, Object>> getSimpleResourceByName(String name,Integer maxRows) {
		return this.authorityMapper.getSimpleResourceByName(name,maxRows);
	}

	@Override
	public Authority getAuthorityByAuthName(String name) {
		// TODO Auto-generated method stub
		return this.authorityMapper.getAuthorityByAuthName(name);
	}

	@Override
	public Authority getAuthorityByAuthId(String authorityId) {
		// TODO Auto-generated method stub
		return this.authorityMapper.getAuthorityByAuthId(authorityId);
	}

	@Override
	public Authority getParentAuthorityByNameAndId(String parentName,String parentId) {
		// TODO Auto-generated method stub
		return this.authorityMapper.getParentAuthorityByNameAndId(parentName,parentId);
	}

}
