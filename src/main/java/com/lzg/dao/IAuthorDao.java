package com.lzg.dao;

import java.util.List;
import java.util.Map;

import com.lzg.entity.Author;

public interface IAuthorDao {

	List<Author> findAll();

	List<Author> findAllByJoin();

	List<Author> findAllPage(Map<String, Object> params);

}
