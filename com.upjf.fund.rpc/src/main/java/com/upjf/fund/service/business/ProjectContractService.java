package com.upjf.fund.service.business;

import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.ProjectContract;


/**
 * 项目合同附件服务接口
 * @author lixq
 * @date 2018年10月10日
 */
public interface ProjectContractService {
	
	//保存项目合同附件与项目关系信息
	String saveProjectContract(ProjectContract projectContract) throws Exception;
	
	
	//分页获取项目合同附件列表信息
	Map<String, Object> getContractListByPage(String projectId,Page page) throws Exception;
	
	
	//删除项目合同附件信息
	int delContract(String pid) throws Exception;
}
