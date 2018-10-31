package com.upjf.fund.web.controller.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upjf.fund.web.aspect.annotation.Log;
import com.upjf.fund.web.utils.file.download.FileDownLoad;
import com.upjf.fund.web.utils.file.upload.FileUpload;


/**
 * 文件操作
 * @company upjf.com
 * @author guantong
 *
 */
@Controller
@RequestMapping("/estateFundFile")
public class EstateFundFileController {
	

	/**
	 * 显示文件
	 * @param request
	 * @param response
	 * @param path
	 * @param fileName
	 */
	@RequestMapping(value="/showAccessory")
	public void showAccessory(HttpServletRequest request,HttpServletResponse response,String path,String fileName){
		try {
			path = new String(path.getBytes("iso-8859-1"), "utf-8");
			fileName = new String(fileName.getBytes("iso-8859-1"),"utf-8");
			FileDownLoad.downLoadFile(response, request, path, fileName,FileDownLoad.INLINE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @param path
	 * @param fileName
	 */
	@Log(module="文件管理-附件管理",content="下载附件")
	@RequestMapping(value="/downloadAccessory")
	public void downloadAccessory(HttpServletRequest request,HttpServletResponse response, String path,String fileName){
		try {
			path = new String(path.getBytes("iso-8859-1"), "utf-8");
			fileName = new String(fileName.getBytes("iso-8859-1"),"utf-8");
			FileDownLoad.downLoadFile(response, request, path, fileName,FileDownLoad.ATTACHEMENT);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件(逻辑删除)
	 * @param request
	 * @param response
	 * @param pid
	 * @return
	 */
	@Log(module="文件管理-附件管理",content="删除附件")
	@RequestMapping("/deleteFile")
	public @ResponseBody ModelMap deleteFile(HttpServletRequest request,HttpServletResponse response,String filePid){
		ModelMap mm = new ModelMap();
		boolean result = FileUpload.deleteFile(filePid);
		if(result){
			mm.put("successMsg", "删除成功");
		}else{
			mm.put("errMsg", "删除失败");
		}
		
		return mm;
		
	}
	
	
	/**
	 * 校验文件是否存在
	 * @author  guantong 
	 * @param   path
	 * @param   request
	 * @param   response
	 * @return  ModelMap  
	 * @date 2018年9月29日
	 */
	@RequestMapping("/checkFileIsExist")
	public @ResponseBody ModelMap checkFileIsExist(@RequestParam("path")String path,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		try {
			path = new String(path.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(FileDownLoad.fileIsExist(path)){
			mm.put("successMsg", "success");
		}else{
			mm.put("errMsg", "文件不存在");
		}
		return mm;
	}
	
	
}
