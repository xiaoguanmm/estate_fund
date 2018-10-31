
package com.upjf.fund.service.system;

import java.util.List;

import com.upjf.fund.dto.AreaInfo;


public interface AreaInfoService{
	/**
    * @Title: getAreaInfoList
    * @Description: 获取省市区集合
    * @param areaInfo
    * @return List<AreaInfo>    返回类型
    * @author wufujing
    * @date 2018年4月25日
    * @throws
	 */
    List<AreaInfo> getAreaInfoList(AreaInfo areaInfo);

	/**
     * @Title: getAreaInfoList
     * @Description: 获取省市区详情
     * @param areaCode
     * @return AreaInfo
     * @author wufujing
     * @date 2018年4月25日
     * @throws
 	 */
    AreaInfo getAreaInfoByCode(String areaCode);

	/**
     * @Title: getAreaInfoList
     * @Description: 获取省市区集合
     * @param List<String> areaCodes
     * @return List<AreaInfo>
     * @author wufujing
     * @date 2018年4月25日
     * @throws
 	 */
    List<AreaInfo> getAreaInfoByAreaCodes(List<String> areaCodes);
}
