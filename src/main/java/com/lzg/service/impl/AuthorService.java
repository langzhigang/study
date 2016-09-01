package com.lzg.service.impl;

import com.lzg.dao.IAuthorMapper;
import com.lzg.entity.Author;
import com.lzg.result.dto.TestPage1;
import com.lzg.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("authorService")
public class AuthorService implements IAuthorService{

	@Autowired
	private IAuthorMapper authorMapper;
	
	@Transactional(readOnly = true)
	public List<Author> findAll() {
		return authorMapper.findAll();
	}

	@Transactional(readOnly = true)
	public List<Author> findAllByJoin() {
		return authorMapper.findAllByJoin();
	}

	@Transactional(readOnly = true)
	public List<Author> findAllPage(Map<String, Object> params) {
		return authorMapper.findAllPage(params);
	}

	@Override
	public int findAllPageCount(Map<String, Object> params) {
		return authorMapper.findAllPageCount(params);
	}

	@Override
	public List<String> findAuthorsPage(TestPage1 testPage) {
		List<String> result = authorMapper.findAuthorsPage(testPage);
		return result;
	}

}
