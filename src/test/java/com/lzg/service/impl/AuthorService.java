package com.lzg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzg.dao.IAuthorDao;
import com.lzg.entity.Author;
import com.lzg.service.IAuthorService;

@Service("authorService")
public class AuthorService implements IAuthorService{

	@Autowired
	private IAuthorDao authorDao;
	
	@Transactional(readOnly = true)
	public List<Author> findAll() {
		return authorDao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Author> findAllByJoin() {
		return authorDao.findAllByJoin();
	}

	@Transactional(readOnly = true)
	public List<Author> findAllPage(Map<String, Object> params) {
		return authorDao.findAllPage(params);
	}

	@Override
	public int findAllPageCount(Map<String, Object> params) {
		return authorDao.findAllPageCount(params);
	}

}
