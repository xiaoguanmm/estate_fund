package com.upjf.fund.web.controller.system;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upjf.fund.dto.AreaInfo;
import com.upjf.fund.service.system.AreaInfoService;
import com.upjf.fund.web.controller.base.BaseController;


/**
    * @ClassName: AreaInfoController
    * @Description: 省市区控制类
    * @author wufujing
    * @date 2018年4月23日
    *
 */
@Controller
@RequestMapping("/systemAreaInfo")
public class AreaInfoController extends BaseController{
	
	@Autowired
	private AreaInfoService areaInfoService;

	/** 省市集合*/
	@RequestMapping(value="/getAreaInfoList")
	public void getAreaInfoList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
	    List<AreaInfo> areaInfos = null;
		try {
			String areaCode = request.getParameter("areaCode");
			String areaName = request.getParameter("areaName");
			String parentCode = request.getParameter("parentCode");
			String level = request.getParameter("level");
			if(!StringUtils.isBlank(areaName)){
				areaName = URLDecoder.decode(areaName, "UTF-8");
			}
			
			AreaInfo areaInfo = new AreaInfo();
			areaInfo.setAreaCode(areaCode);
			areaInfo.setAreaName(areaName);
			areaInfo.setParentCode(parentCode);
			areaInfo.setLevel(level);
			
			areaInfos = areaInfoService.getAreaInfoList(areaInfo);
			resultMap.put("areaInfos", areaInfos);
			resultMap.put("code", "0");
			resultMap.put("msg", "成功");
			
		} catch (Exception e) {
			resultMap.put("code", "1");
			resultMap.put("msg", "失败");
			e.printStackTrace();
		}
	    
	    // 输出
		outPutJson(resultMap,response);
	}

}
