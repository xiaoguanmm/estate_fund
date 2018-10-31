package com.upjf.fund.service.impl.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.DictInfo;
import com.upjf.fund.service.impl.system.mapper.DictDetailMapper;
import com.upjf.fund.service.impl.system.mapper.DictInfoMapper;
import com.upjf.fund.service.system.DictInfoService;
import com.upjf.fund.utils.UuidGenerator;

@Service("dictInfoService")
public class DictInfoServiceImpl implements DictInfoService{
	
	@Autowired
	private DictInfoMapper dictInfoMapper;
	
	@Autowired
	private DictDetailMapper dictDetailMapper;

	@Override
	@Transactional
	public int addDictInfo(DictInfo dictInfo) throws Exception {
		if(dictInfo.getDictDetails()==null){
			throw new Exception("字典 值/值描述 不能为空");
		}
		dictInfo.setPid(UuidGenerator.getUuidGenerator());
		int result = this.dictInfoMapper.insertSelective(dictInfo);
		if(result>0){
			for(int i=0;i<dictInfo.getDictDetails().length;i++){
				dictInfo.getDictDetails()[i].setPid(UuidGenerator.getUuidGenerator());
				dictInfo.getDictDetails()[i].setDictInfoId(dictInfo.getPid());
				
			}
		}else{
			return 0; 
		}
		result = this.dictDetailMapper.batchInserDictDetail(dictInfo.getDictDetails());
		if(result>0){
			return result;
		}
		throw new Exception("字典值插入失败");
		
	}

	@Override
	public List<Map<String, Object>> getDictInfosByConditions(DictInfo dictInfo, Integer offset, Integer pageRows) {
		
		return this.dictInfoMapper.getDictInfosByConditions(dictInfo,offset,pageRows);
	}

	@Override
	public Integer countDictInfosByConditions(DictInfo dictInfo) {
		return this.dictInfoMapper.countDictInfosByConditions(dictInfo);
	}

	@Override
	public List<DictInfo> getDictDetailsBydictId(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int modifyDictInfo(DictInfo dictInfo) throws Exception {
		
		int result = this.dictInfoMapper.updateByPrimaryKeySelective(dictInfo);
		
		if(result>0 && dictInfo.getDictDetails()!=null && dictInfo.getDictDetails().length>0){
			for(int i=0;i<dictInfo.getDictDetails().length;i++){
				dictInfo.getDictDetails()[i].setPid(UuidGenerator.getUuidGenerator());
				dictInfo.getDictDetails()[i].setDictInfoId(dictInfo.getPid());
				
			}
			this.dictDetailMapper.deleteByDictId(dictInfo.getPid());
			result = this.dictDetailMapper.batchInserDictDetail(dictInfo.getDictDetails());
		}
		if(result>0){
			return result;
		}
		throw new Exception("更新字典详细表失败");
	}

	@Override
	public int delDictInfo(String pid) {
		int result =this.dictInfoMapper.deleteByPrimaryKey(pid);
		if(result>0){
			this.dictDetailMapper.deleteByDictId(pid);
		}
		return result;
	}

	@Override
	public DictInfo getDictInfoByDictName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DictInfo getDictInfoByDictCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DictInfo getDictInfoBySingleCondition(String oriCode, String oriName) {
		
		return this.dictInfoMapper.getDictInfoBySingleCondition(oriCode,oriName);
	}

	@Override
	public List<DictDetail> getDictDetailsByPid(String pid) {
		return this.dictDetailMapper.getDictDetailsByPid(pid);
	}

	@Override
	public List<DictInfo> getDictDetailsBydictCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 根据字典编码,查询获取对应的字典详情信息
	 * @author lixq
	 * @param code
	 * @return
	 */
	@Override
	public List<DictDetail> getDictDetailsByCode(String code) {
		List<DictDetail> list = new ArrayList<DictDetail>();
		if(StringUtils.isNotBlank(code)){
			 list = dictDetailMapper.getDictDetailsByCode(code);
		}
		
		return list;
	}

}
