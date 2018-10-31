/** Project Name:com.upjf.risk.server File Name:UserInfoServiceImpl.java Package Name:com.upjf.risk.userinfo.service.impl Date:2018年3月26日上午11:24:21 Copyright (c)
 * 2018, chenzhou1025@126.com All Rights Reserved. */

package com.upjf.fund.service.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.impl.system.mapper.UserInfoMapper;
import com.upjf.fund.service.system.UserInfoService;


/** ClassName:UserInfoServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年3月26日 上午11:24:21 <br/>
 * 
 * @author huxinlong
 * @version */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserInfoMapper userInfoMapper;

	

	public UserInfo getUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public UserInfo getUserInfoByUserName(String userName) {
		
		return this.userInfoMapper.getUserInfoByUserName(userName);
	}



	@Override
	@Transactional
	public int updatePasswordByPid(String pid, String password) {
		
		return this.userInfoMapper.updatePasswordByPid(pid,password);
	}



	@Override
	public List<Map<String, Object>> getUserInfoByCondition(UserInfo userInfo,Integer offset, Integer pageRows) {
		List<Map<String, Object>> result = this.userInfoMapper.getUserInfoByCondition(userInfo,offset,pageRows);
		return result;
	}



	@Override
	public Integer countUserInfoByCondition(UserInfo userInfo) {
		
		return this.userInfoMapper.countUserInfoByCondition(userInfo);
	}



	@Override
	@Transactional
	public int addUserInfo(UserInfo userInfo) {
		
		return this.userInfoMapper.addUserInfo(userInfo);
	}



	@Override
	@Transactional
	public int modifyUserInfo(UserInfo userInfo) {
		
		return this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	@Transactional
	public int switchStatus(String pid, String status) {
		
		return this.userInfoMapper.switchStatus(pid,status);
	}



	@Override
	@Transactional
	public int delUserInfo(String pid) {
		this.userInfoMapper.deleteUserRoleByPid(pid);
		return this.userInfoMapper.deleteByPrimaryKey(pid);
	}



	@Override
	public UserInfo getUserInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return this.userInfoMapper.selectByPrimaryKey(pid);
	}



	@Override
	public int updateNewUserPasswordByPid(String pid, String password, String no) {
		
		return this.userInfoMapper.updateNewUserPasswordByPid(pid, password, no);
	}



}
