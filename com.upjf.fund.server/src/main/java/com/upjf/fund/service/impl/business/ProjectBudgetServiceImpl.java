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
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.ProjectBudget;
import com.upjf.fund.dto.ProjectBudgetAccessory;
import com.upjf.fund.dto.ProjectBudgetVo;
import com.upjf.fund.service.business.ProjectBudgetService;
import com.upjf.fund.service.impl.business.mapper.ProjectBudgetAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectBudgetMapper;
import com.upjf.fund.service.impl.system.mapper.EstateFundFileMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目预算信息服务接口实现类
 * @author lixq
 * @date 2018年9月26日
 */
@Service("projectBudgetService")
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

	@Autowired
	private ProjectBudgetMapper projectBudgetMapper;							//项目预算信息服务接口
	
	@Autowired
	private ProjectBudgetAccessoryMapper projectBudgetAccessoryMapper;			//项目预算附件信息服务接口
	
	@Autowired
	private EstateFundFileMapper estateFundFileMapper;							//文件服务接口
	
	
	//保存项目预算信息
	@Override
	@Transactional
	public String saveProjectBudget(ProjectBudget pb) throws Exception {
		String result = "";
		ProjectBudgetAccessory pba = null;
		String pbaPid = UuidGenerator.getUuidGenerator();
		String prjBudgetId = UuidGenerator.getUuidGenerator();
		if(pb != null){
			
			//转换单位
			BigDecimal unit = new BigDecimal("10000");
			BigDecimal projectProfitBudget = pb.getProjectProfitBudget();
			if(projectProfitBudget != null){
				pb.setProjectProfitBudget(projectProfitBudget.multiply(unit));
			}
			
			//校验是否存在,存在则更新,不存在则新增
			ProjectBudget budget = projectBudgetMapper.getProjectBudgetByKey(pb.getPid());
			if(budget != null){
				Integer status = budget.getStatus();
				if(status == 1){
					//更新
					int updateResult = projectBudgetMapper.updateProjectBudgetByPid(pb);
					if(updateResult > 0){
						result = pb.getPid();
					}
				}else{
					//新增
					pb.setPid(prjBudgetId);
					projectBudgetMapper.saveProjectBudget(pb);
					
					//优先新增一条附件关系记录
					pba = new ProjectBudgetAccessory();
					pba.setPid(pbaPid);
					pba.setPrjBudgetId(prjBudgetId);
					projectBudgetAccessoryMapper.saveProjectBudgetAccessory(pba);
					
					result = prjBudgetId;
				}
			}else{
				//新增
				pb.setPid(prjBudgetId);
				projectBudgetMapper.saveProjectBudget(pb);
				result = prjBudgetId;
				
				//优先新增一条附件关系记录
				pba = new ProjectBudgetAccessory();
				pba.setPid(pbaPid);
				pba.setPrjBudgetId(prjBudgetId);
				projectBudgetAccessoryMapper.saveProjectBudgetAccessory(pba);
				
				result = prjBudgetId;
			}
		}
		return result;
	}


	//校验预算名称是否改变:true表示预算名称已改变,false表示项目预算名称尚未改变
	@Override
	public boolean cheackBudget(ProjectBudget pb) throws Exception {
		boolean reslut = false;
		if(pb != null){
			ProjectBudget budget = projectBudgetMapper.getProjectBudgetByKey(pb.getPid());
			if(budget != null){
				String budgetName = budget.getBudgetName();
				if(!budgetName.equals(pb.getBudgetName())){
					reslut = true;
				}
			}
		}
		return reslut;
	}

	
	
	//分页获取项目预算记录
	@Override
	public Map<String, Object> getBudgetDataByPage(String projectId, Page page) throws Exception {
		Map<String,Object> queryParams = new HashMap<String, Object>();			//封装查询条件
		Map<String,Object> resualtMap = null;									//封装结果信息
		
		PageUtils.toTrimPageFields(page);										//页码信息去空格赋值
		int curPage = Integer.parseInt(page.getCurPage());
		int pageSize  = Integer.parseInt(page.getPageSize());
		Integer startIndex = (curPage - 1) * pageSize;							//起始查询索引
		
		
		if(StringUtils.isNotBlank(projectId)){													
			queryParams.put("projectId", projectId);
			queryParams.put("startIndex", startIndex);
			queryParams.put("pageSize", pageSize);
			
			List<ProjectBudgetVo> budgetList = projectBudgetMapper.getBudgetDataByPage(queryParams);
			
			int totalCount = projectBudgetMapper.getTotalCount(queryParams);
			page.setTotalCount(totalCount);										//赋值最新相关页码信息
			
			resualtMap = new HashMap<String, Object>();
			resualtMap.put("budgetList", budgetList);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}

	
	
	//删除项目预算记录
	@Override
	public int delbudget(String pid) throws Exception {
		int result = -1;
		List<ProjectBudgetAccessory> list = null;
		if(StringUtils.isNotBlank(pid)){
			//先变更状态,接着变更文件表状态,接着校验是否已经为最后一条关系,如果是,则变更主表记录
			ProjectBudgetAccessory oldPba = projectBudgetAccessoryMapper.selectByPrimaryKey(pid);
			if(oldPba != null){
				Integer status = oldPba.getStatus();
				String fileId = oldPba.getFileId();
				String prjBudgetId = oldPba.getPrjBudgetId();
				
				if(status == 1){
					ProjectBudgetAccessory pba = new ProjectBudgetAccessory();
					pba.setPid(pid);
					pba.setStatus(0);
					int updateResult = projectBudgetAccessoryMapper.updateBudgetAccessoryByPid(pba);
					if(updateResult > 0){
						EstateFundFile oldFile = estateFundFileMapper.selectByPrimaryKey(fileId);
						if(oldFile != null){
							estateFundFileMapper.updateFileStatus(fileId, "0");
							
						}
						
						list = projectBudgetAccessoryMapper.getAccessoryByBudgetPid(prjBudgetId, 1);
						if(list == null || list.size() <= 0){
							ProjectBudget pb = new ProjectBudget();
							pb.setPid(prjBudgetId);
							pb.setStatus(0);
							projectBudgetMapper.updateProjectBudgetByPid(pb);
						}
					}
				}
				
				result = 1;
			}
		}
		return result;
	}

	
	//根据项目主键获取所属的最新的项目预算信息
	@Override
	public ProjectBudget getLatestBudgetByProjectPid(String projectPid) throws Exception {
		ProjectBudget pb = null;
		if(StringUtils.isNotBlank(projectPid)){
			pb = projectBudgetMapper.getLatestBudgetByProjectPid(projectPid);
		}
		return pb;
	}
	
	
	
}
