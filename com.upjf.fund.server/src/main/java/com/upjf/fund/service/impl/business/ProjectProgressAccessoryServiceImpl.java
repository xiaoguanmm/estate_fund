package com.upjf.fund.service.impl.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.ProjectProgressAccessory;
import com.upjf.fund.service.business.ProjectProgressAccessoryService;
import com.upjf.fund.service.impl.business.mapper.ProjectProgressAccessoryMapper;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目进度与文件关系信息表
 * @author lixq
 * @date 2018年9月30日
 */
@Service("projectProgressAccessoryService")
public class ProjectProgressAccessoryServiceImpl implements ProjectProgressAccessoryService {
	
	
	@Autowired
	private ProjectProgressAccessoryMapper projectProgressAccessoryMapper;
	
	
	//批量删除项目进度信息
	@Override
	public int delProgressAccessoryByPids(List<String> pids, String updateId) throws Exception {
		
		return 0;
	}
	
	
	//保存项目进度信息
	@Override
	@Transactional
	public synchronized String saveProgressAccessory(ProjectProgressAccessory ppa) throws Exception {
		String result ="";
		if(ppa != null ){
			//校验当前项目进度主键下是否存在无文件主键关系的记录存在,如果存在,则优先更新信息到该条记录,如果不存在,那么再新增
			String progressId = ppa.getPrjProgressId();
			ProjectProgressAccessory existPpa = projectProgressAccessoryMapper.getProgressAccessoryByProgressId(progressId);
			if(existPpa != null){
				ppa.setPid(existPpa.getPid());
				projectProgressAccessoryMapper.updateProgressAccessoryByKey(ppa);
				result = existPpa.getPid();
			}else{
				String uuidGenerator = UuidGenerator.getUuidGenerator();
				ppa.setPid(uuidGenerator);
				projectProgressAccessoryMapper.saveProgressAccessory(ppa);
				result = uuidGenerator;
			}
		}
		return result;
	}
	
	
	//根据项目主键,查询 获取项目基本信息
	@Override
	public ProjectProgressAccessory getProgressAccessoryByKey(String pid) throws Exception {
		
		return null;
	}
	
	
	//根据主键修改企业基本信息
	@Override
	public int updateProgressAccessoryByKey(ProjectProgressAccessory ppa) throws Exception {
		
		return 0;
	}

}
