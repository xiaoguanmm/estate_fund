package com.upjf.fund.web.utils.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件常量类
 * @company upjf.com
 * @author guantong
 *
 */
@Component
public class FileProperties {
	
	public static String FILE_BASE_PATH;
	
	public static String FILE_ROOT_PATH;
	
	public static String FILE_SEPARATOR;
	
	@Value("${upload.file.base.path}")
	public void setFileBasePath(String fileBasePath){
		FileProperties.FILE_BASE_PATH = fileBasePath;
	}
	
	@Value("${upload.file.root.path}")
	public void setFileRootPath(String fileRootPath){
		FileProperties.FILE_ROOT_PATH = fileRootPath;
	}
	
	@Value("${upload.file.separator}")
	public void setFileSeparator(String fileSepatator){
		FileProperties.FILE_SEPARATOR = fileSepatator;
	}
	
}
