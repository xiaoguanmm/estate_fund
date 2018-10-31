package com.upjf.fund.service.system;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.EstateFundFile;

/**
 * 文件服务
 * @author Administrator
 *
 */
public interface EstateFundFileService {

	/**
	 * 批量插入
	 * @param files
	 * @return
	 */
	public int batchInsertFiles(List<EstateFundFile> files);

	/**
	 * 删除
	 * @param pid
	 * @return
	 */
	public int delete(String pid);

	/**
	 * 批量插入付款附件表
	 * @param files
	 * @param paymentAccessoryReceive
	 * @param paymentId
	 * @return
	 */
	public int batchInsertPaymentAccessory(List<EstateFundFile> files,	String paymentAccessoryReceive, String paymentId);

	/**
	 * 根据paymentId获取付款信息附件信息
	 * @param pid
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	public List<Map<String, Object>> getPaymentAccessoriesByPaymentId(String paymentId, Integer offset, Integer pageRows);

	/**
	 * 根据paymentId统计付款信息附件信息
	 * @param pid
	 * @return
	 */
	public Integer countPaymentAccessoriesByPaymentId(String paymentId);

	/**
	 * 更新文件状态
	 * @param pid
	 * @param status
	 * @return
	 */
	public int updateFileStatus(String pid, String status);
	
	/**
	 * 根据paybackId获取回款信息附件信息
	 * @param pid
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	public List<Map<String, Object>> getPaybackAccessoriesByPaybackId(String paybackId, Integer offset, Integer pageRows);
	
	/**
	 * 根据paybackId统计回款信息附件信息
	 * @param paybackId
	 * @return
	 */
	public Integer countPaybackAccessoriesByPaybackId(String paybackId);

	/**
	 * 批量插入
	 * @param files
	 * @param accessoryType
	 * @param paymentId
	 * @return
	 */
	public int batchInsertPaybackAccessory(List<EstateFundFile> files,String accessoryType, String paybackId);
}
