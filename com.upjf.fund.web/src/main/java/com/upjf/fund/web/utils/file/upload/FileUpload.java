package com.upjf.fund.web.utils.file.upload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.exception.FileException;
import com.upjf.fund.service.system.EstateFundFileService;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.spring.SpringContextUtils;

/**
 * 文件上传
 * @company upjf.com
 * @author guantong
 *
 */
public class FileUpload extends AbstractFileUpload{
	
	private static Logger logger = LoggerFactory.getLogger(FileUpload.class);
	
	private List<EstateFundFile> estateFundFiles;
	
	private List<EstateFundFile> getEstateFundFiles() {
		return estateFundFiles;
	}

	private void setEstateFundFiles(List<EstateFundFile> estateFundFiles) {
		this.estateFundFiles = estateFundFiles;
	}

	@Override
	public int insertEstateFile(Map<String, MultipartFile> fileMap,String url) {
		List<EstateFundFile> estateFundFiles = new ArrayList<EstateFundFile>();
		for(Map.Entry<String, MultipartFile> entry:fileMap.entrySet()){
			MultipartFile file = entry.getValue();
			EstateFundFile estateFundFile = new EstateFundFile();
			UserInfo userInfo = BaseController.getUserInfo();
			String fileName = file.getOriginalFilename();
			estateFundFile.setPid(UuidGenerator.getUuidGenerator());
			estateFundFile.setFileName(getTimeMills()+fileName.substring(0, fileName.lastIndexOf(".")));
			estateFundFile.setRealName(fileName.substring(0, fileName.lastIndexOf(".")));
			estateFundFile.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
			estateFundFile.setFileSize(file.getSize());
			estateFundFile.setFileUrl(url+getTimeMills()+fileName);
			estateFundFile.setCreateId(userInfo.getPid());
			estateFundFile.setCreateDate(new Date());
			estateFundFiles.add(estateFundFile);
		}
		EstateFundFileService estateFundFileService = SpringContextUtils.getBean(EstateFundFileService.class);
		int result = estateFundFileService.batchInsertFiles(estateFundFiles);
		setEstateFundFiles(estateFundFiles);
		return result;
	}
	
	@Override
	public int deleteEstateFile(String pid) {
		EstateFundFileService estateFundFileService = SpringContextUtils.getBean(EstateFundFileService.class);
		return estateFundFileService.delete(pid);
	}
	
	@Override
	public int updateFileStatus(String pid) {
		EstateFundFileService estateFundFileService = SpringContextUtils.getBean(EstateFundFileService.class);
		return estateFundFileService.updateFileStatus(pid,EstateFundConstants.STATUS_DELETE);
	}
	
	/**
	 * 上传文件
	 * @param files
	 * @param request
	 * @param response
	 * @param modulePath
	 */
	public static List<EstateFundFile> uploadFile(HttpServletRequest request,HttpServletResponse response,String modulePath){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		FileUpload up = new FileUpload();
		try {
			up.upload(fileMap, request, response, StringUtils.isEmpty(modulePath)?"":modulePath);
			return up.getEstateFundFiles();
		} catch (FileException e){
			logger.info(e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
			logger.info("上传文件异常");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除文件(物理删除)
	 * @param pid
	 * @param filePath
	 */
	public static boolean deleteFile(String pid,String filePath){
		FileUpload up = new FileUpload();
		try {
			up.delete(pid,filePath);
			return true;
		} catch (FileException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			logger.info("删除文件异常");
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除文件(逻辑删除)
	 * @param pid
	 * @return
	 */
	public static boolean deleteFile(String pid){
		FileUpload up = new FileUpload();
		try {
			up.delete(pid);
			return true;
		} catch (FileException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			logger.info("更新文件状态异常");
			e.printStackTrace();
		}
		return false;
	}

}
