package com.upjf.fund.web.utils.file.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upjf.fund.web.utils.file.FileProperties;


/**
 * 文件下载
 * @company upjf.com
 * @author guantong
 *
 */
public class FileDownLoad {
	
	protected static Logger logger = LoggerFactory.getLogger(FileDownLoad.class);
	
	/**disposition 类型 告诉浏览下载*/
	public static final String ATTACHEMENT = "attachment";
	
	/**disposition 类型 告诉浏览显示*/
	public static final String INLINE = "inline";
	
	
	
	private static String getAbsolutePath(String path){
		
		return FileProperties.FILE_BASE_PATH + path;
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param request
	 * @param path
	 * @param fileName
	 * @param disposition 告诉浏览器是显示还是下载
	 * @throws IOException 
	 */
	public static void downLoadFile(HttpServletResponse response, HttpServletRequest request,String path,String fileName,String disposition) throws IOException{
		String filePath = getAbsolutePath(path);
		File file = new File(filePath);
		/*如果文件存在才下载*/
		if(file.exists()){
			outputFile(file, filePath, fileName, disposition, response);
		}else {
			/*预览文件时如果文件不存在，使用404文件替换*/
			if(FileDownLoad.INLINE.equals(disposition)){
				logger.info(String.format("预览文件 %s 不存在", fileName));
				String realPath = request.getSession().getServletContext().getRealPath("/common/img/404.png");
				if(realPath!=null){
					File realFile = new File(realPath);
					outputFile(realFile, realPath, "404.png", disposition, response);
				}
			}else{
				throw new FileNotFoundException(String.format("下载文件 %s 不存在", fileName));
			}
		}
	}
	
	/**
	 * 输出文件
	 * @throws IOException 
	 */
	private static void outputFile(File file,String filePath, String fileName,String disposition,HttpServletResponse response) throws IOException{
		InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
		byte[] readBites = new byte[fis.available()];
		fis.read(readBites);
		fis.close();
		fileName = new String(fileName.getBytes(),"ISO8859-1");
		response.addHeader("Content-Disposition", disposition+";filename=" + fileName );
		response.addHeader("Content-Length", "" + file.length());
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		os.write(readBites);
		os.flush();
		os.close();
	}

	/**
	 * 校验文件是否存在
	 * @param url
	 * @return
	 */
	public static boolean fileIsExist(String path) {
		File file = new File(getAbsolutePath(path));
		if(file.exists()){
			return true;
		}
		return false;
	}
}
