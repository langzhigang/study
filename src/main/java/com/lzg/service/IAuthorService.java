package com.lzg.service;

import java.util.List;

import com.lzg.entity.Author;

public interface IAuthorService {
	public List<Author> findAll();
	
	public List<Author> findAllByJoin();
}
