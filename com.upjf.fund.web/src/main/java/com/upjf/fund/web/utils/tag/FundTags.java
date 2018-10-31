package com.upjf.fund.web.utils.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.upjf.fund.service.system.FundTagService;

/**
 * fund自定义标签
 * @author guantong
 *
 */
public class FundTags  extends RequestContextAwareTag{
	private static final long serialVersionUID = -3213498104367285583L;
	
	@Autowired
	private FundTagService fundTagService;
	
	/**
	 * 映射标签code属性
	 */
	private String code;
	
	/**
	 * 映射标签value属性
	 */
	private String value;
	
	@Override
	protected int doStartTagInternal() throws Exception {
		StringBuilder outStr = new StringBuilder();
		JspWriter writer = pageContext.getOut();  
		fundTagService = this.getRequestContext().getWebApplicationContext().getBean(FundTagService.class);
		List<Map<String,String>> dataList = this.fundTagService.getDataByCode(code);
		for(Map<String,String> data:dataList){
			String label = String.valueOf(data.get("label"));
			String val = String.valueOf(data.get("value"));
//			String pid = String.valueOf(data.get("pid"));
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
