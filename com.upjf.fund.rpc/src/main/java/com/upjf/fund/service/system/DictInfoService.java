package com.upjf.fund.service.system;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.DictInfo;

public interface DictInfoService {
	/**
	 * 添加字典信息
	 * @param dictInfo
	 * @param detailValues
	 * @param detailNames
	 * @return
	 */
	int addDictInfo(DictInfo dictInfo)throws Exception;

	List<Map<String, Object>> getDictInfosByConditions(DictInfo dictInfo,Integer offset, Integer pageRows);

	Integer countDictInfosByConditions(DictInfo dictInfo);

	List<DictInfo> getDictDetailsBydictId(String pid);

	int modifyDictInfo(DictInfo dictInfo) throws Exception;

	int delDictInfo(String pid);

	DictInfo getDictInfoByDictName(String name);

	DictInfo getDictInfoByDictCode(String code);

	List<DictInfo> getDictDetailsBydictCode(String code);

	DictInfo getDictInfoBySingleCondition(String oriCode, String oriName);

	List<DictDetail> getDictDetailsByPid(String pid);
	
	/**
	 * 根据字典编码,查询获取对应的字典详情信息
	 * @author lixq
	 * @param code
	 * @return
	 */
	List<DictDetail> getDictDetailsByCode(String code);

}
