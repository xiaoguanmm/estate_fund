package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.StockRightsChangeAccessory;

public interface StockRightsChangeAccessoryMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(StockRightsChangeAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(StockRightsChangeAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    StockRightsChangeAccessory selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(StockRightsChangeAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(StockRightsChangeAccessory record);
    
    /**
	 * 项目公司管理---新增项目公司---项目公司股东信息---股权变更---上传股权变更附件
	 * 批量插入股权变更附件表
	 * @author zhangcai 
	 * @param files
	 * @param stockRightsChangePid
	 * @param loginUserPid
	 * @return
	 */
	Integer batchInsertStockRightsChangeAccessory(@Param("accessoryList") List<StockRightsChangeAccessory> accessoryList);

	/**
	 * 通过stockRightsChangeId查询股权变更附件表
	 * @author zhangcai 
	 * @param stockRightsChangeId
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getStockChangeAccessoryByStockRightsChangeId(@Param("stockRightsChangeId")String stockRightsChangeId, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 通过stockRightsChangeId查询股权变更附件表条数
	 * @author zhangcai 
	 * @param stockRightsChangePid
	 * @return
	 */
	Integer countStockChangeAccessoryByStockRightsChangeId(@Param("stockRightsChangeId")String stockRightsChangeId);
}