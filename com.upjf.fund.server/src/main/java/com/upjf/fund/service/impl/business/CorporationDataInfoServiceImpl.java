package com.upjf.fund.service.impl.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.CorporationDataInfo;
import com.upjf.fund.service.business.CorporationDataInfoService;
import com.upjf.fund.service.impl.business.mapper.CorporationDataInfoMapper;
import com.upjf.fund.service.impl.system.mapper.EstateFundFileMapper;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 企业资料扫描件服务接口实现类
 * @author lixq
 * @date 2018年9月19日
 */
@Service("corporationDataInfoService")
public class CorporationDataInfoServiceImpl implements CorporationDataInfoService {

	
	@Autowired
	private CorporationDataInfoMapper corporationDataInfoMapper;
	
	
	@Autowired
	private EstateFundFileMapper estateFundFileMapper;
	
	
	//根据企业基本信息主键和文件类型主键变更处于有效状态的企业资料扫描件状态(伪删除)
	@Override
	@Transactional
	public int delCorporationByCondition(String corPid,String corDataType, String updateId) throws Exception {
		int result = -1;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(corPid) && StringUtils.isNotBlank(corDataType)){
			paramsMap.put("updateId", updateId);
			paramsMap.put("updateDate", new Date());
			paramsMap.put("corpId", corPid);
			paramsMap.put("corDataType", corDataType);
			
			CorporationDataInfo corporationDataInfo = corporationDataInfoMapper.getDataInfoByCondition(paramsMap);
			int updateResult = corporationDataInfoMapper.delCorDataInfoByCondition(paramsMap);
			if(updateResult > 0){
				if(corporationDataInfo != null){
					String fileId = corporationDataInfo.getFileId();
					estateFundFileMapper.updateFileStatus(fileId, "0");
				}
				
				result = 1;
			}
		}
		return result;
	}

	
	
	//保存企业资料扫描件
	@Override
	@Transactional
	public String saveCorporationDataInfo(CorporationDataInfo corDataInfo) throws Exception {
		String resultId = "";
		CorporationDataInfo corporationDataInfo = null;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(corDataInfo != null){
			paramsMap.put("corpId", corDataInfo.getCorpId());
			paramsMap.put("corDataType", corDataInfo.getCorDataType());
			
			corporationDataInfo = corporationDataInfoMapper.getDataInfoByCondition(paramsMap);
			if(corporationDataInfo != null){
				int updateResult = corporationDataInfoMapper.delCorDataInfoByCondition(paramsMap);
				if(updateResult > 0){
					
					String fileId = corporationDataInfo.getFileId();
					if(StringUtils.isNotBlank(fileId)){
						estateFundFileMapper.updateFileStatus(fileId, "0");
					}
					
					String uuidGenerator = UuidGenerator.getUuidGenerator();
					corDataInfo.setPid(uuidGenerator);
					corporationDataInfoMapper.saveCorporationDataInfo(corDataInfo);
					resultId = uuidGenerator;
				}
			}else{
				String uuidGenerator = UuidGenerator.getUuidGenerator();
				corDataInfo.setPid(uuidGenerator);
				corporationDataInfoMapper.saveCorporationDataInfo(corDataInfo);
				resultId = uuidGenerator;
			}
			
		}
		
		return resultId;
	}


	//根据企业主键获取处于有效状态的企业资料扫描件信息
	@Override
	public List<CorporationDataInfo> getCorDataInfoByCorPid(String corPid) throws Exception {
		List<CorporationDataInfo> list = null;
		if(StringUtils.isNotBlank(corPid)){
			list = corporationDataInfoMapper.getCorDataInfoByCorPid(corPid);
		}
		return list;
	}
	
	
	
	
	
}
