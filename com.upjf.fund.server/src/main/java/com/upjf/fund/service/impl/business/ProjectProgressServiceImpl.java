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
import com.upjf.fund.dto.ProjectProgress;
import com.upjf.fund.dto.ProjectProgressAccessory;
import com.upjf.fund.dto.ProjectProgressFileVo;
import com.upjf.fund.service.business.ProjectProgressService;
import com.upjf.fund.service.impl.business.mapper.ProjectProgressAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.ProjectProgressMapper;
import com.upjf.fund.service.impl.system.mapper.EstateFundFileMapper;
import com.upjf.fund.utils.PageUtils;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目基本信息服务接口实现类
 * @author lixq
 * @date 2018年9月29日
 */
@Service("projectProgressService")
public class ProjectProgressServiceImpl implements ProjectProgressService {
	
	@Autowired
	private ProjectProgressMapper projectProgressMapper;							//项目进度接口
	
	@Autowired
	private ProjectProgressAccessoryMapper projectProgressAccessoryMapper;			//项目进度附件接口
	
	@Autowired
	private EstateFundFileMapper estateFundFileMapper;								//文件接口
	
	/**
	 * 批量删除项目进度信息
	 */
	@Override
	public int delProProgressByPids(List<String> pids, String updateId) throws Exception {
		
		return 0;
	}
	
	
	
