package com.upjf.fund.web.utils.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.upjf.fund.service.system.FundTagService;

public class EnterpriseTags extends RequestContextAwareTag{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295987380815258059L;
	
	/**
	 * 映射标签code属性，标记项目公司或者企业
	 */
	private String type;
	
	/**
	 * 映射标签value属性
	 */
	private String value;
	
	@Autowired
	private FundTagService fundTagService;

	@Override
	protected int doStartTagInternal() throws Exception {
		StringBuilder outStr = new StringBuilder();
		JspWriter writer = pageContext.getOut();  
		fundTagService = this.getRequestContext().getWebApplicationContext().getBean(FundTagService.class);
		List<Map<String,String>> dataList = this.fundTagService.getEnterpriseDataByType(type);
		for(Map<String,String> data:dataList){
			String label = String.valueOf(data.get("label"));
			String val = String.valueOf(data.get("value"));
			String selected="";
			if(StringUtils.isNotEmpty(this.value) && this.value.equals(val)){
				selected="selected=\"selected\"";
			}
			outStr.append("<option value=\""+val+"\" "+selected+">"+label+"</option>");
			
		}
		writer.write(outStr.toString());
		writer.flush();
		return 0;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
		
	

}
