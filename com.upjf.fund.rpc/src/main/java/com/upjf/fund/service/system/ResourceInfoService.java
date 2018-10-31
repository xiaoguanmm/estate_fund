
package com.upjf.fund.service.system;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.ResourceInfo;

/** 
 * ClassName:ResourceInfoService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年3月26日 上午11:13:38 <br/>
 * 
 * @author huxinlong
 * @version */
public interface ResourceInfoService
{
	/**
	 * 根据用户名获取该用户下所有能操作菜单
	 * @param userName
	 * @return
	 */
	List<Map<String, Object>> findMenusByUserPid(String pid);

	/**
	 * 查询所有目录
	 * @return
	 */
	public List<Map<String,Object>> findAllMenus();
	
	/**
	 * 新增目录
	 * @param menuInfo
	 */
	public int addMenu(ResourceInfo menuInfo);

	/**
	 * 获取pid为最大的目录
	 * @return
	 */
	public String getMaxPidMenu();

	/**
	 * 重新排序
	 * @param parentId
	 * @param param 
	 * @param maxSeriesNo
	 */
	public void reSortRecourceInfo(String parentId, Integer seriesNo, String mathParam);
	
	/**
	 * 根据pid删除菜单
	 * @param pid
	 * @return
	 */
	public int delMenuByPid(String pid);

	/**
	 * 更新菜单
	 * @param menuInfo
	 */
	public void updateMenu(ResourceInfo menuInfo);

	/**
	 * 更新菜单序号
	 * @param seriesNo 需要更新的顺序
	 * @param parentId 父id
	 * @param oriSeriesNo 原顺序
	 */
	public void updateMenuByParentAndSerierNo(Integer seriesNo,	String parentId, Integer oriSeriesNo);
	
}
