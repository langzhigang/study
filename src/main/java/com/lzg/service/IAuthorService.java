package com.lzg.service;

import com.lzg.entity.Author;
import com.lzg.result.dto.TestPage1;

import java.util.List;
import java.util.Map;

public interface IAuthorService {
	public List<Author> findAll();
	
	public List<Author> findAllByJoin();

	public List<Author> findAllPage(Map<String, Object> params);

	public int findAllPageCount(Map<String, Object> params);

	List<String> findAuthorsPage(TestPage1 testPage);
}
