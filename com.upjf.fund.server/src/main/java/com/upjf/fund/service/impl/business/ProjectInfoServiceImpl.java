package com.upjf.fund.service.impl.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.ProjectBudget;
import com.upjf.fund.dto.ProjectBudgetAccessory;
import com.upjf.fund.dto.ProjectContract;
import com.upjf.fund.dto.ProjectInfo;
import com.upjf.fund.dto.ProjectProgress;
import com.upjf.fund.dto.ProjectProgressAccessory;
import com.upjf.fund.dto.ProjectVo;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.impl.business.mapper.BusinessCompositionMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectBudgetAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectBudgetMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectContractMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectInfoMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectProgressAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectProgressMapper;
import com.upjf.fund.service.impl.business.mapper.StockholderInfoMapper;
import com.upjf.fund.service.impl.system.mapper.EstateFundFileMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目基本信息服务接口实现类
 * @author lixq
 * @date 2018年9月26日
 */
@Service("projectInfoService")
public class ProjectInfoServiceImpl implements ProjectInfoService {
	
	
	@Autowired
	private ProjectInfoMapper projectInfoMapper;									//项目基本信息接口
	
	@Autowired
	private BusinessCompositionMapper businessCompositionMapper;					//项目业态组成信息接口
	
	@Autowired
	private ProjectContractMapper projectContractMapper;							//项目合同附件与项目关系信息接口
	
	@Autowired
	private EstateFundFileMapper estateFundFileMapper;								//文件接口
	
	@Autowired
	private ProjectProgressMapper projectProgressMapper;							//项目进度接口
	
	@Autowired
	private ProjectProgressAccessoryMapper projectProgressAccessoryMapper;			//项目进度附件接口
	
	@Autowired
	private ProjectBudgetMapper projectBudgetMapper;								//项目预算信息服务接口
	
	@Autowired
	private ProjectBudgetAccessoryMapper projectBudgetAccessoryMapper;				//项目预算附件信息服务接口
	
	@Autowired
	private StockholderInfoMapper stockholderInfoMapper;							//股东信息服务接口
	
	
	
	//分页获取项目管理信息
	@Override
	public Map<String, Object> getProjectVoListByPage(ProjectVo projectVo,Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();			//封装查询条件
		Map<String,Object> resualtMap = null;									//封装结果信息
		
		PageUtils.toTrimPageFields(page);										//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;							//起始查询索引
		
		
		if(projectVo != null){
			queryParams.put("projectName", projectVo.getProjectName());
			queryParams.put("projectCompany", projectVo.getProjectCompany());
			queryParams.put("projectProvinceId", projectVo.getProjectProvinceId());
			queryParams.put("projetCityId", projectVo.getProjetCityId());
			queryParams.put("projectRegionId", projectVo.getProjectRegionId());
			queryParams.put("projectCategoryId", projectVo.getProjectCategoryId());
			queryParams.put("landgetWayId", projectVo.getLandgetWayId());
			queryParams.put("projectProgress", projectVo.getProjectProgress());
		}
	
		queryParams.put("startIndex", startIndex);
		queryParams.put("pageSize", pageSize);
		
		List<ProjectVo> projectVoList = projectInfoMapper.getProjectVoListByPage(queryParams);
		int totalCount = projectInfoMapper.getTotalCount(queryParams);
		page.setTotalCount(totalCount);										//赋值最新相关页码信息
		
		resualtMap = new HashMap<String, Object>();
		resualtMap.put("projectVoList", projectVoList);
		resualtMap.put("page", page);
		
		return resualtMap;
	}
	
	
	
