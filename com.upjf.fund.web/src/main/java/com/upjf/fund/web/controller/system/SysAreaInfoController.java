package com.upjf.fund.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upjf.fund.dto.SysAreaInfo;
import com.upjf.fund.service.system.SysAreaInfoService;
import com.upjf.fund.web.controller.base.BaseController;


/**
 * 系统地区信息控制层
 * @author lixq
 * @date 2018年9月27日
 */
@Controller
@RequestMapping("/sysAreaInfoController")
public class SysAreaInfoController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(SysAreaInfoController.class);
	
	@Autowired
	private SysAreaInfoService sysAreaInfoService;				//系统地区信息表服务

	
	/**
	 * 根据条件查询获取系统地区信息
	 * @author  lixq 
	 * @param sysAreaInfo
	 * @param req
	 * @return  Map<String,Object>  
	 * @date 2018年9月27日
	 */
	@RequestMapping(value="/getSonAreaList")
	@ResponseBody
	public Map<String,Object> getAreaInfoList(SysAreaInfo sysAreaInfo,HttpServletRequest req){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			if(sysAreaInfo != null){
				List<SysAreaInfo> sonAreaList = sysAreaInfoService.getSysAreaByCondition(sysAreaInfo);
				resultMap.put("success", "s");
				resultMap.put("msg", "获取成功!");
				resultMap.put("sonAreaList", sonAreaList);
			}else{
				resultMap.put("success", "e");
				resultMap.put("msg", "请求参数为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错,获取系统地区信息失败!");
			resultMap.put("success", "e");
			resultMap.put("msg", "系统出错,获取系统地区信息失败!");
		}
		
		return resultMap;
	}

}
