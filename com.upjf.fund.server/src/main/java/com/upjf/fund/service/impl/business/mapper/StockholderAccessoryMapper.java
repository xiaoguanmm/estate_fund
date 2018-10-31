package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.StockholderAccessory;

public interface StockholderAccessoryMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(StockholderAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(StockholderAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    StockholderAccessory selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(StockholderAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(StockholderAccessory record);

    /**
     * 批量插入项目公司合同附件表
     * @param accessoryList
     * @return
     */
	int batchInsertStockholderAccessory(@Param("accessoryList") List<StockholderAccessory> accessoryList);

	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---修改股东公司---查询股东附件
	 * @param stockholderId
	 * @param type 
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getStockholderAccessoryByStockholderId(@Param("stockholderId")String stockholderId, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---修改股东公司---查询股东附件数量
	 * @param stockholderId
	 * @return
	 */
	Integer countStockholderAccessoryByStockholderId(@Param("stockholderId")String stockholderId);

}