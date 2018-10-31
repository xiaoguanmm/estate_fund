package com.upjf.fund.web.controller.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.upjf.fund.dto.DictDetail;
import com.upjf.fund.dto.DictInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.web.aspect.annotation.Log;

/**
 * 诊断管理
 * @author guantong
 *
 */
@Controller
public class DictManageController extends SystemManageController {
	
	@RequestMapping("/dictManage")
	public String todictList(){
		
		return DICT_MANAGE_PAGE;
	}
	
	
	@RequestMapping(value="/queryDictList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryDictList(DictInfo dictInfo, HttpServletRequest request,HttpServletResponse response) {
		
		List<Map<String, Object>> rows = this.dictInfoService.getDictInfosByConditions(dictInfo,getOffset(), getPageRows());
		
		Integer records = this.dictInfoService.countDictInfosByConditions(dictInfo);
		
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增字典信息
	 * @param userInfo
	 * @param request
	 * @param response
	 */
	@Log(module="系统管理-字典管理",content="新增字典")
	@RequestMapping("/addDict")
	public @ResponseBody ModelMap addDict(DictInfo dictInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(dictInfo==null){
			mm.put("errMsg", "字典信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(dictInfo.getStatus())
				||StringUtils.isEmpty(dictInfo.getCode())
				||StringUtils.isEmpty(dictInfo.getName())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		String[] values = request.getParameterValues("values");
		String[] valueDeses = request.getParameterValues("valueDeses");
		
		if(values!=null && valueDeses != null
				&& values.length>0 && 
				valueDeses.length>0 
				&&(values.length==valueDeses.length)){
			DictDetail[] dictDetails = new DictDetail[values.length];
			for(int i=0;i<values.length;i++){
				dictDetails[i] = new DictDetail();
				dictDetails[i].setValue(values[i]);
				dictDetails[i].setValueDes(valueDeses[i]);
			}
			dictInfo.setDictDetails(dictDetails);
		}
		
		DictInfo existDictInfo = this.dictInfoService.getDictInfoBySingleCondition(dictInfo.getCode(), dictInfo.getName());
		if(existDictInfo!=null){
			mm.put("errMsg", String.format("字典 %s - %s 已存在", existDictInfo.getName(),existDictInfo.getCode()));
			return mm;
		}
		UserInfo userInfo = getUserInfo();
		dictInfo.setCreateId(userInfo.getPid());
		dictInfo.setCreateDate(new Date());
		int result=0;
		try {
			result = dictInfoService.addDictInfo(dictInfo);
		} catch (Exception e) {
			e.printStackTrace();
			mm.put("errMsg", e.getMessage());
			return mm;
		}
		
		if(result>0){
			mm.put("successMsg", "新增字典成功");
			return mm;
		}else{
			mm.put("errMsg", "新增字段失败");
			return mm;
		}
	}
	
	
	/**
	 * 获取字典详细参数
	 * @param userInfo
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getDictDetailInfos")
	public @ResponseBody ModelMap getDictDetailInfos(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String pid = request.getParameter("pid");
		
		if(StringUtils.isEmpty(pid)){
			mm.put("errMsg", "字典信息不存在");
			return mm;
		}
		
		List<DictDetail> result = this.dictInfoService.getDictDetailsByPid(pid);
		
		String data = JSONObject.toJSONString(result);
		
		if(!result.isEmpty()){
			mm.put("successMsg", "查询成功");
			mm.put("data", data);
			return mm;
		}else{
			mm.put("errMsg", "未获取到字典详情数据");
			return mm;
		}
	}
	
	/**
	 * 修改字典
	 * @param authority
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-字典管理",content="修改字典")
	@RequestMapping("/modifyDict")
	public @ResponseBody ModelMap modifyDict(DictInfo dictInfo, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(dictInfo==null){
			mm.put("errMsg", "字典信息为空");
			return mm;
		}
		if(StringUtils.isEmpty(dictInfo.getStatus())
				||StringUtils.isEmpty(dictInfo.getCode())
				||StringUtils.isEmpty(dictInfo.getName())){
			mm.put("errMsg", "请填写必要信息");
			return mm;
		}
		
		String oriName = request.getParameter("oriName");
		String oriCode = request.getParameter("oriCode");
		if(!oriName.equals(dictInfo.getName()) || !oriCode.equals(dictInfo.getCode())){
			DictInfo existDictInfo = this.dictInfoService.getDictInfoBySingleCondition(dictInfo.getCode(),dictInfo.getName());
			if(existDictInfo!=null){
				mm.put("errMsg", String.format("字典 %s - %s 已存在", existDictInfo.getName(),existDictInfo.getCode()));
				return mm;
			}
		}
		
		String[] values = request.getParameterValues("values");
		String[] valueDeses = request.getParameterValues("valueDeses");
		
		if(values!=null && valueDeses != null
				&& values.length>0 && 
				valueDeses.length>0 
				&&(values.length==valueDeses.length)){
			DictDetail[] dictDetails = new DictDetail[values.length];
			for(int i=0;i<values.length;i++){
				dictDetails[i] = new DictDetail();
				dictDetails[i].setValue(values[i]);
				dictDetails[i].setValueDes(valueDeses[i]);
			}
			dictInfo.setDictDetails(dictDetails);
		}
		
		dictInfo.setUpdateId(getUserInfo().getPid());
		dictInfo.setUpdateDate(new Date());
		int result=0;
		try {
			result = this.dictInfoService.modifyDictInfo(dictInfo);
		} catch (Exception e) {
			e.printStackTrace();
			mm.put("errMsg", e.getMessage());
			return mm;
		}
		
		if(result>0){
			mm.put("successMsg", "修改字典成功");
			return mm;
		}else{
			mm.put("errMsg", "修改字典失败");
			return mm;
		}
	}
	
	/**
	 * 删除权限
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="系统管理-字典管理",content="删除字典")
	@RequestMapping("/delDict")
	public @ResponseBody ModelMap delDict(HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		String pid = request.getParameter("pid");
		if(StringUtils.isEmpty(pid)){
			mm.put("errMsg", "字典信息为空");
			return mm;
		}
		int result = this.dictInfoService.delDictInfo(pid);
		if(result>0){
			mm.put("successMsg", "删除成功");
			return mm;
		}else{
			mm.put("errMsg", "删除失败");
			return mm;
		}
	}
	
}
