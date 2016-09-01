package com.lzg.result.dto;

import com.lzg.entity.TestUser;
import com.lzg.interceptor.PageParameter;

public class TestPage1 {
	private PageParameter page;
	private String orderBy;

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
