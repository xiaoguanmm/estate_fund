package com.upjf.fund.web.filter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 防止XSS跨站脚本攻击和SQL注入攻击
 * 使用Spring的HtmlUtils对参数进行转义
 * @company upjf.com
 * @author guantong
 *
 */
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper{

	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values==null)  {
			return null;
		}
        String[] newValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = cleanXSS(newValues[i]);
        }
        return values;
	}
	
	@Override
	public String getParameter(String name) {
		name = super.getParameter(name);
		if(name==null){
			return null;
		}
		return cleanXSS(name);
	}
	
	@Override
	public String getHeader(String name) {
        name = super.getHeader(name);
        if (name == null){
        	return null;
        }
        return cleanXSS(name);
	}
	private String cleanXSS(String value) {
		if (value != null) {
            /* 避免空字符串*/
            value = value.replaceAll(" ", "");
            /* 避免script 标签*/
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 避免src形式的表达式*/
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            /*删除单个的 </script> 标签*/
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 删除单个的<script ...> 标签*/
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 避免 eval(...) 形式表达式*/
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 避免 e­xpression(...) 表达式*/
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 避免 javascript: 表达式*/
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 避免 vbscript:表达式*/
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 避免 onload= 表达式*/
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            /* 对表单事件进行处理*/
    		scriptPattern = Pattern.compile("on(.*?)=\\\'.*?\\\'", Pattern.CASE_INSENSITIVE);
    		value = scriptPattern.matcher(value).replaceAll("");
    		scriptPattern = Pattern.compile("on(.*?)=\\\".*?\\\"", Pattern.CASE_INSENSITIVE);
    		value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }

}
