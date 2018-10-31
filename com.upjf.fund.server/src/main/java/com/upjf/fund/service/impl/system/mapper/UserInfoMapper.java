package com.upjf.fund.service.impl.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String pid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 根据用户名查找用户信息
     * @param userName
     */
	public UserInfo getUserInfoByUserName(@Param("userName")String userName);

	/**
	 * 根据pid更新用户密码
	 * @param pid
	 * @param password
	 * @return
	 */
	int updatePasswordByPid(@Param("pid")String pid, @Param("password")String password);

	/**
	 * 根据条件查询用户列表
	 * @param userInfo
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getUserInfoByCondition(@Param("userInfo")UserInfo userInfo,	@Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件统计用户总条数
	 * @param userInfo
	 * @return
	 */
	Integer countUserInfoByCondition(UserInfo userInfo);

	/**
	 * 新增用户信息
	 * @param userInfo
	 * @return
	 */
	int addUserInfo(UserInfo userInfo);

	/**
	 * 切换用户状态
	 * @param pid
	 * @param status
	 * @return
	 */
	int switchStatus(@Param("pid")String pid, @Param("status")String status);

	int updateNewUserPasswordByPid(@Param("pid")String pid, @Param("password")String password, @Param("no")String no);

	int deleteUserRoleByPid(@Param("pid")String pid);
}