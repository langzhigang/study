package com.lzg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzg.dao.IAuthorDao;
import com.lzg.entity.Author;
import com.lzg.service.IAuthorService;

@Service("authorService")
public class AuthorService implements IAuthorService{

	@Autowired
	private IAuthorDao authorDao;
	
	public List<Author> findAll() {
		return authorDao.findAll();
	}

	public List<Author> findAllByJoin() {
		return authorDao.findAllByJoin();
	}

}
