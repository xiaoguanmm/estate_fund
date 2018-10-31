package com.upjf.fund.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upjf.fund.dto.AreaInfo;
import com.upjf.fund.service.impl.system.mapper.AreaInfoMapper;
import com.upjf.fund.service.system.AreaInfoService;


@Service("areaInfoService")
public class AreaInfoServiceImpl implements AreaInfoService{
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	
	/**
    * @Title: getAreaInfoList
    * @Description: 获取省市区集合
    * @param areaInfo
    * @return List<AreaInfo>    返回类型
    * @author wufujing
    * @date 2018年4月25日
    * @throws
	 */
	public List<AreaInfo> getAreaInfoList(AreaInfo areaInfo){
		return areaInfoMapper.getAreaInfoList(areaInfo);
    }

	/**
     * @Title: getAreaInfoByCode
     * @Description: 获取省市区详情
     * @param areaCode
     * @return AreaInfo
     * @author wufujing
     * @date 2018年4月25日
     * @throws
 	 */
	public AreaInfo getAreaInfoByCode(String areaCode){
    	return areaInfoMapper.getAreaInfoByCode(areaCode);
    }

	/**
     * @Title: getAreaInfoByAreaCodes
     * @Description: 获取省市区集合
     * @param List<String> areaCodes
     * @return List<AreaInfo>
     * @author wufujing
     * @date 2018年4月25日
     * @throws
 	 */
	public List<AreaInfo> getAreaInfoByAreaCodes(List<String> areaCodes){
		return areaInfoMapper.getAreaInfoByAreaCodes(areaCodes);
    }
	
}
