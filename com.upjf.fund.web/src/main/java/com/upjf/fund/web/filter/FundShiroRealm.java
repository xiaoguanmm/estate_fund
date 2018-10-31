package com.upjf.fund.web.filter;


import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.upjf.fund.constants.I18nConstants;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.exception.UserException;
import com.upjf.fund.service.system.AuthorityService;
import com.upjf.fund.service.system.UserInfoService;
public class FundShiroRealm extends AuthorizingRealm {
	
	
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private AuthorityService authorityService;
	@Override
	public boolean supports(AuthenticationToken token) {
		return super.supports(token);
	}
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		
		UserInfo userInfo = (UserInfo)principal.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		try {
			if (userInfo != null) {
				//获取用户所有角色以及角色下的操作权限信息
				List<Map<String, Object>> authorityList = authorityService.getAuthoritiesByUserId(userInfo.getPid());

				if (authorityList != null && authorityList.size() > 0) {
					HashSet<String> roleIds= new HashSet<String>();
					HashSet<String> authorityIds= new HashSet<String>();
					for (Map<String, Object> authorityMap : authorityList) {
						String roleId = (String) authorityMap.get("role_id");
						String authorityId = (String) authorityMap.get("authority_id");
						if(!roleIds.contains(roleId) && !StringUtils.isBlank(roleId)){
							roleIds.add(roleId);
						}
						if(!authorityIds.contains(authorityId) && !StringUtils.isBlank(authorityId)){
							authorityIds.add(authorityId);
						}
					}
                    info.addRoles(roleIds);
					info.addStringPermissions(authorityIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenToken) throws AuthenticationException {
		UsernamePasswordToken token = ((UsernamePasswordToken)authenToken);
		String userName = token.getUsername();
		UserInfo userInfo = this.userInfoService.getUserInfoByUserName(userName);
		if(userInfo==null){
			throw new UserException(I18nConstants.USER_NOT_EXIST);
		}
		return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), getName());  

	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}
