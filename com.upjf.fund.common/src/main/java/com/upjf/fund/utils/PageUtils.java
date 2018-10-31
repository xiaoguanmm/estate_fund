package com.upjf.fund.utils;

import org.apache.commons.lang.StringUtils;

import com.upjf.fund.base.Page;
import com.upjf.fund.constants.EstateFundConstants;

/**
 * 分页参数去空和空值校验并重新赋值工具类
 * @author lixq
 * @date 2018年9月25日
 */
public class PageUtils {
	
	/**
	 * 页码去空格并重赋值
	 * @param page
	 */
	public static void toTrimPageFields(Page page){
		if(page != null){
			String curPage = page.getCurPage();									//当前查询页码
			String pageSize = page.getPageSize();								//每页显示条数
			
			if(StringUtils.isNotBlank(curPage)){
				curPage = curPage.trim();
			}else{
				curPage = EstateFundConstants.DEFAULT_CUR_PAGE;					//默认显示第一页
			}
			
			if(StringUtils.isNotBlank(pageSize)){
				pageSize = pageSize.trim();
			}else{
				pageSize = EstateFundConstants.DEFAULT_PAGE_ROWS + "";			//默认每页显示10条
			}
			
			page.setCurPage(curPage);
			page.setPageSize(pageSize);
		}
	}

}
