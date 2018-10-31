package com.upjf.fund.base;

import java.io.Serializable;

import com.upjf.fund.constants.EstateFundConstants;

/**
 * 分页封装类
 * @author lixq
 * @date 2018年9月20日
 */
public class Page implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//当前页,默认第一页
	private String curPage = EstateFundConstants.DEFAULT_CUR_PAGE;
	
	//下一页
	private Integer nextPage;
	
	//上一页
	private Integer upPage;
	
	//尾页页码
	private Integer endPage;
	
	//每页显示记录数,默认为10条每页
	private String pageSize = EstateFundConstants.DEFAULT_PAGE_ROWS + "";
	
	//总页数
	private Integer totalPage;
	
	//总记录数
	private Integer totalCount;

	
	
	
	
	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getUpPage() {
		return upPage;
	}

	public void setUpPage(Integer upPage) {
		this.upPage = upPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		int allPage = (totalCount+Integer.parseInt(pageSize)-1)/Integer.parseInt(pageSize);
		this.totalCount = totalCount;
		this.totalPage = allPage;
		
		if(Integer.parseInt(curPage) == 1){
			this.upPage = 1;
		}else{
			this.upPage = Integer.parseInt(curPage) - 1;
		}
		
		if(totalCount == 0){
			this.endPage = 1;
		}else{
			this.endPage = allPage;
		}
		
		if(Integer.parseInt(curPage) == allPage){
			this.nextPage = Integer.parseInt(curPage);
		}else{
			this.nextPage = Integer.parseInt(curPage) + 1;
		}
		
	}
	
}
