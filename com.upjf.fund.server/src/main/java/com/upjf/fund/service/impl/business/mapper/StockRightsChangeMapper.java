package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.StockRightsChange;

public interface StockRightsChangeMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(StockRightsChange record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(StockRightsChange record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    StockRightsChange selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(StockRightsChange record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(StockRightsChange record);

    /**
     * 通过股东表ID查询 股权变更历史表
     * @param stockholderId
     * @return
     */
	List<StockRightsChange> queryStockRightsChangeByStockholderId(@Param("stockholderId") String stockholderId);

	/**
	 * 查询股权变更历史记录index页列表
	 * @param stockholderPid 
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> queryStockChangeHisList(@Param("stockholderId") String stockholderId, @Param("offset")Integer offset,@Param("pageRows")Integer pageRows);

	/**
	 * 查询股权变更历史记录index页列表总条数
	 * @author zhangcai
	 * @param stockholderId 
	 * @date 2018.09.28
	 * @return
	 */
	Integer countStockChangeHisList(@Param("stockholderId") String stockholderId);
}