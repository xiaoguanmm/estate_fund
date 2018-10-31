package com.upjf.fund.service.system;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.UserInfo;


/**
 * ClassName:UserInfoService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年3月26日 上午11:16:00 <br/>
 * 
 * @author huxinlong
 * @version */
public interface UserInfoService
{
	
	public UserInfo getUserInfo(UserInfo userInfo);

	/**
	 * 根据用户名查找用户信息
	 * @param userName
	 * @return
	 */
	public UserInfo getUserInfoByUserName(String userName);

	/**
	 * 根据pid更新用户密码
	 * @param pid
	 * @param password
	 * @return
	 */
	public int updatePasswordByPid(String pid, String password);

	/**
	 * 根据查询条件获取用户列表,分页显示
	 * @param userInfo
	 * @param endRows 
	 * @param startRows 
	 * @return
	 */
	public List<Map<String, Object>> getUserInfoByCondition(UserInfo userInfo, Integer offset, Integer pageRows);

	/**
	 * 根据条件统计用户总条数
	 * @param userInfo
	 * @return
	 */
	public Integer countUserInfoByCondition(UserInfo userInfo);

	/**
	 * 新增用户信息
	 * @param userInfo
	 * @return
	 */
	public int addUserInfo(UserInfo userInfo);

	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	public int modifyUserInfo(UserInfo userInfo);

	/**
	 * 切换状态
	 * @param pid
	 * @param status
	 * @return
	 */
	public int switchStatus(String pid, String status);

	/**
	 * 删除用户信息
	 * @param pid
	 * @return
	 */
	public int delUserInfo(String pid);

	public UserInfo getUserInfoByPid(String pid);

	public int updateNewUserPasswordByPid(String pid, String password, String no);
}
