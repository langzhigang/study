package com.lzg.dao;

import com.lzg.entity.Author;
import com.lzg.result.dto.TestPage1;

import java.util.List;
import java.util.Map;

public interface IAuthorMapper {

	List<Author> findAll();

	List<Author> findAllByJoin();

	List<Author> findAllPage(Map<String, Object> params);

	int findAllPageCount(Map<String, Object> params);

	List<String> findAuthorsPage(TestPage1 testPage);
}
