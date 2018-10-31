package com.upjf.fund.service.impl.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.ProjectBudgetAccessory;
import com.upjf.fund.service.business.ProjectBudgetAccessoryService;
import com.upjf.fund.service.impl.business.mapper.ProjectBudgetAccessoryMapper;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目预算信息服务接口实现类
 * @author lixq
 * @date 2018年9月26日
 */
@Service("projectBudgetAccessoryService")
public class ProjectBudgetAccessoryServiceImpl implements ProjectBudgetAccessoryService {
	
	@Autowired
	private ProjectBudgetAccessoryMapper projectBudgetAccessoryMapper;
	
	
	//保存项目预算附件信息
	@Override
	@Transactional
	public synchronized String saveProjectBudgetAccessory(ProjectBudgetAccessory pba) throws Exception {
		String result = "";
		ProjectBudgetAccessory oldBudget = null;
		if(pba != null){
			//根据当前传入的项目预算主键校验是否存在有效状态下的无文件主键的记录,如果有,则更新,如果无,直接新增
			oldBudget = projectBudgetAccessoryMapper.getBudgetAccessoryByBudgetPid(pba.getPrjBudgetId());
			if(oldBudget != null ){
				String pid = oldBudget.getPid();
				pba.setPid(pid);
				int updateResult = projectBudgetAccessoryMapper.updateBudgetAccessoryByPid(pba);
				if(updateResult > 0){
					result = pid;
				}
			}else{
				String budgetAccessoryPid = UuidGenerator.getUuidGenerator();
				pba.setPid(budgetAccessoryPid);
				projectBudgetAccessoryMapper.saveProjectBudgetAccessory(pba);
				result = budgetAccessoryPid;
			}
		}
		return result;
	}
	
	
	
	//根据主键变更项目预算附件信息
	@Override
	@Transactional
	public int updateProjectBudgetAccessory(ProjectBudgetAccessory pba) throws Exception {
		
		return 0;
	}
	
	
	
	//根据条件查询获取项目预算附件信息
	@Override
	public List<ProjectBudgetAccessory> getBudgetAccessoryByCondition(ProjectBudgetAccessory pba) throws Exception {
		
		return null;
	}

	
	
	
	
	
}
