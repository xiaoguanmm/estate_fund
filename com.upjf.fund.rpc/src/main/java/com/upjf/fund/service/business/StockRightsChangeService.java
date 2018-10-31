package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.StockholderInfo;
import com.upjf.fund.dto.UserInfo;



public interface StockRightsChangeService {

	/**
	 * 插入股权变更历史表
	 * @author zhangcai
	 * @param userInfo 
	 * @param corPid
	 * @date 2018.09.28
	 * @return
	 */
	Map<String,Object> insertStockRightsChange(StockholderInfo stockholderInfo, UserInfo userInfo);

	/**
	 * 查询股权变更历史记录index页列表
	 * @author zhangcai
	 * @param stockholderPid 
	 * @param offset
	 * @param pageRows
	 * @date 2018.09.28
	 * @return
	 */
	List<Map<String, Object>> queryStockChangeHisList(String stockholderPid, Integer offset,Integer pageRows);

	/**
	 * 查询股权变更历史记录index页列表总条数
	 * @author zhangcai
	 * @param stockholderPid 
	 * @date 2018.09.28
	 * @return
	 */
	Integer countStockChangeHisList(String stockholderPid);

	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---股权变更---上传股权变更附件
	 * 批量插入股权变更附件表
	 * @author zhangcai 
	 * @param files
	 * @param stockRightsChangePid
	 * @param loginUserPid
	 * @return
	 */
	Integer batchInsertStockRightsChangeAccessory(List<EstateFundFile> files,String stockRightsChangePid, String loginUserPid);

	/**
	 * 通过stockRightsChangeId查询股权变更附件表
	 * @author zhangcai 
	 * @param stockRightsChangeId
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getStockChangeAccessoryByStockRightsChangeId(String stockRightsChangeId, Integer offset, Integer pageRows);

	/**
	 * 通过stockRightsChangeId查询股权变更附件表条数
	 * @author zhangcai 
	 * @param stockRightsChangePid
	 * @return
	 */
	Integer countStockChangeAccessoryByStockRightsChangeId(String stockRightsChangePid);




}
