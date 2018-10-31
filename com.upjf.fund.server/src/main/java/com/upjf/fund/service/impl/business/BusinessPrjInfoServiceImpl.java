package com.upjf.fund.service.impl.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.BusinessPrjInfo;
import com.upjf.fund.service.business.BusinessPrjInfoService;
import com.upjf.fund.service.impl.business.mapper.BusinessPrjInfoMapper;

/**
 * 企业项目关系表服务接口实现类
 * @author zhangcai
 * @date 2018年9月21日
 */
@Service("businessPrjInfoService")
public class BusinessPrjInfoServiceImpl implements BusinessPrjInfoService {
	
	@Autowired
	private BusinessPrjInfoMapper businessPrjInfoMapper;
	
	private static Logger log = LoggerFactory.getLogger(BusinessPrjInfoServiceImpl.class);
	
	@Override
	public List<Map<String, Object>> getProjCompanyByConditon(
			Map<String, String> condtions, Integer offset, Integer pageRows) {
		return this.businessPrjInfoMapper.getProjCompanyByConditon(condtions,offset,pageRows);
	}

	@Override
	public Integer countProjCompanyByCondition(Map<String, String> condtions) {
		return this.businessPrjInfoMapper.countProjCompanyByCondition(condtions);
	}

	@Override
	public List<BusinessPrjInfo> getBusinessPrjInfoByCorPid(
			String corporationInfoId) {
		return this.businessPrjInfoMapper.getBusinessPrjInfoByCorPid(corporationInfoId);
	}

	@Override
	@Transactional
	public int insertSelective(BusinessPrjInfo businessPrjInfo) {
		return this.businessPrjInfoMapper.insertSelective(businessPrjInfo);
	}
	
	
	/**
	 * 根据条件获取所有处于该条件下的项目公司信息
	 * @author  lixq 
	 * @param   businessPrjInfo
	 * @return  List<BusinessPrjInfo>  
	 * @date    2018年9月27日
	 */
	@Override
	public List<BusinessPrjInfo> getBusProjInfoByCondition(BusinessPrjInfo businessPrjInfo) {
		List<BusinessPrjInfo> busProInfo = null;
		try {
			if(businessPrjInfo != null){
				busProInfo = businessPrjInfoMapper.getBusProjInfoByCondition(businessPrjInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,根据状态 获取所有处于该状态下的项目公司信息失败!");
		}
		return busProInfo;
	}

	@Override
	public BusinessPrjInfo selectByPrimaryKey(String pid) {
		return this.businessPrjInfoMapper.selectByPrimaryKey(pid);
	}

	@Override
	@Transactional
	public int updateBusinessPrjInfo(BusinessPrjInfo businessPrj) {
		return this.businessPrjInfoMapper.updateBusinessPrjInfo(businessPrj);
	}

	@Override
	public int insertBusinessPrjInfo(String businessPrjInfoPid,String prjCorpName,String corporationInfoPid, String userInfoPid) {
		BusinessPrjInfo businessPrjInfo = new BusinessPrjInfo();
		businessPrjInfo.setPid(businessPrjInfoPid);
		businessPrjInfo.setPrjCorpName(prjCorpName);
		businessPrjInfo.setCorporationInfoId(corporationInfoPid);
		businessPrjInfo.setCreateDate(new Date());
		businessPrjInfo.setCreateId(userInfoPid);
		int result = insertSelective(businessPrjInfo);
		return result;
	}

	@Override
	@Transactional
	public int delBusinessPrjInfoByPids(List<String> pids, String curPid) {
		int result = 0;
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		Date updateDate = new Date();
		if(pids != null && pids.size() > 0){
			paramsMap.put("pids", pids);
			paramsMap.put("status", 0);
			paramsMap.put("updateDate", updateDate);
			
			if(StringUtils.isNotBlank(curPid)){
				paramsMap.put("updateId", curPid);
			}
			
			result = this.businessPrjInfoMapper.delBusinessPrjInfoByPids(paramsMap);			//伪删除项目公司信息表
		}
		return result;
	}
	


	
	
}
