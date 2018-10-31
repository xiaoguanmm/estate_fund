package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.BusinessPrjInfo;

/**
 * 企业项目关系表服务接口
 * @author zhangcai
 * @date 2018年9月21日
 */
public interface BusinessPrjInfoService {
	
	/**
	 * 根据条件查询项目公司列表
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getProjCompanyByConditon(Map<String, String> condtions, Integer offset, Integer pageRows);

	/**
	 * 根据条件查询项目公司总条数
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return int
	 */
	Integer countProjCompanyByCondition(Map<String, String> condtions);
	
	/**
	 * 根据企业Pid查询企业项目关系表
	 * @author zhangcai
	 * @param corporationInfoPid
	 * @date 2018年9月21日
	 * @return
	 */
	List<BusinessPrjInfo> getBusinessPrjInfoByCorPid(String corporationInfoPid);
	
	/**
	 * 插入企业项目关系表
	 * @author zhangcai
	 * @param corporationInfoPid
	 * @date 2018年9月21日
	 * @return
	 */
	int insertSelective(BusinessPrjInfo businessPrjInfo);
	
	
	/**
	 * 根据条件获取所有处于该条件下的项目公司信息
	 * @author  lixq 
	 * @param   businessPrjInfo
	 * @return  List<BusinessPrjInfo>  
	 * @date    2018年9月27日
	 */
	List<BusinessPrjInfo> getBusProjInfoByCondition(BusinessPrjInfo businessPrjInfo);

	/**
	 * 通过主键查询企业项目关系表
	 * @author zhangcai
	 * @param pid
	 * @date 2018年9月21日
	 * @return
	 */
	BusinessPrjInfo selectByPrimaryKey(String pid);

	/**
	 * update企业项目关系表
	 * @author zhangcai
	 * @param pid
	 * @date 2018年9月21日
	 * @return
	 */
	int updateBusinessPrjInfo(BusinessPrjInfo businessPrj);

	/**
	 * 插入企业项目关系表
	 * @author zhangcai
	 * @param prjCorpName
	 * @param corporationInfoPid
	 * @param userInfoPid
	 * @param businessPrjInfoPid 
	 * @return
	 */
	int insertBusinessPrjInfo(String businessPrjInfoPid,String prjCorpName, String corporationInfoPid,String userInfoPid);

	/**
	 * 删除企业项目关系表
	 * @author zhangcai
	 * @param prjCorpName
	 * @param corporationInfoPid
	 * @param userInfoPid
	 * @param businessPrjInfoPid 
	 * @return
	 */
	int delBusinessPrjInfoByPids(List<String> pids, String curPid);
}
