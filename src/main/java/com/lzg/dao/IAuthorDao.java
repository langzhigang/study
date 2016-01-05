package com.lzg.dao;

import java.util.List;

import com.lzg.entity.Author;

public interface IAuthorDao {

	List<Author> findAll();

	List<Author> findAllByJoin();

}
