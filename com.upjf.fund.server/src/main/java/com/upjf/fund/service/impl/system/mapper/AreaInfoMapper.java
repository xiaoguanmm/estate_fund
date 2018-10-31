package com.upjf.fund.service.impl.system.mapper;

import java.util.List;

import com.upjf.fund.dto.AreaInfo;

public interface AreaInfoMapper {

    List<AreaInfo> getAreaInfoList(AreaInfo areaInfo);

    AreaInfo getAreaInfoByCode(String areaCode);

    List<AreaInfo> getAreaInfoByAreaCodes(List<String> areaCodes);
}