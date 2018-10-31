package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.Payment;
import com.upjf.fund.dto.StockholderInfo;


/**
 * 股东相关操作服务接口
 * @author lixq
 * @date   2018年10月22日
 */
public interface StockholderInfoService {
	
	
	/**
	 * 分页获取项目股东付款请求列表信息
	 * @author  lixq 
	 * @param   payMent
	 * @param   page
	 * @return  Map<String,Object> 
	 * @date    2018年10月22日
	 */
	Map<String,Object> getStockHolderPayByPage(Payment payMent,Page page) throws Exception;
	
	
	/**
	 * 根据付款主键,分页获取该付款主键下的项目股东付款请求附件信息
	 * @author  lixq 
	 * @param   payMentPid
	 * @param   page
	 * @return  Map<String,Object>
	 * @throws  Exception
	 * @date    2018年10月22日
	 */
	Map<String,Object> getStockHolderVoucherByPage(String payMentPid,Page page) throws Exception;
	
	
	/**
	 * 根据项目主键查询获取项目公司相关信息
	 * @author  lixq 
	 * @param   projectPid
	 * @return	Map<String,Object>
	 * @throws  Exception    
	 * @date    2018年10月22日
	 */
	List<Map<String,Object>> getProjectCompanyByProjectPid(String projectPid) throws Exception;
	
	
	
	/**
	 * 根据项目公司主键及项目主键,查询获取有效状态下的股东信息
	 * @author  lixq 
	 * @param 	projectCompanyPid
	 * @param 	projectId
	 * @return 	List<Map<String,Object>>
	 * @throws 	Exception    
	 * @date 	2018年10月22日
	 */
	List<Map<String,Object>> getStockHolderByProjectComPanyPid(String projectCompanyPid,String projectId) throws Exception;
	
	
	/**
	 * 根据企业主键查询获取该企业银行信息
	 * @author  lixq 
	 * @param   companyPid
	 * @return  List<Map<String,Object>>
	 * @throws  Exception    
	 * @date    2018年10月22日
	 */
	List<Map<String,Object>> getBankInfoByComPanyPid(String companyPid) throws Exception;
	
	
	
	/**
	 * 根据银行主键及企业主键,查询获取有效状态的企业银行账号信息
	 * @author  lixq 
	 * @param   companyPid
	 * @param   bankId
	 * @return	List<Map<String,Object>>  
	 * @throws 	Exception  
	 * @date 	2018年10月23日
	 */
	List<Map<String,Object>> getBankInfoByComPanyPidAndBankId(String companyPid,String bankId) throws Exception;
	
	
	
	/**
	 * 根据付款主键,查询获取股东相关信息
	 * @author  lixq 
	 * @param   payMentPid
	 * @return	Map<String,Object>
	 * @throws 	Exception    
	 * @date 	2018年10月23日
	 */
	Map<String,Object> getStockHolderPayMentByPayMentPid(String payMentPid) throws Exception;
	
	
	/**
	 * 批量删除股东付款请求
	 * @author  lixq 
	 * @param   payMentPids
	 * @return	int
	 * @throws  Exception    
	 * @date    2018年10月23日
	 */
	int delStockHolderPayByPids(List<String> payMentPids)  throws Exception;
	
	
	
	/**
	 * 查询项目公司股东信息index页列表
	 * @author zhangcai
	 * @param businessPrjInfoId 
	 * @param projectInfoPid 
	 * @param projectInfoPid 
	 * @param offset
	 * @param pageRows
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryProjCompanyStockholderList(String businessPrjInfoId, String projectInfoPid, Integer offset, Integer pageRows);
	
	/**
	 * 查询项目公司股东信息index页列表条数
	 * @author zhangcai
	 * @param businessPrjInfoId 
	 * @param projectInfoPid 
	 * @param projectInfoPid 
	 * @return int
	 */
	Integer countProjCompanyStockholderList(String businessPrjInfoId, String projectInfoPid);

	/**
	 * 根据选中的股东PID（即企业Pid）、项目公司Pid、项目Pid 查询股东表，校验是否重复
	 * @author zhangcai
	 * @param corPid
	 * @param prjId
	 * @param businessPrjInfoId 
	 * @return
	 */
	StockholderInfo getStockholderByCorpId(String businessPrjInfoId, String corPid, String prjId);

	/**
	 * 插入股东表
	 * @author zhangcai
	 * @param corPid
	 * @return
	 */
	int insertSelective(StockholderInfo stockholderInfo);

	/**
	 * 修改股东表
	 * @author zhangcai
	 * @param corPid
	 * @return
	 */
	int updateByPrimaryKeySelective(StockholderInfo stockholderInfo);

	/**
	 * 逻辑删除股东表
	 * @author zhangcai
	 * @param pids
	 * @param curPid 
	 * @return
	 */
	int delStockholderInfoByPids(List<String> pids, String curPid);

	/**
	 * 批量插入股东附件表
	 * @author zhangcai
	 * @param files
	 * @param stockholderPid
	 * @param loginUserId
	 * @return
	 */
	int batchInsertStockholderAccessory(List<EstateFundFile> files, String stockholderPid, String loginUserId);

	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---修改股东公司---查询股东附件
	 * @author zhangcai
	 * @param stockholderId
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getStockholderAccessoryByStockholderId(String stockholderId, Integer offset, Integer pageRows);

	/**
	 * 项目公司管理---新增项目公司---项目公司股东信息---修改股东公司---查询股东附件数量
	 * @author zhangcai
	 * @param stockholderId
	 * @param type 
	 * @return
	 */
	Integer countStockholderAccessoryByStockholderId(String stockholderId);

	/**
	 * 根据项目公司pid异步加载股东信息
	 * @author zhangcai
	 * @param stockholderId
	 * @param type 
	 * @return
	 */
	List<Map<String, Object>> getStockholderPidByBusinessPrjPid(String projectInfoPid, String businessPrjInfoPid);

	/**
	 * 根据项目公司pid、项目pid查询股东
	 * @author zhangcai
	 * @param businessPrjPid
	 * @param projectInfoPid 
	 * @return
	 */
	List<String> queryStockholderInfo(String businessPrjPid,String projectInfoPid);

	/**
	 * 根据股东Pid批量更新股东表
	 * @param pids
	 * @param projectInfoPid 
	 * @param userInfoPid
	 * @return
	 */
	int updateStockholderInfoByPids(List<String> pids, String projectInfoPid, String userInfoPid);

	/**
	 * 根据项目名称加载股东
	 * @author zhangcai
	 * @param projectInfoPid
	 * @return
	 */
	List<Map<String, Object>> getStockInfoByPrjId(String projectInfoPid);


}
