package com.upjf.fund.service.system;

import java.util.List;
import java.util.Map;

/**
 * 地产基金自定义接口
 * @author Administrator
 *
 */
public interface FundTagService {

	/**
	 * 根据code获取dict数据(该方法需使用缓存，不需要每次均加载)
	 * @param code
	 * @return
	 */
	List<Map<String, String>> getDataByCode(String code);

	/**
	 * 获取企业和项目公司信息
	 * @param type
	 * @return
	 */
	List<Map<String, String>> getEnterpriseDataByType(String type);



}
