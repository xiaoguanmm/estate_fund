package com.upjf.fund.service.impl.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.ResourceInfo;

public interface ResourceInfoMapper {
    int deleteByPrimaryKey(String pid);

    int insert(ResourceInfo record);

    int insertSelective(ResourceInfo record);

    ResourceInfo selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(ResourceInfo record);

    int updateByPrimaryKey(ResourceInfo record);

    /**
     * 根据用户名获取该用户下所有能操作菜单
     * @param userName
     * @return
     */
	List<Map<String, Object>> findMenusByUserPid(@Param("pid")String pid);

	 /**
     * 查找所有根目录
     * @return
     */
	List<Map<String, Object>> findAllMenus();

	/**
	 * 获取pid最大值
	 * @return
	 */
	String getMaxPidMenu();

	/**
	 * 重新排序
	 * @param parentId
	 * @param seriesNo
	 * @param mathParam
	 */
	void reSortRecourceInfo(@Param("parentId") String parentId, @Param("seriesNo") Integer seriesNo, @Param("mathParam") String mathParam);

	/**
	 * 更新菜单序号
	 * @param seriesNo 需要更新的顺序
	 * @param parentId 父id
	 * @param oriSeriesNo 原顺序
	 */
	void updateMenuByParentAndSerierNo(@Param("seriesNo") Integer seriesNo, @Param("parentId") String parentId,@Param("oriSeriesNo") Integer oriSeriesNo);
}