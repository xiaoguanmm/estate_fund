package com.upjf.fund.service.impl.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.BusinessComposition;
import com.upjf.fund.service.business.BusinessCompositionService;
import com.upjf.fund.service.impl.business.mapper.BusinessCompositionMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目业态组成信息服务接口实现类
 * @author lixq
 * @date 2018年9月26日
 */
@Service("businessCompositionService")
public class BusinessCompositionServiceImpl implements BusinessCompositionService {
	
	
	@Autowired
	private BusinessCompositionMapper businessCompositionMapper;					//项目业态组成信息接口
	
	
	
	
	//根据条件分页查询获取项目业态组成信息
	@Override
	public Map<String, Object> getBusCompoListByPage(BusinessComposition busCompo, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();			//封装查询条件
		Map<String,Object> resualtMap = null;									//封装结果信息
		
		PageUtils.toTrimPageFields(page);										//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;							//起始查询索引
		
		
		if(busCompo != null){													
			queryParams.put("projectId", busCompo.getProjectId());
			queryParams.put("startIndex", startIndex);
			queryParams.put("pageSize", pageSize);
			queryParams.put("status", 1);
			
			List<BusinessComposition> busCompoList = businessCompositionMapper.getBusCompoListByPage(queryParams);
			int totalCount = businessCompositionMapper.getTotalCount(queryParams);
			page.setTotalCount(totalCount);										//赋值最新相关页码信息
			
			resualtMap = new HashMap<String, Object>();
			resualtMap.put("busCompoList", busCompoList);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}

	
	//批量删除项目业态组成信息
	@Override
	public int delBusCompoByPids(List<String> pids, String updateId) {
		
		return 0;
	}
	
	
	
	//保存项目业态组成信息
	@Override
	@Transactional
	public String saveBusCompoInfo(BusinessComposition busCompo) throws Exception{
		String result = "";
		if(busCompo != null){
			
			//转换单位
			BigDecimal unit = new BigDecimal("10000");
			BigDecimal projectValue = busCompo.getProjectValue();
			if(projectValue != null){
				busCompo.setProjectValue(projectValue.multiply(unit));
			}
			
			if(StringUtils.isNotBlank(busCompo.getPid())){
				BusinessComposition busCompoByKey = businessCompositionMapper.getBusCompoByKey(busCompo.getPid());
				
				if(busCompoByKey == null){
					String pid = UuidGenerator.getUuidGenerator();
					busCompo.setPid(pid);
					
					businessCompositionMapper.saveBusCompoInfo(busCompo);
					result = pid;
				}else{
					int effectCount = businessCompositionMapper.updateBusCompoByKey(busCompo);
					if(effectCount <= 0){
						result = "";
					}else{
						result = busCompo.getPid();
					}
				}
			}else{
				String pid = UuidGenerator.getUuidGenerator();
				busCompo.setPid(pid);
				
				businessCompositionMapper.saveBusCompoInfo(busCompo);
				result = pid;
			}
		}
		return result;
	}
	
	
	//根据项目主键,查询 获取项目业态组成信息
	@Override
	public BusinessComposition getBusCompoByKey(String pid) throws Exception {
		BusinessComposition busCompoByKey = null;
		if(StringUtils.isNotBlank(pid)){
			busCompoByKey = businessCompositionMapper.getBusCompoByKey(pid);
		}
		return busCompoByKey;
	}
	
	
	//根据主键修改企业基本信息
	@Override
	@Transactional
	public int updateBusCompoByKey(BusinessComposition busCompo) throws Exception {
		int result = 0;
		if(busCompo != null){
			result = businessCompositionMapper.updateBusCompoByKey(busCompo);
		}
		return result;
	}

	
	
}
