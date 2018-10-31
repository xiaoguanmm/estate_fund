package com.upjf.fund.web.utils.file.upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.upjf.fund.constants.I18nConstants;
import com.upjf.fund.exception.FileException;
import com.upjf.fund.web.utils.file.FileProperties;

/**
 * 
 * @company upjf.com
 * @author guantong
 * @description 文件上传抽象类
 *
 */
public abstract class AbstractFileUpload {
	
	private long timeMills;
	
	private String dateStr;
	
	protected long getTimeMills() {
		return timeMills;
	}

	protected void setTimeMills(long timeMills) {
		this.timeMills = timeMills;
	}

	protected String getDateStr() {
		return dateStr;
	}

	protected void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	/**
	 * 该方法需要重写(此处不定义为抽象,为了后面在静态方法中不实现)
	 * @param fileMap
	 * @param url
	 * @return
	 */
	public int insertEstateFile(Map<String, MultipartFile> fileMap,String url){
		
		return 0;
	}
	
	/**
	 * 该方法需要重写(此处不定义为抽象,为了后面在静态方法中不实现)
	 * @param pid
	 * @return
	 */
	public int deleteEstateFile(String pid){
		
		return 0;
	}
	/**
	 * 该方法需要重写(此处不定义为抽象,为了后面在静态方法中不实现)
	 * @param pid
	 * @return
	 */
	public int updateFileStatus(String pid) {
		
		return 0;
		
	}
	
	
	
	public void upload(Map<String, MultipartFile> fileMap,HttpServletRequest request,HttpServletResponse response,String modulePath){
		try {
			if(uploadFiles(fileMap, request, response, modulePath)){
				int result = insertEstateFile(fileMap,getfileUrl(modulePath));
				if(result==0){
					deleteFiles(fileMap,modulePath);
					throw new FileException(I18nConstants.FILE_UPLOAD_FAIL, "插入系统文件表异常,上传失败");
				}
				
			}else{
				throw new FileException(I18nConstants.FILE_UPLOAD_FAIL,"文件不存在或未选择上传文件");
			}
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量删除文件
	 * @param fileMap
	 * @param modulePath
	 */
	private void deleteFiles(Map<String, MultipartFile> fileMap, String modulePath) {
		for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
			MultipartFile file = entry.getValue();
			String fileName = file.getOriginalFilename();
			String absolutePath = getAbsolutePath(modulePath, fileName);
			File f = new File(absolutePath);
			if(f.exists()){
				f.delete();
			}
		}
		
	}

	/**
	 * 删除文件
	 * @param pid
	 * @param filePath
	 * @param pid
	 */
	public void delete(String filePath,String pid){
		int result = deleteEstateFile(pid);
		if(result==0){
			throw new FileException(I18nConstants.FILE_DELETE_FAIL,"删除文件失败");
		}
		deleteFile(filePath);
	}
	
	
	/**
	 * 删除文件
	 * @param filePath
	 */
	private void deleteFile(String filePath) {
		String absolutePath = FileProperties.FILE_BASE_PATH+filePath;
		File file = new File(absolutePath);
		if(file.exists()){
			file.delete();
		}
	}

	public String getfileUrl(String modulePath){
		StringBuilder relativePath = new StringBuilder();
		relativePath.append(FileProperties.FILE_ROOT_PATH);
		relativePath.append(FileProperties.FILE_SEPARATOR);
		relativePath.append(modulePath);
		relativePath.append(FileProperties.FILE_SEPARATOR);
		relativePath.append(getDateStr());
		relativePath.append(FileProperties.FILE_SEPARATOR);
		return relativePath.toString();
	}
	
	
	private String getAbsolutePath(String modulePath,String fileName){
		String dirPath = getUploadDir(modulePath);
		long timeMillis = System.currentTimeMillis();
		setTimeMills(timeMillis);
		return dirPath+FileProperties.FILE_SEPARATOR+timeMillis+fileName;
	}
	
	private String getUploadDir(String modulePath){
		StringBuilder absolutePath = new StringBuilder();
		absolutePath.append(FileProperties.FILE_BASE_PATH);
		absolutePath.append(FileProperties.FILE_ROOT_PATH);
		absolutePath.append(FileProperties.FILE_SEPARATOR);
		absolutePath.append(modulePath);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		setDateStr(sdf.format(date));
		absolutePath.append(FileProperties.FILE_SEPARATOR);
		absolutePath.append(getDateStr());
		return absolutePath.toString();
	}
	
	/**
	 * 多文件上传
	 * @param files
	 * @param request
	 * @param response
	 * @param modulePath 根据实际业务添加模块路径
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public boolean uploadFiles(Map<String, MultipartFile> fileMap,HttpServletRequest request,HttpServletResponse response,String modulePath) throws IllegalStateException, IOException{
		if(fileMap!=null && fileMap.size()>0){
			File dir = new File(getUploadDir(modulePath));
			if (!dir.exists()){
				dir.mkdirs();
			}
			for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
				MultipartFile file = entry.getValue();
				String fileName = file.getOriginalFilename();
				String absolutePath = getAbsolutePath(modulePath, fileName);
				file.transferTo(new File(absolutePath));
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
	
	/**
	 * 删除文件(逻辑删除)
	 * @param pid
	 */
	public void delete(String pid) {
		int result = updateFileStatus(pid);
		if(result==0){
			throw new FileException(I18nConstants.FILE_DELETE_FAIL,"更新文件状态失败");
		}
	}

}
