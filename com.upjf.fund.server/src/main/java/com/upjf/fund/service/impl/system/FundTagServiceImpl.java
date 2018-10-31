package com.upjf.fund.service.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upjf.fund.service.impl.system.mapper.DictInfoMapper;
import com.upjf.fund.service.system.FundTagService;

@Service("fundTagService")
public class FundTagServiceImpl implements FundTagService {
	
	@Autowired
	private DictInfoMapper dictInfoMapper;

	@Override
	public List<Map<String, String>> getDataByCode(String code) {
		
		return this.dictInfoMapper.getdataByCode(code);
	}

	@Override
	public List<Map<String, String>> getEnterpriseDataByType(String type) {
		
		return this.dictInfoMapper.getEnterpriseDataByType(type);
	}

	

}
