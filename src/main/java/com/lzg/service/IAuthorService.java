package com.lzg.service;

import java.util.List;
import java.util.Map;

import com.lzg.entity.Author;

public interface IAuthorService {
	public List<Author> findAll();
	
	public List<Author> findAllByJoin();

	public List<Author> findAllPage(Map<String, Object> params);
}
