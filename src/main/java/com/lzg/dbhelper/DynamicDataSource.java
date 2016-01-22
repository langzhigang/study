package com.lzg.dbhelper;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义数据源路由类
 * @author lzg
 * @date 2016年1月22日
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DBContextHolder.getDbType();
	}

}
