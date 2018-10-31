package com.upjf.fund.service.impl.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.ProjectContract;
import com.upjf.fund.service.business.ProjectContractService;
import com.upjf.fund.service.impl.business.mapper.ProjectContractMapper;
import com.upjf.fund.service.impl.system.mapper.EstateFundFileMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目基本信息服务接口实现类
 * @author lixq
 * @date 2018年9月29日
 */
@Service("projectContractService")
public class ProjectContractServiceImpl implements ProjectContractService {
	
	@Autowired
	private ProjectContractMapper projectContractMapper;							//项目合同附件与项目关系信息接口
	
	
	@Autowired
	private EstateFundFileMapper estateFundFileMapper;								//文件接口
	
	//保存项目合同附件与项目关系信息
	@Override
	@Transactional
	public String saveProjectContract(ProjectContract pc) throws Exception{
		String result = "";
		if(pc != null){
			String uuidGenerator = UuidGenerator.getUuidGenerator();
			pc.setPid(uuidGenerator);
			projectContractMapper.saveProjectContract(pc);
			result = uuidGenerator;
		}
		return result;
	}

	
	
	//分页获取项目合同附件列表信息
	@Override
	public Map<String, Object> getContractListByPage(String projectId, Page page) throws Exception {
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
			
			List<ProjectContract> contractList = projectContractMapper.getContractListByPage(queryParams);
			int totalCount = projectContractMapper.getTotalCount(queryParams);
			page.setTotalCount(totalCount);										//赋值最新相关页码信息
			
			resualtMap = new HashMap<String, Object>();
			resualtMap.put("contractList", contractList);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}


	//删除项目合同附件
	@Override
	@Transactional
	public int delContract(String pid) throws Exception {
		int result = -1;
		if(StringUtils.isNotBlank(pid)){
			ProjectContract projectContract = projectContractMapper.selectByPrimaryKey(pid);
			if(projectContract != null){
				Integer status = projectContract.getStatus();
				String fileId = projectContract.getFileId();
				
				if(status == 1){
					ProjectContract pc = new ProjectContract();
					pc.setPid(pid);
					pc.setStatus(0);
					int updateReslut = projectContractMapper.updateProjectContractByPid(pc);
					if(updateReslut > 0){
						if(StringUtils.isNotBlank(fileId)){
							EstateFundFile eff = new EstateFundFile();
							eff.setPid(fileId);
							eff.setStatus("0");
							estateFundFileMapper.updateByPrimaryKeySelective(eff);
						}
					}
				}
			}
			result = 1;
		}
		return result;
	}
	
	
	
	
	
}
