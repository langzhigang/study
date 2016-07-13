package com.lzg.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ITestDao {

	public int insert(int id);

	public int batchInsert(Map<String, Object> p);

	public int insertOne(@Param("id") int id, @Param("name") String name);
}