	//批量删除项目信息
	@Override
	@Transactional
	public int delProjectByPids(List<String> projectPids, String updateId) throws Exception {
		int result = -1;
		List<String> busCompoPids = null;
		List<ProjectBudget> budgetList = null;
		List<ProjectContract> contractList = null;
		List<ProjectProgress> progressList = null;
		List<ProjectProgressAccessory> accessoryList = null;
		List<ProjectBudgetAccessory> budgetAccessoryList = null;
		
		List<String> contractPids = new ArrayList<String>();
		List<String> contractFilePids =  new ArrayList<String>();
		List<String> progressPids =  new ArrayList<String>();
		List<String> accessoryPids =  new ArrayList<String>();
		List<String> accessoryFilePids =  new ArrayList<String>();
		List<String> budgetPids =  new ArrayList<String>();
		List<String> budgetAccessoryPids =  new ArrayList<String>();
		List<String> budgetAccessoryFilePids =  new ArrayList<String>();
		
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		Date updateDate = new Date();
		paramsMap.put("updateId", updateId);
		paramsMap.put("updateDate", updateDate);
		
		if(projectPids != null && projectPids.size() > 0){
			paramsMap.put("idList", projectPids);
			contractList = projectContractMapper.getContractByProjectPids(paramsMap);
			progressList = projectProgressMapper.getProgressByProjectPids(paramsMap);
			budgetList = projectBudgetMapper.getBudgetByProjectPids(paramsMap);
			
			//首先变更项目状态
			int updateProjectResult = projectInfoMapper.delProInfoByPids(paramsMap);
			if(updateProjectResult > 0){
				//其次根据项目主键校验是否存在处于有效状态的项目业态组成信息,有就变更它们.
				busCompoPids = businessCompositionMapper.getBusCompoPidByProjectPids(paramsMap);
				if(busCompoPids != null && busCompoPids.size() > 0){
					paramsMap.put("idList", busCompoPids);
					businessCompositionMapper.delBusCompoByPids(paramsMap);
				}
				
				//接着根据项目主键校验是否存在处于有效状态的项目合同信息,有就变更它们的状态,并同时变更其对应的项目合同文件状态.
				if(contractList !=null && contractList.size() > 0){
					for (ProjectContract pc : contractList) {
						String contractPid = pc.getPid();
						String contractFilePid = pc.getFileId();
						
						if(StringUtils.isNotBlank(contractFilePid)){
							contractFilePids.add(contractFilePid);
						}
						contractPids.add(contractPid);
					}
					
					paramsMap.put("idList", contractPids);
					int updateContractResult = projectContractMapper.delContractByPids(paramsMap);
					if(updateContractResult > 0){
						if(contractFilePids != null && contractFilePids.size() > 0){
							paramsMap.put("idList", contractFilePids);
							estateFundFileMapper.delEstateFundFileByPids(paramsMap);
						}
					}
				}
				
				
				//接着根据项目主键校验是否存在处于有效状态的项目进度信息,有就变更它们的状态,并校验这些项目进度是否存在文件,有就变更文件状态
				if(progressList != null && progressList.size() > 0){
					for (ProjectProgress pp : progressList) {
						String progressPid = pp.getPid();
						progressPids.add(progressPid);
					}
					
					paramsMap.put("idList", progressPids);
					projectProgressMapper.delProgressByPids(paramsMap);
					
					accessoryList = projectProgressAccessoryMapper.getProgressAccessoryByProgressPids(paramsMap);
					if(accessoryList != null && accessoryList.size() > 0){
						for (ProjectProgressAccessory ppa : accessoryList) {
							String accessoryPid = ppa.getPid();
							String accessoryFileId = ppa.getFileId();
							
							if(StringUtils.isNotBlank(accessoryFileId)){
								accessoryFilePids.add(accessoryFileId);
							}
							accessoryPids.add(accessoryPid);
						}
						
						paramsMap.put("idList", accessoryPids);
						int udaptePpaResult = projectProgressAccessoryMapper.delProgressAccessoryByPids(paramsMap);
						if(udaptePpaResult > 0){
							if(accessoryFilePids != null && accessoryFilePids.size() > 0){
								paramsMap.put("idList", accessoryFilePids);
								estateFundFileMapper.delEstateFundFileByPids(paramsMap);
							}
						}
					}
				}
				
				//接着根据项目主键校验是否存在处于有效状态的项目预算信息,有就变更它们的状态,并校验这些项目预算是否存在文件,有就变更文件状态.
				if(budgetList != null && budgetList.size() > 0){
					for (ProjectBudget pb : budgetList) {
						String budgetPid = pb.getPid();
						budgetPids.add(budgetPid);
					}
					paramsMap.put("idList", budgetPids);
					int updateBudgetResult = projectBudgetMapper.delBudgetByPids(paramsMap);
					if(updateBudgetResult > 0){
						budgetAccessoryList = projectBudgetAccessoryMapper.getBudgetAccessoryByBudgetPids(paramsMap);
						if(budgetAccessoryList != null && budgetAccessoryList.size() > 0){
							for (ProjectBudgetAccessory pba : budgetAccessoryList) {
								String budgetAccessoryPid = pba.getPid();
								String budgetAccessoryFilePid = pba.getFileId();
								
								if(StringUtils.isNotBlank(budgetAccessoryFilePid)){
									budgetAccessoryFilePids.add(budgetAccessoryFilePid);
								}
								budgetAccessoryPids.add(budgetAccessoryPid);
							}
							paramsMap.put("idList", budgetAccessoryPids);
							int budgetAccessoryResult = projectBudgetAccessoryMapper.delBudgetAccessoryByPids(paramsMap);
							if(budgetAccessoryResult > 0){
								if(budgetAccessoryFilePids != null && budgetAccessoryFilePids.size() > 0){
									paramsMap.put("idList", budgetAccessoryFilePids);
									estateFundFileMapper.delEstateFundFileByPids(paramsMap);
								}
							}
						}
						
					}
				}
				
				result = 1;
			}
		}
		return result;
	}
	
	
	//保存项目基本信息
	@Override
	@Transactional
	public Map<String, Object> saveProjectInfo(ProjectInfo proInfo) throws Exception {
		Map<String, Object> resultMap = null;
		String uuid = UuidGenerator.getUuidGenerator();
		String projectCode = UuidGenerator.getDateFormatUuid(6);
		String newProjectCompanyPid = proInfo.getBusinessPrjInfoId();
		
		if(proInfo != null){
			
			//转换单位
			BigDecimal unit = new BigDecimal("10000");
			BigDecimal projectAreaCost = proInfo.getProjectAreaCost();
			BigDecimal allPrice = proInfo.getAllPrice();
			BigDecimal projectAllPutInto = proInfo.getProjectAllPutInto();
			BigDecimal expectEarnings = proInfo.getExpectEarnings();
			
			if(projectAreaCost != null){
				proInfo.setProjectAreaCost(projectAreaCost.multiply(unit));
			}
			if(allPrice != null){
				proInfo.setAllPrice(allPrice.multiply(unit));
			}
			if(projectAllPutInto != null){
				proInfo.setProjectAllPutInto(projectAllPutInto.multiply(unit));
			}
			if(expectEarnings != null){
				proInfo.setExpectEarnings(expectEarnings.multiply(unit));
			}
		
			
			ProjectInfo projectInfo = getProjectInfoByKey(proInfo.getPid());
			if(projectInfo != null){//修改
				int result = projectInfoMapper.updateProInfoByKey(proInfo);
				if(result >0){
					resultMap = new HashMap<String, Object>();
					resultMap.put("pid", proInfo.getPid());
					resultMap.put("projectCode", proInfo.getProjectCode());
				}
			}else{//新增
				proInfo.setPid(uuid);
				proInfo.setProjectCode(projectCode);
				projectInfoMapper.saveProjectInfo(proInfo);
				
				resultMap = new HashMap<String, Object>();
				resultMap.put("pid", uuid);
				resultMap.put("projectCode", projectCode);
				
			}
			
			if(StringUtils.isNotBlank(newProjectCompanyPid)){
				List<String> stockHolderList = stockholderInfoMapper.getStockholderInfoOfNullPrjId(newProjectCompanyPid);
				if(stockHolderList != null && stockHolderList.size()> 0){
					Map<String, Object> paramsMap = new HashMap<String, Object>();
					paramsMap.put("prjId",resultMap.get("pid"));
					paramsMap.put("updateDate", new Date());
					paramsMap.put("updateId", proInfo.getUpdateId());
					paramsMap.put("pids", stockHolderList);
					
					stockholderInfoMapper.updateStockholderInfoByPids(paramsMap);
				}
			}
		}
		
		return resultMap;
	}
	
	
	//根据项目主键,查询 获取项目基本信息
	@Override
	public ProjectInfo getProjectInfoByKey(String pid) throws Exception {
		ProjectInfo projectInfo = projectInfoMapper.getProjectInfoByKey(pid);
		return projectInfo;
	}
	
	
	//根据主键修改项目基本信息
	@Override
	@Transactional
	public int updateProjectInfoByKey(ProjectInfo proInfo) throws Exception{
		int result = -1;
		if(proInfo != null){
			result = projectInfoMapper.updateProInfoByKey(proInfo);
		}
		return result;
	}

	
	
	//根据状态,获取有效或者无效状态的项目信息
	@Override
	public List<ProjectInfo> getProjectListByStatus(Integer status) throws Exception {
		List<ProjectInfo> list = new ArrayList<ProjectInfo>();
		if(status != null){
			list = projectInfoMapper.getProjectListByStatus(status);
		}
		return list;
	}



	@Override
	public List<Map<String, Object>> getBusinessPrjInfoPidByPrjId(String projectInfoPid) {
		return this.projectInfoMapper.getBusinessPrjInfoPidByPrjId(projectInfoPid);
	}



	@Override
	public String getProjectStatusByProjectId(String projectId) {
		
		return this.projectInfoMapper.getProjectStatusByProjectId(projectId);
	}



	
}