	/**
	 * 保存项目进度信息
	 */
	@Override
	@Transactional
	public String saveProProgress(ProjectProgress proProgress) throws Exception {
		String result = "";
		if(proProgress != null){
			
			//转换单位
			BigDecimal unit = new BigDecimal("10000");
			BigDecimal hasPushValue = proProgress.getHasPushValue();
			BigDecimal surplusPubshValue = proProgress.getSurplusPubshValue();
			
			if(hasPushValue != null){
				proProgress.setHasPushValue(hasPushValue.multiply(unit));
			}
			if(surplusPubshValue != null){
				proProgress.setSurplusPubshValue(surplusPubshValue.multiply(unit));
			}
			
			ProjectProgress projectProgress = projectProgressMapper.selectProProgressByKey(proProgress.getPid());
			
			if(projectProgress == null){
				String uuidGenerator = UuidGenerator.getUuidGenerator();
				String progressAccessoryPid = UuidGenerator.getUuidGenerator();
				proProgress.setPid(uuidGenerator);
				projectProgressMapper.saveProProgress(proProgress);
				
				//关系表优先插入一条无文件主键关系的记录
				ProjectProgressAccessory ppa = new ProjectProgressAccessory();
				ppa.setPid(progressAccessoryPid);
				ppa.setPrjProgressId(uuidGenerator);
				projectProgressAccessoryMapper.saveProgressAccessory(ppa);
				
				result = uuidGenerator;
			}else{
				Integer status = projectProgress.getStatus();
				if(status == 0){
					String uuidGenerator = UuidGenerator.getUuidGenerator();
					String progressAccessoryPid = UuidGenerator.getUuidGenerator();
					proProgress.setPid(uuidGenerator);
					projectProgressMapper.saveProProgress(proProgress);
					
					//关系表优先插入一条无文件主键关系的记录
					ProjectProgressAccessory ppa = new ProjectProgressAccessory();
					ppa.setPid(progressAccessoryPid);
					ppa.setPrjProgressId(uuidGenerator);
					projectProgressAccessoryMapper.saveProgressAccessory(ppa);
					
					result = uuidGenerator;
				}else{
					int returnInt = projectProgressMapper.updateProProgressByKey(proProgress);
					if(returnInt > 0){
						result = proProgress.getPid();
					}
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 根据项目主键,查询 获取项目基本信息
	 */
	@Override
	public ProjectProgress getProProgressByKey(String pid) throws Exception {
		
		return null;
	}
	
	
	/**
	 * 根据主键修改企业基本信息
	 */
	@Override
	public int updateProProgressByKey(ProjectProgress proProgress)
			throws Exception {
		
		return 0;
	}


	//校验变动的项目进度信息是否与最新一条记录一致:true为一致,false为不一致
	@Override
	public boolean cheackProProgress(ProjectProgress proProgress) throws Exception {
		boolean result = false;
		if(proProgress != null){
			proProgress.setStatus(1);
			int cheackProProgress = projectProgressMapper.cheackProProgress(proProgress);
			if(cheackProProgress > 0){
				result = true;
			}
		}
		return result;
	}


	//分页查询获取项目进度附件列表
	@Override
	public Map<String, Object> getProgressFileByPage(String projectId,Page page) throws Exception {
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
			
			List<ProjectProgressFileVo> progressFileList = projectProgressMapper.getProgressFileByPage(queryParams);
			
			int totalCount = projectProgressMapper.getTotalCount(queryParams);
			page.setTotalCount(totalCount);										//赋值最新相关页码信息
			
			resualtMap = new HashMap<String, Object>();
			resualtMap.put("progressFileList", progressFileList);
			resualtMap.put("page", page);
		}
		
		return resualtMap;
	}


	//删除项目进度状态
	@Override
	@Transactional
	public int delProgress(String pid) throws Exception {
		int result = -1;
		if(StringUtils.isNotBlank(pid)){
			ProjectProgressAccessory ppa = projectProgressAccessoryMapper.getProgressAccessoryByPid(pid);
			if(ppa != null){
				Integer status = ppa.getStatus();
				String prjProgressId = ppa.getPrjProgressId();
				
				switch (status) {
				case 0:
					ProjectProgress progress = projectProgressMapper.selectProProgressByKey(prjProgressId);
					if(progress != null){
						if(progress.getStatus() == 1){
							ProjectProgress ppg = new ProjectProgress();
							ppg.setPid(progress.getPid());
							ppg.setStatus(0);
							projectProgressMapper.updateProProgressByKey(ppg);
						}
					}
					
					String fileId = ppa.getFileId();
					EstateFundFile estateFundFile = estateFundFileMapper.selectByPrimaryKey(fileId);
					if(estateFundFile != null){
						if(estateFundFile.getStatus().equals("1")){
							EstateFundFile eff = new EstateFundFile();
							eff.setPid(estateFundFile.getPid());
							eff.setStatus("0");
							estateFundFileMapper.updateByPrimaryKeySelective(eff);
						}
					}
					
					result = 1;
					break;
				case 1:
					ProjectProgressAccessory  progressAccessory = new ProjectProgressAccessory();
					progressAccessory.setPid(pid);
					progressAccessory.setStatus(0);
					int updateResult = projectProgressAccessoryMapper.updateProgressAccessoryByKey(progressAccessory);
					if(updateResult > 0){
						String filePid = ppa.getFileId();
						EstateFundFile fundFile = estateFundFileMapper.selectByPrimaryKey(filePid);
						if(fundFile != null){
							if(fundFile.getStatus().equals("1")){
								EstateFundFile eff = new EstateFundFile();
								eff.setPid(fundFile.getPid());
								eff.setStatus("0");
								estateFundFileMapper.updateByPrimaryKeySelective(eff);
							}
						}
						
						Map<String, Object> queryParams = new HashMap<String, Object>();
						queryParams.put("progressPid", prjProgressId);
						queryParams.put("status", 1);
						List<ProjectProgressAccessory> list = projectProgressAccessoryMapper.getAllProgressAccessoryByCondition(queryParams);
						if(list == null || list.size() <= 0){
							ProjectProgress projectProgress = projectProgressMapper.selectProProgressByKey(prjProgressId);
							if(projectProgress != null){
								if(projectProgress.getStatus() == 1){
									ProjectProgress proProgress = new ProjectProgress();
									proProgress.setPid(projectProgress.getPid());
									proProgress.setStatus(0);
									projectProgressMapper.updateProProgressByKey(proProgress);
								}
							}
						}
						result = 1;
					}
					break;
				default:
					break;
				}
			}
		}
		
		return result;
	}


	
	//根据项目主键加载所属的最新的项目进度记录
	@Override
	public ProjectProgress getLatestProgressByProjectPid(String projectPid) throws Exception {
		ProjectProgress pp = null;
		if(StringUtils.isNotBlank(projectPid)){
			pp = projectProgressMapper.getLatestProgressByProjectPid(projectPid);
		}
		return pp;
	}
	
	
	
}
