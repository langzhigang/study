package com.lzg.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述分页的参数
 * @author lzg
 * @date 2016年2月17日
 */
public class PageHelper {
	private int pageSize;
	private int currentPageNo;
	private int totalPageNo;
	private int startNo;
	private int endNo;
	
	public PageHelper(int currentPageNo, int pageSize) {
		this.pageSize = pageSize;
		this.currentPageNo = currentPageNo;
		this.startNo = (currentPageNo - 1) * pageSize;
		this.endNo = this.startNo + pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}
	
	public Map<String,Object> toParamsMap(){
		Map<String,Object> result = new HashMap<String, Object>();
//		result.put("pageSize", pageSize);
//		result.put("currentPageNo", currentPageNo);
//		result.put("totalPageNo", totalPageNo);
		result.put("startNo", startNo);
		result.put("endNo", endNo);
		return result;
	}
}
